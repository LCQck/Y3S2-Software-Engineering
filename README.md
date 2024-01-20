# Y3S2-Software-Engineering

# Pizza Ordering System

## Project Overview

**Semester:** 2022/2023 Semester 2  
**Course:** CPT202 Assignment 1  
**Group:** 24  
**Project Website:** [Pizza Ordering System](http://121.199.161.166:8080/pizzaOrderingSys/login)  
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

The "Pizza Ordering System" is designed for a pizza shop to provide online ordering services. This system allows both customers and managers to interact with the shop's operations effectively. Customers can place orders online, view order history and current order status, while managers can manage product listings, view orders, and access business-related reports. The goal is to streamline the ordering process and improve overall user experience&#8203;``【oaicite:12】``&#8203;.

### Aims of the Project

The project aims to facilitate different roles for customers and store managers through the web application. Customers can register, order pizzas, and track their orders, whereas managers have access to order details, pizza management, and business analytics&#8203;``【oaicite:11】``&#8203;.

### Project Scope

The scope of the project includes the integration of third-party payment methods and delivery services, which are not controlled by the system but are essential for the ordering process&#8203;``【oaicite:10】``&#8203;.

### User Characteristics

The primary users are online pizza orderers, typically aged between 15 to 35 years, and are mostly from first and second-tier cities. The system is user-friendly, catering to a wide range of users including those unfamiliar with complex web navigation&#8203;``【oaicite:9】``&#8203;.

### Assumptions and Dependencies

The project assumes that all team members possess the necessary skills and experience for web development. It depends on several external factors like development speed, software libraries, and the availability of external resources&#8203;``【oaicite:8】``&#8203;.

### Project Risks

The project faces risks in coding, collaboration, and legal, social, ethical, and professional domains. These include software tool compatibility, team coordination, data privacy, and security concerns&#8203;``【oaicite:7】``&#8203;.

### Architectural Design

Key features like pizza customization, order management, user authentication, and statistical reporting are implemented using technologies like MySQL, Java Persistence API (JPA), Java Database Connectivity (JDBC), Spring Boot, Spring MVC, and Thymeleaf&#8203;``【oaicite:6】``&#8203;.

### System Architecture

The system utilizes JPA for object-relational mapping, Spring Boot with Spring MVC for web services, and Thymeleaf for frontend templating, offering a robust and scalable solution&#8203;``【oaicite:5】``&#8203;.

### Software Modules

The system comprises modules for user management, pizza management, menu, shopping cart, order management, and statistical reporting, each with specific input, output, functions, and dependencies&#8203;``【oaicite:4】``&#8203;.

### High-Level Database Design

The database design includes entities like User, Customer, ShopManager, Pizza, ShoppingCart, and Order, structured to efficiently store and manage necessary data&#8203;``【oaicite:3】``&#8203;.

### Software Design

The design ensures user-friendly navigation through registration, login, menu selection, shopping cart management, and order processing&#8203;``【oaicite:2】``&#8203;.

### Software Support Services

The system includes database management, security features like verification codes and firewalls, and enhanced webpage navigation for an improved user experience&#8203;``【oaicite:1】``&#8203;.

### Coding Structure and Convention

The project adheres to standards in file naming, organization, Java source files, indentation, comments, declarations, statements, white space, and programming practices&#8203;``【oaicite:0】``&#8203;.

### Software Configuration and Production Environment
The development environment includes Spring Security, HTML, Java, CSS, IntelliJ IDEA, Visual Studio Code, and an Ali ECS Ubuntu server with MySQL database​​.

### Software Testing
Unit and integration testing methodologies are employed to ensure the reliability and correctness of individual components and their integration within the system​​.
