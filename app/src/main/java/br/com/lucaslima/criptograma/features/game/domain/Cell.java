package br.com.lucaslima.criptograma.features.game.domain;

public final class Cell {

    private final int number;
    private final char solutionLetter;
    private Character guess;

    public Cell(int number, char solutionLetter) {
        this.number = number;
        this.solutionLetter = solutionLetter;
    }

    public int getNumber() {
        return number;
    }

    public Character getGuess() {
        return guess;
    }

    public boolean isFilled() {
        return this.guess != null;
    }

    public void applyGuess(char guess) {
        this.guess = Character.toUpperCase(guess);
    }

    public void clearGuess() {
        this.guess = null;
    }

    public boolean isCorrect() {
        return this.guess != null && this.guess == this.solutionLetter;
    }
}
