package br.com.lucaslima.criptograma.features.game.domain;

public final class GameSession {

    private final String sessionId;
    private final Puzzle puzzle;
    private final Template template;
    private final GameState state;

    public GameSession(String sessionId, Puzzle puzzle, Template template, GameState state) {
        validateSessionId(sessionId);
        validatePuzzle(puzzle);
        validateTemplate(template);
        validateState(state);

        this.sessionId = sessionId;
        this.puzzle = puzzle;
        this.template = template;
        this.state = state;
    }

    public String sessionId() { return sessionId; }
    public Puzzle puzzle() { return puzzle; }
    public Template template() { return template; }
    public GameState state() { return state; }

    public GameSession withState(GameState newState) {
        validateState(newState);
        return new GameSession(sessionId, puzzle, template, newState);
    }

    private static void validateSessionId(String sessionId) {
        if (sessionId == null || sessionId.isBlank()) {
            throw new IllegalArgumentException("sessionId must not be blank");
        }
    }

    private static void validatePuzzle(Puzzle puzzle) {
        if (puzzle == null) {
            throw new IllegalArgumentException("puzzle must not be null");
        }
    }

    private static void validateTemplate(Template template) {
        if (template == null) {
            throw new IllegalArgumentException("template must not be null");
        }
    }

    private static void validateState(GameState state) {
        if (state == null) {
            throw new IllegalArgumentException("state must not be null");
        }
    }
}
