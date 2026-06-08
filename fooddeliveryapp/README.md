# Craves - Food Delivery Application

Craves is a comprehensive full-stack food delivery application built with Spring Boot and React. It provides a robust backend for managing orders, a dynamic frontend for users to browse and place food orders, and secure payment and cart management functionality.

## Project Structure

The repository contains the following main components:

- **Backend**: Built with Java 23, Spring Boot 3, Spring Data JPA, and Spring Security. The application uses MySQL for database management.
- **Frontend**: A fast and responsive single-page application built using React, Vite, and React Router.

## Tech Stack

### Backend
- Java 23
- Spring Boot 3.5
- Spring Data JPA
- Spring Security
- MySQL
- Lombok

### Frontend
- React 19
- Vite
- React Router DOM
- Lucide React (for icons)

## Features

- **User Authentication**: Secure login and registration using Spring Security.
- **Menu Browsing**: View available food items and restaurant details.
- **Cart Management**: Add items to the cart, update quantities, and review orders.
- **Order Processing**: Seamless order placement and management.
- **Responsive UI**: A modern interface that works across devices.

## Getting Started

### Prerequisites
- Java 23 or higher
- Node.js (v18+ recommended)
- MySQL Server

### Backend Setup
1. Navigate to the `fooddeliveryapp` directory.
2. Configure your MySQL database credentials in `src/main/resources/application.properties`.
3. Run the application using your IDE or Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend Setup
1. Navigate to the `frontend` directory.
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm run dev
   ```

## License
This project is licensed under the MIT License.
