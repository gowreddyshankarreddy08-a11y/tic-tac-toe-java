package com.example.tictactoe;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

@RestController
@SessionScope
@RequestMapping("/api/game")
public class GameController {
    private char[] board = new char[9];
    private char currentPlayer = 'X';
    private String status = "Player X's turn";
    private boolean gameOver = false;

    @GetMapping
    public synchronized GameState getGame() {
        return state();
    }

    @PostMapping("/move/{position}")
    public synchronized GameState move(@PathVariable int position) {
        if (position < 0 || position > 8) {
            throw new IllegalArgumentException("Position must be between 0 and 8.");
        }
        if (gameOver) return state();

        if (board[position] != '\0') {
            status = "That square is already taken. Try another.";
            return state();
        }

        board[position] = currentPlayer;

        if (hasWon(currentPlayer)) {
            status = "Player " + currentPlayer + " wins!";
            gameOver = true;
        } else if (isDraw()) {
            status = "It's a draw!";
            gameOver = true;
        } else {
            currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
            status = "Player " + currentPlayer + "'s turn";
        }
        return state();
    }

    @PostMapping("/reset")
    public synchronized GameState reset() {
        board = new char[9];
        currentPlayer = 'X';
        status = "Player X's turn";
        gameOver = false;
        return state();
    }

    private boolean hasWon(char p) {
        int[][] lines = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };
        for (int[] line : lines) {
            if (board[line[0]] == p && board[line[1]] == p && board[line[2]] == p) {
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (char cell : board) if (cell == '\0') return false;
        return true;
    }

    private GameState state() {
        String[] cells = new String[9];
        for (int i = 0; i < board.length; i++) {
            cells[i] = board[i] == '\0' ? "" : String.valueOf(board[i]);
        }
        return new GameState(cells, currentPlayer, status, gameOver);
    }

    public record GameState(String[] board, char currentPlayer, String status, boolean gameOver) {}
}
