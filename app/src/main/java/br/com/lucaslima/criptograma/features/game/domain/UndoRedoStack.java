package br.com.lucaslima.criptograma.features.game.domain;

import java.util.ArrayDeque;
import java.util.Deque;

public final class UndoRedoStack {

    private final Deque<GameState> past = new ArrayDeque<>();
    private final Deque<GameState> future = new ArrayDeque<>();

    private final int maxHistorySize;

    public UndoRedoStack() {
        this(200);
    }

    public UndoRedoStack(int maxHistorySize) {
        validateMaxHistorySize(maxHistorySize);
        this.maxHistorySize = maxHistorySize;
    }

    public boolean canUndo() { return !past.isEmpty(); }
    public boolean canRedo() { return !future.isEmpty(); }

    public void pushPast(GameState state) {
        validateState(state);

        past.push(state);
        trimPastIfNeeded();
    }

    public GameState popPast() {
        validateCanUndo();
        return past.pop();
    }

    public void pushFuture(GameState state) {
        validateState(state);
        future.push(state);
    }

    public GameState popFuture() {
        validateCanRedo();
        return future.pop();
    }

    public void clearFuture() {
        future.clear();
    }

    public void clearAll() {
        past.clear();
        future.clear();
    }

    private static void validateState(GameState state) {
        if (state == null) {
            throw new IllegalArgumentException("state must not be null");
        }
    }

    private static void validateMaxHistorySize(int maxHistorySize) {
        if (maxHistorySize <= 0) {
            throw new IllegalArgumentException("maxHistorySize must be > 0");
        }
    }

    private void validateCanUndo() {
        if (!canUndo()) {
            throw new IllegalStateException("Cannot undo: past is empty");
        }
    }

    private void validateCanRedo() {
        if (!canRedo()) {
            throw new IllegalStateException("Cannot redo: future is empty");
        }
    }

    private void trimPastIfNeeded() {
        while (past.size() > maxHistorySize) {
            past.removeLast();
        }
    }
}
