# Assignment

Authentication service generates information about transactions. Each transaction contains following
information:
- ID (integer)
- Timestamp
- Type (string)
- Actor (string)
- Transaction data (key-value map of strings)

The transactions must be collected by a new service. The service should receive the data at HTTP
interface, store them in SQL database and make them available via the HTTP interface.

Implement the service for CRUD (Create Update Delete) and search operations. Suggest and design what
the search operation should look like to be usable.

Implement it as a Spring application using MySQL database.

## Database table
The application uses only one database table, which stores individual records in JSON format. No additional tables were created, as using JSON for the transaction_data column provides a simple implementation and flexibility in storing various types of transaction-related data.

`
CREATE TABLE transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    timestamp TIMESTAMP NOT NULL,
    type VARCHAR(255) NOT NULL,
    actor VARCHAR(255) NOT NULL,
    transaction_data JSON
);
`
## GUI
The GUI of the application is represented by Swagger, which provides an interactive API documentation and allows users to test endpoints directly from the browser
`http://localhost:8080/swagger-ui/index.html#/`

## Settings
Need to add some settings/database configuration to **application.properties**. For example,
* **spring.datasource.url**=_jdbc:mysql://localhost:3306/transaction_db_
* **spring.datasource.username**=_root_
* **spring.datasource.password**=_root_

## Project structure
* **api** - contains interfaces which define API endpoints with Swagger annotations
* **configuration** - stores configuration-related classes, such as CORS policies, or security configurations.
* **controller** - contains REST controllers that handle HTTP requests, process inputs, and return responses to clients.
* **converter** - contains classes that transform data between different formats, such as converting between entities and DTOs.
* **dto** - contains DTO classes used to transfer data between layers without exposing the database model directly.
* **model** - contains entity classes that represent database tables. These classes are mapped using JPA/Hibernate.
* **repository** - stores interfaces extending JpaRepository or CrudRepository for database access and queries.
* **service** - contains business logic and service layer methods that handle operations between repositories and controllers.
* **specification** - stores JPA specifications or criteria queries for filtering and searching database records dynamically.
