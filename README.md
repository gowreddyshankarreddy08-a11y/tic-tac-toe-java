# Tic-Tac-Toe — Java Web Game

A responsive two-player Tic-Tac-Toe game built with Java 17, Spring Boot, HTML, CSS, and JavaScript.

## Features
- Responsive 3×3 game board
- Alternating X and O turns
- Win and draw detection
- New Game button
- Java REST API backend
- Each browser session gets its own game board

## Requirements
- Java 17+
- Maven 3.6+

## Run locally
```bash
mvn spring-boot:run
```
Open http://localhost:8080 in your browser.

## Build a JAR
```bash
mvn clean package
java -jar target/tic-tac-toe-1.0.0.jar
```

## Deploy publicly
This project includes a `Dockerfile` for Docker-based hosting (for example, a Docker web service on Render).

1. Push this repository to GitHub.
2. In your hosting provider, create a new Web Service from the GitHub repository.
3. Choose Docker as the runtime/environment.
4. Deploy and wait for the build to finish.
5. Open the public URL supplied by the hosting provider.

The app reads the `PORT` environment variable, falling back to port 8080 locally.

## Project structure
```text
src/main/java/com/example/tictactoe/
  TicTacToeApplication.java
  GameController.java
src/main/resources/static/
  index.html
pom.xml
Dockerfile
```

## License
Choose a license before accepting outside contributions. If you want others to reuse this project, consider adding an MIT LICENSE file.
