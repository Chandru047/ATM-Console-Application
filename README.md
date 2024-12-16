# Java Calculator Program

This is a simple console-based calculator application written in Java. It supports basic arithmetic operations like addition, subtraction, multiplication, division, power, and modulo. The user can choose to perform operations with either 2 or 3 operands.

## Features

- **Addition (`+`)**: Adds two or three numbers.
- **Subtraction (`-`)**: Subtracts two or three numbers.
- **Multiplication (`*`)**: Multiplies two or three numbers.
- **Division (`/`)**: Divides two numbers and handles division by zero gracefully.
- **Power (`pow`)**: Calculates the power of a number (base raised to the exponent).
- **Modulo (`mod`)**: Calculates the remainder of the division of two numbers.
- **Result Checker**: The program checks the result of the operation and prints it as:
  - An integer if it is a whole number within the integer range.
  - A `long` if the result exceeds the integer range.
  - A floating-point number if it is a non-whole number.

## Requirements

- Java 8 or higher.

## How to Use

1. Clone or download the repository to your local machine.
2. Open a terminal or command prompt.
3. Navigate to the directory containing the `Calculator.java` and `Main.java` files.
4. Compile the Java files using the following command:

   ```bash
   javac Main.java Calculator.java
5. Run the Main class to start the calculator program:

   ```bash
   java Main
