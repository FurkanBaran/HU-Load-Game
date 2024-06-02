# HU-Load Game

HU-Load Game is a JavaFX-based mining game developed as an assignment. The player controls a drill machine to collect valuable minerals while managing fuel, money, and avoiding obstacles like boulders and lava. The game implements realistic gravity and movement mechanics to provide an engaging gameplay experience.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Game Rules](#game-rules)
- [Class Diagram and Checklist](#class-diagram-and-checklist)


## Introduction

In HU-Load Game, you navigate a drill machine through different layers of the earth, collecting minerals and gems. You need to manage your fuel levels and avoid running into obstacles such as boulders and lava blocks. The game is designed with realistic gravity mechanics, adding to the challenge and fun.

## Features

- **JavaFX-based GUI**: A user-friendly graphical interface using JavaFX.
- **Realistic Gravity**: The drill machine is affected by gravity, requiring strategic movement.
- **Resource Management**: Manage fuel, storage capacity, and money.
- **Various Tile Types**: Different types of minerals, boulders, and lava with unique properties.
- **Keyboard Controls**: Control the drill machine using arrow keys.

## Installation

1. **Clone the repository**:
   ```sh
   git clone https://github.com/FurkanBaran/HU-Load-Game.git
    ```
2. **Navigate to the project directory**:

    ```sh
    cd HU-Load-Game
    ```

3. Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
4. Ensure you have **Java 1.8 (412b)** set up in your IDE.
5. Ensure you have **JavaFX** set up in your IDE. You may need to configure your build path to include JavaFX libraries.

## Usage
Run the Main class from your IDE.
Use the arrow keys to navigate the drill machine.
Collect valuable minerals and avoid obstacles to achieve the highest score.d

## Game Rules
1. The drill machine can move left, right, and down using the arrow keys. It cannot drill upwards.
2. The machine consumes fuel over time and with movement.
3. Collect valuable minerals to increase your money and haul.
4. Avoid boulders and lava blocks; hitting lava results in game over.
5. The machine changes appearances depending on where it is facing.
6. Gravity affects the machine: if the block below is drilled, the machine falls one step down.
7. The game ends if the machine runs out of fuel or hits lava.

## Class Diagram and Checklist
The UML class diagram and project checklist are available in the [UML_Diagram.pdf](UML_Diagram.pdf) file.

