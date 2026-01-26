package br.com.lucaslima.criptograma.features.game.application;

import br.com.lucaslima.criptograma.features.game.data.PuzzleRepository;
import br.com.lucaslima.criptograma.features.game.domain.GameState;
import br.com.lucaslima.criptograma.features.game.domain.Puzzle;

public final class StartNewGameUseCase {

    private final PuzzleRepository puzzleRepository;

    public StartNewGameUseCase(PuzzleRepository puzzleRepository) {
        this.puzzleRepository = puzzleRepository;
    }

    public GameState execute() {
        Puzzle puzzle = puzzleRepository.loadRandomPuzzle();
        return new GameState(puzzle);
    }
}
