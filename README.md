# Checkers Game Coding Exercise

## Overview
This project is a coding exercise that automates gameplay and validation for the **Checkers game** (hosted at [gamesforthebrain.com](https://www.gamesforthebrain.com/game/checkers/)) using **Java**, **Selenium WebDriver**, and **TestNG**.

The exercise validates a sequence of moves to ensure the game board updates correctly.

---

## Scenarios Covered
1. Navigate to the Checkers game page.  
2. Confirm the site loads and the game board is displayed.  
3. Perform **5 valid moves** for the Orange player (`you1.gif`).  
4. Ensure at least **one capture move** is made.  
5. Verify game board updates after each move.  
6. Log success/failure after each move.  

---

## Tech Stack
- **Java 17+**
- **Maven**
- **Selenium WebDriver**
- **TestNG**

---

## Project Structure
checkers-game-coding-exercise/
├── pom.xml # Maven dependencies and build config
├── src/main/java/ # Utility classes (if any)
├── src/test/java/ # Test classes
│ └── tests/CheckersTest.java
└── README.md # Project documentation

---

## Running the Tests
1. Clone the repo:
   ```bash
   git clone https://github.com/<your-username>/checkers-game-coding-exercise.git
   cd checkers-game-coding-exercise
2. Run with Maven: 
   mvn clean test
3. TestNG will execute the scenarios and report results in the console.

Notes:
The exercise focuses on automation strategy and functional validation rather than building a full checkers AI.
Each test step has logging for clarity during execution.

Author:
Sudheer Chaitanya Jandhyala
Test Automation Lead

