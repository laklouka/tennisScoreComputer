# Tennis Score Computer

A simple full-stack application to compute and display the score of a tennis game, following standard tennis rules.

## 🎯 Goal

The goal of this kata is to implement a tennis score calculator. The scoring logic follows the traditional rules of tennis, focusing on a single game (not a full set or match).

 Scoring Rules
Each player starts with 0 point.
- First ball won → 15 points
- Second ball won → 30 points
- Third ball won → 40 points
- If a player with 40 points wins the next ball and the opponent has less than 40 → the player wins the game.
- If both players reach 40 → deuce
- From deuce:
  * Winner of the next ball gets advantage
  * If the player with advantage wins again → wins the game
  * If the opponent wins → back to deuce
More details: Tennis Scoring on Wikipedia

## 🧱 Tech Stack

* Backend: Java with Spring Boot
* Frontend: React
* Messaging: Apache Kafka
* Containerization: Docker & Docker Compose

## 🚀 Getting Started

### Prerequisites
Make sure you have the following installed:
* Docker & Docker Compose
* Java 17+
* Node.js 18+ (for frontend)
  
### Run the application
* Clone the repository
```
git clone [URL_DU_REPO]
cd tennisScoreComputer
```

* Start all services (backend, frontend, Kafka)

```
docker-compose up --build
```

* Open the frontend in your browser:

```
http://localhost:3000
```

## ⚙️ Project Structure

* tennisGameBackend/       → Spring Boot service (score computation + Kafka integration)
* tennisGameFrontend/      → React app (UI to display and control the game)
* docker-compose.yaml
* README.md
