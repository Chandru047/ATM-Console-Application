# Banking Transaction System

## Overview
A comprehensive banking system designed to handle accounts, transactions, and denomination management. The system provides functionality for both administrators and customers to manage accounts and perform various financial transactions.

## System Architecture Diagram



## Core Components

### Main Module
- System initialization
- Application entry point
- Core operation management

### ATM Module
- Static double bankBalance management
- ArrayList initialization for denominations
- New denomination array creation and management
- Account customer management

### Account Management
- Account information storage (id, pass)
- Transaction history tracking
- Balance management
- Customer and Admin specific operations

## Modules

### Administrative Operations (AdminActions)
- Account login management
- Account addition capabilities
- Balance management
- Deposit handling
- Transaction tracking
- Transaction history management

### Customer Operations (CustomerActions)
- Account login
- Balance checking
- Withdrawal management
- Deposit operations
- Transaction history viewing
- Personal balance management

### Transaction Management
- Transaction type tracking
- Amount management
- Transaction history storage
- Balance updates

## Data Models

### Account POJO
- Private String id
- Private String pass
- ArrayList<Transaction> tracking
- Transaction history management

### Notes Management
- Denomination handling:
  - Two Thousand (₹2000)
  - Five Hundred (₹500)
  - Two Hundred (₹200)
  - Hundred (₹100)
- Note count tracking
- Total amount calculation

## Technical Specifications

### Class Structure
```java
Main
└── main()

ATM
└── bankBalance
└── ArrayList<Denomination>

Account
└── private String id
└── private String pass
└── ArrayList<Transaction>

AdminPOJO
└── private static final double PIN

CustomerPOJO
└── private String balance
```

## Features
- Real-time balance management
- Multiple denomination support
- Secure transaction processing
- Admin and Customer role separation
- Transaction history tracking
- Balance maintenance

## Getting Started

1. Initialize the system through Main
2. Set up initial bank balance
3. Configure denominations
4. Create admin accounts
5. Enable customer account creation
6. Begin transaction processing

## Usage Examples

### For Administrators
```java
// Admin operations
void addAccount()
void deleteAccount()
void viewTransactions()
void addTransaction()
```

### For Customers
```java
// Customer operations
void checkBalance()
void withdrawAmount()
void depositAmount()
void viewTransactions()
```

## Security Features
- PIN protection
- Separate admin and customer access
- Transaction logging
- Balance verification
- Denomination tracking

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License
This project is licensed under the MIT License - see the LICENSE file for details.
