package br.com.lucaslima.criptograma.features.game.data;

import java.util.List;
import java.util.Random;

import br.com.lucaslima.criptograma.features.game.domain.Puzzle;
import br.com.lucaslima.criptograma.features.game.domain.Word;

public final class FakePuzzleRepository implements PuzzleRepository {

    @Override
    public Puzzle loadRandomPuzzle() {
        return new Puzzle(
                "puzzle0001",
                List.of(
                        new Word("00001", "Que destrói as bactérias.", List.of()),
                        new Word("00002", "Omisso em reverência", List.of())));
    }
}
