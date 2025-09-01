# Deck of Cards API Tests (Blackjack Variation)

This project demonstrates API testing using **RestAssured** and **Maven** with the [Deck of Cards API](https://deckofcardsapi.com/).  
The focus is on a simplified Blackjack variation where card values are calculated based on scoring rules.

---

## 🎯 Features
- Draw cards from the Deck of Cards API
- Calculate Blackjack scores:
  - 2–10 → face value
  - J, Q, K → 10 points
  - Ace → 11 or 1 (11 unless it causes bust > 21)
- Check if a player has exactly 21 points
- Implemented with **Java + RestAssured + TestNG**

---

## 🛠️ Tech Stack
- Java 17+
- Maven
- RestAssured
- TestNG

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/deck-of-cards-tests.git
   cd deck-of-cards-tests
2. Run tests with Maven
   bash - mvn clean test
3. Test reports are generated in the below folder
   bash - target/surefire-reports
