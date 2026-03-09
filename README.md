# Digital Bank API

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3-green)
![License](https://img.shields.io/badge/license-MIT-blue.svg)


A RESTful API built with **Java and Spring Boot** that simulates the core operations of a digital banking system.

This project was developed with a strong focus on **clean architecture, business rules, security, and backend best practices**, serving as a **portfolio project to demonstrate backend development skills**.

---

# 🚀 Features

The API provides basic banking operations such as:

* 👤 User registration
* 🏦 Bank account creation
* 💰 Deposit operations
* 💸 Withdraw operations
* 🔁 Transfers between accounts
* 📄 Transaction history
* 🔐 JWT-based authentication
* 📧 Email verification for new users

---

# 🧠 Business Rules Implemented

* Withdrawals cannot exceed the available account balance
* Negative transaction values are not allowed
* Transfers are executed **atomically**
* Every financial operation generates a **transaction record**
* Nonexistent accounts return **resource not found errors**
* Database exceptions are handled through centralized error handling

---

# 🏗 Project Architecture

The project follows a **layered architecture** structure:

Controller → Service → Domain → Repository

### Responsibilities

**Controller**

* Handles HTTP requests
* Validates incoming data
* Returns API responses

**Service**

* Orchestrates use cases
* Applies business rules

**Domain (Model)**

* Contains entity behavior
* Protects application state

**Repository**

* Data persistence layer using JPA

---

# 🔐 Security

The API uses:

* **Spring Security**
* **JWT (JSON Web Token)** authentication
* Public and protected routes
* Email verification during user registration

---

# 🛠 Technologies Used

* Java 17+
* Spring Boot
* Spring Security
* JWT
* Spring Data JPA
* Hibernate
* Java Mail Sender
* Lombok
* Maven
* H2 / PostgreSQL

---

# 📂 Project Structure

src/main/java

controller
service
model
repository
dto
config
exception

---

# ⚙️ How to Run the Project

### 1️⃣ Clone the repository

```bash
git clone https://github.com/MurilloMorgado/digital-account-api.git
```

### 2️⃣ Navigate to the project folder

```bash
cd digital-account-api
```

### 3️⃣ Run the application

```bash
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8080
```

---

# 📬 Email Configuration

To enable email verification, configure the following properties in `application.properties`:

```
spring.mail.host=
spring.mail.port=
spring.mail.username=
spring.mail.password=
```

---

# 📖 Example Endpoints

## Create User

POST /users

```json
{
  "name": "John Doe",
  "email": "john@email.com",
  "password": "123456"
}
```

---

## Create Account

POST /accounts

```json
{
  "agency": 123,
  "currentAccount": 456789,
  "customer": "John Doe",
  "bank": "DigitalBank",
  "accountType": "CHECKING"
}
```

---

## Deposit

POST /transactions/deposit

```json
{
  "sourceAccount": {
    "agency": 123,
    "currentAccount": 456789,
    "customer": "John Doe",
    "bank": "DigitalBank",
    "accountType": "CHECKING"
  },
  "amount": 500
}
```

---

## Withdraw

POST /transactions/withdraw

```json
{
  "sourceAccount": {
    "agency": 123,
    "currentAccount": 456789,
    "customer": "John Doe",
    "bank": "DigitalBank",
    "accountType": "CHECKING"
  },
  "amount": 100
}
```

---

## Transfer

POST /transactions/transfer

```json
{
  "sourceAccount": {
    "agency": 123,
    "currentAccount": 456789,
    "customer": "John Doe",
    "bank": "DigitalBank",
    "accountType": "CHECKING"
  },
  "destinationAccount": {
    "agency": 321,
    "currentAccount": 987654,
    "customer": "Jane Doe",
    "bank": "DigitalBank",
    "accountType": "CHECKING"
  },
  "amount": 250
}
```

---

# 🧪 Testing

The project includes a structure prepared for unit testing using:

* JUnit
* Spring Boot Test

---

# 🎯 Project Purpose

This project was created to:

* Practice **professional backend architecture**
* Simulate real-world banking business rules
* Demonstrate best practices with **Spring Boot**
* Serve as a **backend portfolio project**

