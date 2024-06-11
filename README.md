# Uniformix - Uniform Management System

![Java](https://img.shields.io/badge/Java-17-orange)
![TypeScript](https://img.shields.io/badge/TypeScript-blue)
![Angular](https://img.shields.io/badge/Angular-14%2B-red)
![Spring](https://img.shields.io/badge/Spring-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Hibernate](https://img.shields.io/badge/Hibernate-orange)
![Flyway](https://img.shields.io/badge/Flyway-Migrations-red)
[![LinkedIn](https://img.shields.io/badge/Connect%20on-LinkedIn-blue)](https://www.linkedin.com/in/oihenriquegomes/)

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Local Usage](#local-usage)
- [Contact](#contact)

## Introduction

The main purpose of Uniformix is to provide inventory control of company uniforms, allowing operations to be recorded through a transaction history and physically through protocols signed by employees.

Live application: [https://uniformix-app.web.app/](https://uniformix-app.web.app/)

## Features

- **CRUD Operations:** Create, read, update, and delete uniforms, uniform batches, units, categories, and suppliers.
- **Report Generation:** Transaction report, uniform, and batch report.
- **Protocol Issuance:** For each transaction, the system issues a PDF protocol for printing and physical signature.
- **Web Interface**
- **User Authentication**
- **Secure Password Hashing and Salting using BCrypt.**
- **Integration with PostgreSQL Database** for storing system information.

## Technologies Used

- ![Java](https://img.shields.io/badge/Java-17-orange): Programming language for building the back-end API application.
- ![TypeScript](https://img.shields.io/badge/TypeScript-blue): Programming language for building the front-end application.
- ![Angular](https://img.shields.io/badge/Angular-14%2B-red): Framework used to manage a SPA in front-end.
- ![Spring](https://img.shields.io/badge/Spring-green): Framework used to build API REST, authentication, and security in the back-end.
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue): Database for storing user-related data.
- ![Hibernate](https://img.shields.io/badge/Hibernate-orange): Framework for simplifying database interaction by mapping Java objects to database tables.
- ![Flyway](https://img.shields.io/badge/Flyway-Migrations-red): Database migration tool that simplifies version control and management of database schemas.

## Local Usage

1. Clone the repository:
```sh
git clone <REPOSITORY_URL>
```
2. Navigate to the front-end directory and install dependencies:
```sh
cd <FRONTEND_DIRECTORY>
npm install
```
3. Build the front-end application:
```sh
npm run build
```
4. run the API
5. Serve the front-end application:
```sh
cd <FRONTEND_DIRECTORY>
ng serve
```

## Contact

Connect with me on [LinkedIn](https://www.linkedin.com/in/oihenriquegomes/).
