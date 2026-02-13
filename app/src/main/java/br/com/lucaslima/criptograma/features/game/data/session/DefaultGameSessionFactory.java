package br.com.lucaslima.criptograma.features.game.data.session;

import android.content.Context;

import java.util.UUID;

import br.com.lucaslima.criptograma.features.game.application.ports.GameSessionFactoryPort;
import br.com.lucaslima.criptograma.features.game.data.puzzle.PuzzleDefinition;
import br.com.lucaslima.criptograma.features.game.data.puzzle.PuzzleDefinitionRepository;
import br.com.lucaslima.criptograma.features.game.data.puzzle.local.AssetPuzzleDefinitionRepository;
import br.com.lucaslima.criptograma.features.game.data.puzzle.mapper.PuzzleDomainMapper;
import br.com.lucaslima.criptograma.features.game.domain.GameSession;
import br.com.lucaslima.criptograma.features.game.domain.GameState;
import br.com.lucaslima.criptograma.features.game.domain.Puzzle;
import br.com.lucaslima.criptograma.features.game.domain.Template;

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
