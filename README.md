# Airline Reservation System (Java Swing)

A desktop-based Airline Reservation System developed in Java using Swing and NetBeans GUI Builder. The project demonstrates Object-Oriented Programming (OOP) concepts through a complete airline ticket booking and management system with role-based access for administrators and passengers.

## Features

### Admin Module

* Secure admin login
* Add new flights
* Update flight details
* Delete flights
* Manage seat availability
* View booking information

### Passenger Module

* User registration and login
* Search flights by source, destination, and date
* Book airline tickets
* Multiple seat classes:

  * First Class
  * Business Class
  * Economy Class
* Secure card payment validation
* View booking history
* Cancel bookings
* Automatic seat restoration after cancellation
* Ticket generation and storage

## Technologies Used

* Java
* Java Swing
* NetBeans IDE
* Object-Oriented Programming
* File Handling (.txt files)

## OOP Concepts Implemented

### Inheritance

* `Passenger` and `Admin` inherit from the abstract `Person` class.

### Abstraction

* Implemented through the `Person` abstract class and interfaces.

### Encapsulation

* Flight data and seat inventories are protected through controlled methods.

### Polymorphism

* Pricing strategies are implemented using the `PricingStrategy` interface.

### Exception Handling

* Custom exceptions for validation and booking operations.

## Project Structure

### Core Classes

* Person (Abstract Class)
* Passenger
* Admin
* Flight
* Booking
* DataManager
* FlightHandler
* BookingHandler
* CardPayment

### Interfaces

* Storable
* Validator
* Payment
* PricingStrategy

### Custom Exceptions

* InvalidInputException
* BookingException

## GUI Screens

### Admin Screens

* AdminLoginPage
* AdminDashboard
* AddFlightsPage
* ManageFlightsPage

### Passenger Screens

* UserLoginPage
* RegisterUserPage
* UserDashboard
* SearchFlightsPage
* BookingPage
* PaymentPage
* TicketSummaryPage
* MyBookingsPage
* CancelTicketPage

## Data Storage

The application uses text files for persistent storage:

* admins.txt
* passengers.txt
* flights.txt
* tickets.txt

## Fare Calculation

| Seat Class  | Multiplier |
| ----------- | ---------- |
| Economy     | 1.0x       |
| Business    | 1.5x       |
| First Class | 2.0x       |

## Payment Validation

The system validates:

* Card Number Format
* Expiry Date (MM/YY)
* Payment Information

## How to Run

1. Clone the repository

```bash
git clone https://github.com/ashir-devops/Airline-Ticketing-System.git
```

2. Open the project in NetBeans or any Java IDE.

3. Compile and run:

```bash
AirlineSystem.java
```

4. Use the GUI to access Admin or Passenger functionalities.

## Learning Outcomes

This project demonstrates:

* Object-Oriented Programming
* GUI Development with Java Swing
* File Handling
* Exception Handling
* Design Patterns (Strategy Pattern)
* Role-Based Access Control
* Real-World System Modeling


## Academic Project

Developed as a Lab Terminal Project for:

**CSC-241 Object Oriented Programming**
COMSATS University Islamabad
