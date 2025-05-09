# Task Manager

This is a simple task management web application built with Spring Boot and Thymeleaf. 
It includes a user interface for adding and completing tasks.

## Requirements

* Java 21+
* Gradle
* H2 Database (embedded, runs in-memory)

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

* `Task` – The model representing a single task.
* `TaskRepository` – A simple repository using `JdbcTemplate` for data access.
* `TaskService` – The service layer for managing task logic.
* `TaskController` – REST API endpoints for interacting with tasks.
* `templates/tasks.html` – The Thymeleaf front-end.
* `static/style.css` – Styling for the UI.


## Notes

* Task data is stored in an embedded H2 in-memory database.
* Tables are created automatically on startup.
* You can view the H2 console at `http://localhost:8080/h2-console`.
