# MOITT System
### Ministry of IT & Telecom e-Services Platform

## 📌 Overview

**MOITT System** is a Spring Boot REST API developed for a Ministry of IT & Telecom e-Services platform.

The system provides APIs for managing citizens, telecom operators, digital services, applications, spectrum licenses, complaints, inspections, projects, and documents.

---

## ✨ Features

- Full CRUD operations for system entities
- DTO-based API requests and responses
- Builder Pattern for DTO conversion
- Request validation using Spring Validation
- Global exception handling
- Soft delete functionality
- JPA/Hibernate entity relationships
- Custom business operations
- REST API testing using Postman

---

## 🛠 Technologies

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Spring Boot | Backend framework |
| Spring Data JPA | Database access |
| Hibernate | ORM |
| MySQL | Database |
| Lombok | Reduce boilerplate code |
| Maven | Dependency management |
| Postman | API testing |

---

## 📂 Project Structure

```text
MOITT-System/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
├── README.md
└── ...
```

### Main Package Structure

```text
src/main/java/
└── com.example.moitt/
    ├── controllers/
    ├── services/
    ├── repositories/
    ├── entities/
    ├── dto/
    ├── exceptions/
    └── ...
```

---

## 🗄️ Main System Modules

The system contains modules for:

- Citizens
- Telecom Operators
- Digital Services
- Applications
- Spectrum Licenses
- Complaints
- Inspections
- Projects
- Documents

---

## 🔗 API

The application exposes RESTful endpoints for creating, retrieving, updating, and deleting system data.

Example:

```text
GET    /api/...
POST   /api/...
PUT    /api/...
DELETE /api/...
```

---

## ✅ Validation & Error Handling

The system uses:

- `@Valid`
- Jakarta Bean Validation
- Custom validation rules
- Global exception handling
- Appropriate HTTP status codes

Invalid requests return structured error responses instead of exposing internal application errors.

---

## 🗑️ Soft Delete

Entities that support deletion use **soft delete** rather than permanently removing records from the database.

This helps preserve historical data while preventing deleted records from appearing in normal API results.

---

## 🔄 Business Operations

In addition to standard CRUD operations, the system includes custom business operations that handle relationships and rules between different entities.

---

## 🧪 API Testing

The REST APIs were tested using **Postman**.

Testing includes:

- Create operations
- Retrieve operations
- Update operations
- Delete operations
- Validation errors
- Exception handling
- Business operations

---

## ⚙️ Running the Project

### 1. Clone the repository

```bash
git clone <repository-url>
```

### 2. Configure MySQL

Create a MySQL database and update the database configuration in:

```text
src/main/resources/application.properties
```

### 3. Run the application

Using Maven:

```bash
mvn spring-boot:run
```

Or run the main Spring Boot application class from your IDE.

---

## 👩‍💻 Development

This project was developed using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL**, following a layered REST API architecture.