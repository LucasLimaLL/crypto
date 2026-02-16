package br.com.lucaslima.criptograma.features.cryptogram.data.session;

import android.content.Context;

import java.util.UUID;

import br.com.lucaslima.criptograma.features.cryptogram.application.ports.GameSessionFactoryPort;
import br.com.lucaslima.criptograma.features.cryptogram.data.puzzle.PuzzleDefinition;
import br.com.lucaslima.criptograma.features.cryptogram.data.puzzle.PuzzleDefinitionRepository;
import br.com.lucaslima.criptograma.features.cryptogram.data.puzzle.local.AssetPuzzleDefinitionRepository;
import br.com.lucaslima.criptograma.features.cryptogram.data.puzzle.mapper.PuzzleDomainMapper;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameSession;
import br.com.lucaslima.criptograma.features.cryptogram.domain.GameState;
import br.com.lucaslima.criptograma.features.cryptogram.domain.Puzzle;
import br.com.lucaslima.criptograma.features.cryptogram.domain.Template;

public final class DefaultGameSessionFactory implements GameSessionFactoryPort {

    private final PuzzleDefinitionRepository puzzleDefinitionRepository;
    private final PuzzleDomainMapper puzzleDomainMapper;

    public DefaultGameSessionFactory(Context context) {
        this(new AssetPuzzleDefinitionRepository(context));
    }

    public DefaultGameSessionFactory(PuzzleDefinitionRepository puzzleDefinitionRepository) {
        this.puzzleDefinitionRepository = puzzleDefinitionRepository;
        this.puzzleDomainMapper = new PuzzleDomainMapper();
    }

    @Override
    public GameSession createNewSession() {
        PuzzleDefinition puzzleDefinition = puzzleDefinitionRepository.getRandomPuzzleDefinition();
        Puzzle puzzle = puzzleDomainMapper.toDomain(puzzleDefinition);
        Template template = null;
        GameState startGame = GameState.empty();
        String sessionId = UUID.randomUUID().toString();
        return new GameSession(sessionId, puzzle, template, startGame);
    }
}
