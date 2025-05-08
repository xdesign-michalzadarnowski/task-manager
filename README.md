# Task Manager

This is a simple task management web application built with Spring Boot and Thymeleaf. 
It includes a user interface for adding and completing tasks.

## Requirements

* Java 21+
* Gradle

## Running the Application

1. Clone the repository:

   ```sh
   git clone <repository-url>
   cd task-manager
   ```

2. Build the project:

   ```sh
   ./gradlew build
   ```

3. Run the application:

   ```sh
   ./gradlew bootRun
   ```

4. Open your browser and visit:

   ```
   http://localhost:8080
   ```

## Running Tests

To run all unit tests:

```sh
./gradlew test
```

## Project Structure

* `Task` - The model representing a single task.
* `TaskService` - The service layer for managing tasks.
* `MainController` - Handles rendering the Thymeleaf template.
* `TaskController` - Provides the REST API endpoints.
* `templates/tasks.html` - The Thymeleaf view.
* `static/style.css` - Styling for the UI.

## Notes

* Tasks are stored in memory and are not persisted.
* You can modify the controller or service to integrate with a database if needed.
