# Y3S2-Software-Engineering

# Pizza Ordering System

## Project Overview

**Semester:** 2022/2023 Semester 2  
**Course:** CPT202 Assignment 1  
**Group:** 24  
**Project Website:** [Pizza Ordering System](http://121.199.161.166:8080/pizzaOrderingSys/login)  
**Project Report:** [Project Report](https://github.com/LCQck/Y3S2-Software-Engineering/blob/f6ca63b132bddc38618488f28dc40458fbdcd290/Project%20Report.pdf)  
**Peresonal Report:** [CPT202-A3-2039153 -Personal Report](https://github.com/LCQck/Y3S2-Software-Engineering/blob/5df9ae9095edd08453246a7c169fdbf3abd4f995/CPT202-A3-2039153%20-Personal%20Report.pdf)  
**Date:** 2023.5.5  

### Team Members

- Zichao Cong (2035606)
- Wenbo Wang (2036580)
- Zihao Li (2037161)
- Changqing Lin (2039153)
- Xinyi Xu (2034100)
- Enhua Kang (2033981)
- Ke Bai (2035843)
- Siyi Pan (2033961)

### Project Demo Video  

For a comprehensive demonstration of the project's design architecture, technical details, and a walkthrough of all functionalities, please watch our demo video on YouTube: [Pizza Ordering System Demo](https://www.youtube.com/watch?v=4T7PLrUN4U8)  

## Table of Contents

1. [Introduction](#introduction)
2. [Aims of the Project](#aims-of-the-project)
3. [Project Scope](#project-scope)
4. [User Characteristics](#user-characteristics)
5. [Assumptions and Dependencies](#assumptions-and-dependencies)
6. [Project Risks](#project-risks)
7. [Architectural Design](#architectural-design)
8. [System Architecture](#system-architecture)
9. [Software Modules](#software-modules)
10. [High-Level Database Design](#high-level-database-design)
11. [Software Design](#software-design)
12. [Software Support Services](#software-support-services)
13. [Coding Structure and Convention](#coding-structure-and-convention)
14. [Software Configuration and Production Environment](#software-configuration-and-production-environment)
15. [Software Testing](#software-testing)

### Introduction

The "Pizza Ordering System" is designed for a pizza shop to provide online ordering services. This system allows both customers and managers to interact with the shop's operations effectively. Customers can place orders online, view order history and current order status, while managers can manage product listings, view orders, and access business-related reports. The goal is to streamline the ordering process and improve overall user experience.

### Aims of the Project

The project aims to facilitate different roles for customers and store managers through the web application. Customers can register, order pizzas, and track their orders, whereas managers have access to order details, pizza management, and business analytics.

### Project Scope

The scope of the project includes the integration of third-party payment methods and delivery services, which are not controlled by the system but are essential for the ordering process.

### User Characteristics

The primary users are online pizza orderers, typically aged between 15 to 35 years, and are mostly from first and second-tier cities. The system is user-friendly, catering to a wide range of users including those unfamiliar with complex web navigation.

### Assumptions and Dependencies

The project assumes that all team members possess the necessary skills and experience for web development. It depends on several external factors like development speed, software libraries, and the availability of external resources.

### Project Risks

The project faces risks in coding, collaboration, and legal, social, ethical, and professional domains. These include software tool compatibility, team coordination, data privacy, and security concerns.

### Architectural Design

Key features like pizza customization, order management, user authentication, and statistical reporting are implemented using technologies like MySQL, Java Persistence API (JPA), Java Database Connectivity (JDBC), Spring Boot, Spring MVC, and Thymeleaf.

### System Architecture

The system utilizes JPA for object-relational mapping, Spring Boot with Spring MVC for web services, and Thymeleaf for frontend templating, offering a robust and scalable solution.

### Software Modules

The system comprises modules for user management, pizza management, menu, shopping cart, order management, and statistical reporting, each with specific input, output, functions, and dependencies.

### High-Level Database Design

The database design includes entities like User, Customer, ShopManager, Pizza, ShoppingCart, and Order, structured to efficiently store and manage necessary data.

### Software Design

The design ensures user-friendly navigation through registration, login, menu selection, shopping cart management, and order processing.

### Software Support Services

The system includes database management, security features like verification codes and firewalls, and enhanced webpage navigation for an improved user experience.

### Coding Structure and Convention

The project adheres to standards in file naming, organization, Java source files, indentation, comments, declarations, statements, white space, and programming practices.

### Software Configuration and Production Environment
The development environment includes Spring Security, HTML, Java, CSS, IntelliJ IDEA, Visual Studio Code, and an Ali ECS Ubuntu server with MySQL database​​.

### Software Testing
Unit and integration testing methodologies are employed to ensure the reliability and correctness of individual components and their integration within the system​​.
