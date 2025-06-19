# Student Management System – Spring Boot Backend

## Overview
This project is a simple RESTful API built with Spring Boot to manage student records. It supports basic CRUD operations along with filtering and soft deletion.

---

## Features

- Add new students with unique roll numbers
- Update student details by roll number
- Soft delete students (mark as deleted without removing from database)
- List all active students
- List all deleted students
- Search students by roll number
- Filter students by gender and technology

---

## Technology Stack

- Java 17+
- Spring Boot 3.x
- Spring Data JPA (Hibernate)
- MySQL
- Maven

---

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven 3.6+
- IDE such as IntelliJ IDEA or Eclipse
- (Optional) Postman for API testing

### Setup & Run

1. Clone the repository:

   ```bash
   git clone https://github.com/praveeneswarM/StudentManagementSystem_SpringBoot.git
   cd StudentManagementSystem_SpringBoot
   mvn clean install
   mvn spring-boot:run
