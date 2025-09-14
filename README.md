This is a web-based Ecommerce application developed using Java Spring Boot for the backend and JSP pages for the frontend. The application simulates an online shopping platform where users can browse products, add them to the cart, and place orders. The backend handles data management, business logic, and security.

Features
User registration and login functionality

Product catalog browsing with categories

Add to cart and manage cart items

Order placement and order history tracking

Admin dashboard for managing products and categories

Secure authentication and session management

Technology Stack
Java 17+

Spring Boot framework

JSP (Java Server Pages) for front-end UI

MySQL for relational database

Spring Data JPA for ORM

Spring Security for authentication and authorization

Maven for build and dependencies

Setup and Installation
Clone the repository:

text
git clone https://github.com/SravanthiMummidi/Ecommerce-.git
Database Setup:

Install MySQL and create a database (e.g., ecommerce_db).

Import the database schema from the provided SQL file (if any).

Update application.properties with your database credentials:

text
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password
Build and Run the Application:

Use Maven to build the project:

text
mvn clean install
Run the Spring Boot application:

text
mvn spring-boot:run
Access the Application:

Open your browser and navigate to:

text
http://localhost:8080/
Contribution
Contributions are welcome! Feel free to open issues or submit pull requests.
