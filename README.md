# 🚧 Digital Account API (In Development)

This project is currently **under development** and is part of my personal backend portfolio.

## 📌 Overview

The **Digital Account API** is a simplified banking system built with **Java and Spring Boot**, designed to simulate core features of a modern digital bank.  
The main goal is to demonstrate solid backend skills, clean architecture, security best practices, and well-defined business rules at a **mid-level (pleno)** standard.

## 🎯 Project Goals

- Apply real-world business rules
- Implement secure authentication and authorization
- Follow clean architecture and best practices
- Build a maintainable and well-structured backend API
- Serve as a professional portfolio case

## 🧱 Functional Scope (Initial)

The API will support:

- Digital account creation
- Deposit into an account
- Withdraw from an account
- Transfer between accounts
- Balance inquiry
- Currency conversion using an external exchange rate API

## 🔐 Technical Stack

- **Language:** Java  
- **Framework:** Spring Boot  
- **Security:** Spring Security + JWT  
- **Persistence:** JPA / Hibernate  
- **Database:** H2 (development) or PostgreSQL 
- **Documentation:** Swagger / OpenAPI  
- **Testing:**  
  - Unit tests (service layer)  
  - Integration tests (when applicable)

## 🏗️ Architecture Guidelines

- Clear separation of layers:
  - Controller
  - Service
  - Domain
  - Repository
- Use of DTOs for input/output
- Input validation
- Business rules handled in domain or service layer
- Clean, readable, and maintainable code

---

🚀 **Status:** In Progress  
More features, tests, and documentation will be added as the project evolves.
