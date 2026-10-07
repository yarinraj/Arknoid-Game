# Arkanoid Arcade Game 🎮

[![Java](https://img.shields.io/badge/Java-11%2B-orange.svg)](https://www.java.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Architecture](https://img.shields.io/badge/Architecture-OOP%20%26%20Event--Driven-green.svg)]()

A feature-complete, Object-Oriented implementation of the classic **Arkanoid** (Brick Breaker) arcade game built in Java. The project emphasizes clean code design, modular architecture, dynamic collision physics, and event-driven game logic.

---

## 📋 Table of Contents
- [About the Project](#about-the-project)
- [Key Features](#key-features)
- [Architecture & Design Patterns](#architecture--design-patterns)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation & Running](#installation--running)
- [Game Controls](#game-controls)
- [Author](#author)

---

## 📖 About the Project

**Arkanoid Arcade Game** is a modern Java desktop application that recreates the classic arcade experience while demonstrating core **Object-Oriented Programming (OOP)** principles and software design patterns. 

The game includes multi-level progression, real-time ball physics, precise collision detection, paddle control, and dynamic score/lives management. The underlying architecture strictly decouples rendering logic, user input, collision handling, and physics calculations into modular components for high maintainability and extensibility.

---

## ✨ Key Features

- **🎮 Core Game Mechanics:** Smooth paddle movement, realistic ball velocity/reflection physics, and precise collision detection with bricks and screen boundaries.
- **🧱 Multi-Level Gameplay:** Dynamic levels with varying brick layouts, hit-points, color themes, and progressive difficulty.
- **📊 Real-time Game State & HUD:** On-screen heads-up display tracking score, remaining lives, current level, alongside pause and victory/defeat screens.
- **⚡ Event-Driven Architecture:** Decoupled event listeners handling brick hits, score updates, and life tracking upon impact.
- **🎨 Custom Graphics Rendering:** Modular rendering pipeline utilizing clean interfaces for animated components (`Sprite`).

---

## 🏗️ Architecture & Design Patterns

The project follows high software engineering standards and object-oriented design principles:

- **Object-Oriented Programming (OOP):** Deep application of encapsulation, inheritance, polymorphism, and interface-driven abstractions.
- **Observer / Listener Pattern:** `HitListener` and `HitNotifier` interfaces decouple collision detection logic from score tracking, block removal, and life updates.
- **Sprite & Collidable Abstractions:** Visual game elements implement `Sprite`, while physical obstacles implement `Collidable`, managed independently within `SpriteCollection` and `GameEnvironment`.
- **Game Loop Synchronization:** Controlled frame-rate animation runner ensuring stable physics updates and visual rendering across different platforms.

---

## 🛠️ Tech Stack

- **Language:** Java 11+
- **Graphics & GUI:** Java AWT / Swing / GUI Library
- **Version Control:** Git & GitHub
- **IDE:** IntelliJ IDEA / Eclipse / VS Code

---

## 📂 Project Structure

```text
Arknoid-Game/
├── src/
│   ├── animation/       # Animation loop runner, screens, and overlays
│   ├── collisions/      # Collision detection algorithms, velocity, and line intersection
│   ├── game/            # Main game loop, game environment, and level flow management
│   ├── geometry/        # Fundamental geometric primitives (Point, Line, Rectangle)
│   ├── listeners/       # Event handlers, HitListeners, and ScoreTrackers
│   └── sprites/         # Game entities (Ball, Paddle, Block, ScoreIndicator, etc.)
├── bin/                 # Compiled Java bytecode
├── README.md            # Project documentation
└── .gitignore           # Git ignore file

```
🚀 Getting Started
Prerequisites
Ensure you have the following installed on your environment:

Java Development Kit (JDK 11 or higher)

Git

Installation & Running
1. Clone the repository:
   git clone [https://github.com/yarinraj/Arknoid-Game.git](https://github.com/yarinraj/Arknoid-Game.git)
   cd Arknoid-Game
2. Compile the source files:
   javac -d bin -srcpath src src/**/*.java
   (Alternatively, open the project directly in IntelliJ IDEA or Eclipse)
3. Launch the game:
   java -cp bin Main

🕹️ Game Controls
- **Move Paddle Left:** `Left Arrow (←)` / `A`
- **Move Paddle Right:** `Right Arrow (→)` / `D`
- **Pause / Resume:** `Spacebar` / `P`

👤 Author
Yarin Raj
GitHub: @yarinraj
LinkedIn: linkedin.com/in/yarinraj
