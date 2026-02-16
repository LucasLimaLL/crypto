package br.com.lucaslima.criptograma.features.cryptogram.domain;

public final class GameRun {

    private final GameSession session;
    private final UndoRedoStack history;

    public GameRun(GameSession session, UndoRedoStack history) {
        validateSession(session);
        validateHistory(history);

        this.session = session;
        this.history = history;
    }

    public GameSession session() { return session; }
    public UndoRedoStack history() { return history; }

    public GameRun withSession(GameSession newSession) {
        validateSession(newSession);
        return new GameRun(newSession, history);
    }

    private static void validateSession(GameSession session) {
        if (session == null) {
            throw new IllegalArgumentException("session must not be null");
        }
    }

    private static void validateHistory(UndoRedoStack history) {
        if (history == null) {
            throw new IllegalArgumentException("history must not be null");
        }
    }
}
