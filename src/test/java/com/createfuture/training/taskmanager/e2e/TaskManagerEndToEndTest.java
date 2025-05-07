package com.createfuture.training.taskmanager.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TaskManagerEndToEndTest {

    private WebDriver driver;

    @BeforeAll
    void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get("http://localhost:8080");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Page loads and shows title")
    void pageLoads() {
        WebElement heading = driver.findElement(By.tagName("h1"));
        Assertions.assertEquals("Task Manager", heading.getText());
    }

    @Test
    @DisplayName("Add task and verify it's listed")
    void addTask() {
        String taskName = "Add Task Test";

        WebElement input = driver.findElement(By.id("title"));
        input.clear();
        input.sendKeys(taskName);

        WebElement button = driver.findElement(By.cssSelector("form button"));
        button.click();

        // Wait until the updated task appears on the page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='" + taskName + "']")
        ));

        List<WebElement> tasks = driver.findElements(By.cssSelector("ul li span"));
        boolean found = tasks.stream().anyMatch(el -> el.getText().equals(taskName));

        Assertions.assertTrue(found, "Task should be listed on the page");

        // Cleanup: click the done button
        WebElement doneButton = driver.findElement(
                By.xpath("//span[text()='" + taskName + "']/following-sibling::form/button")
        );
        doneButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//span[text()='" + taskName + "']")
        ));
    }

    @Test
    @DisplayName("Mark a task as done")
    void markTaskDone() {
        String taskName = "Mark as Done Test ";

        WebElement input = driver.findElement(By.id("title"));
        input.clear();
        input.sendKeys(taskName);

        WebElement addButton = driver.findElement(By.cssSelector("form button"));
        addButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='" + taskName + "']")
        ));

        // Now locate the "Done" button *fresh*
        WebElement doneButton = driver.findElement(
                By.xpath("//span[text()='" + taskName + "']/following-sibling::form/button")
        );
        doneButton.click();

        // Wait until the task is removed
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//span[text()='" + taskName + "']")
        ));

        // Confirm task is no longer listed
        List<WebElement> tasks = driver.findElements(By.cssSelector("ul li span"));
        boolean stillPresent = tasks.stream().anyMatch(el -> el.getText().equals(taskName));
        Assertions.assertFalse(stillPresent, "Task should be removed from the list");
    }
}
