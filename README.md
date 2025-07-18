
# 💳 Payment Routing Microservice

This Spring Boot application is a **Payment Routing System** that dynamically routes payment requests to mock processors (Processor A, B, or C) based on configurable rules defined in the `application.yml` file. It uses **WebClient** for inter-service communication and **H2 in-memory database** to persist transaction logs.

---

## 🚀 Features

- Accepts payment requests via REST API.
- Routes requests to mock processors based on:
  - `merchantId`
  - `cardType` + `minAmount`
- Stores all transactions in the H2 database.
- Returns success/failure response from the selected processor.
- Reads routing logic from `application.yml`.
- Validates input and handles errors gracefully.

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring WebFlux (WebClient)
- H2 Database
- Maven
- YAML-based configuration


