package br.com.lucaslima.criptograma.features.game.domain;

import java.util.List;

public final class Word {

    private final String code;
    private final String hint;
    private List<Cell> cells;

    public Word(String code, String hint, List<Cell> cells) {
        this.code = code;
        this.hint = hint;
        this.cells = cells;
    }

    public String getCode() {
        return code;
    }

    public String getHint() {
        return hint;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public boolean guessLetter(int number, char letter) {
        Cell cell = cells.get(number);
        cell.applyGuess(letter);

        if (!cell.isCorrect()) {
            cell.clearGuess();
            return false;
        }

        return true;
    }

    public boolean isSolved() {
        return cells.stream().allMatch(Cell::isCorrect);
    }

}
