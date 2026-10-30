# library-book-borrowing-system-backend

Student name: Kalaki Noelyn Vanessa  
Student ID: 27540   
Project name: Library Book Borrowing System (Mid-term exam, Web Technology)

## About
A Spring Boot REST API for borrowing library books. It uses Spring Data JPA and PostgreSQL.

## Requirements
- Java 17
- Maven
- PostgreSQL

## How to run
1. Install PostgreSQL and create a database named librarydb.
2. Open src/main/resources/application.properties and set your own PostgreSQL username and password.
3. Open a terminal in the project folder and run: mvn clean spring-boot:run

4. The API starts on http://localhost:8080

## Testing
Import library-book-borrowing-system-midterm.postman_collection.json into Postman. It has one request per requirement, named by its ID (A2, B1, C4, and so on).
