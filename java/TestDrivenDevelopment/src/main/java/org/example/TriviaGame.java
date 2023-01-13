package org.example;

public class TriviaGame {
    private Question[] questions;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    public TriviaGame() { }
    public TriviaGame(Question[] questions, Player playerOne, Player playerTwo, Player currentPlayer) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.questions = questions;
        this.currentPlayer = currentPlayer;
    }
    public void switchCurrentPlayer() {

    }

    public String getScoreBoard() {

    }

    public String getGameWinner() {

    }

    public void playTriviaGame() {

    }
}
