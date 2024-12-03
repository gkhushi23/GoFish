Go Fish Game 🎴

A command-line implementation of the classic card game Go Fish, written in Java. This project showcases object-oriented programming (OOP) concepts like encapsulation, inheritance, and polymorphism, making it a great example for learning and practicing Java development.

Introduction

The Go Fish card game is a simple yet strategic game where players aim to collect sets of four cards of the same rank. This project simulates a two-player version of the game, focusing on the core gameplay mechanics.


Features

Two-player gameplay.
Card deck initialization with shuffling.
Players can:
Ask for a card rank from the opponent.
Draw a card if the opponent doesn't have the requested rank.
Complete sets and track scores.
Dynamic turn-based gameplay with automatic winner determination.
How to Play
Each player is dealt 7 cards at the start.
Players take turns asking the opponent for a specific card rank.
If the opponent has the requested rank:
The opponent gives all cards of that rank to the requesting player.
If the opponent does not have the requested rank:
The player must "Go Fish" and draw a card from the deck.
Players complete sets by collecting four cards of the same rank.
The game ends when:
The deck is empty, or
All sets are completed.
The player with the most completed sets wins!


Setup and Installation

Prerequisites

Java Development Kit (JDK) 8 or higher
A Java IDE (e.g., IntelliJ IDEA, Eclipse, or VS Code) or a terminal for compilation.

Installation

Clone the repository:
bash
Copy code
git clone https://github.com/your-username/go-fish-game.git
cd go-fish-game
Open the project in your Java IDE or navigate to the folder in your terminal.
How to Run
Compile the Java files:

bash
Copy code
javac GoFish.java
Run the program:

bash
Copy code
java GoFish
Follow the on-screen instructions to play the game.


Project Structure

bash
Copy code
├── Card.java       # Represents a single card in the game
├── Deck.java       # Represents the deck of cards
├── Player.java     # Represents a player in the game
├── Game.java       # Manages the game logic and flow
└── GoFish.java     # Entry point to the game
