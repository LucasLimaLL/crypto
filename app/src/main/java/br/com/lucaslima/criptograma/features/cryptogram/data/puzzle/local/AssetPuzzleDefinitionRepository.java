package br.com.lucaslima.criptograma.features.cryptogram.data.puzzle.local;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

import br.com.lucaslima.criptograma.features.cryptogram.data.puzzle.PuzzleDefinition;
import br.com.lucaslima.criptograma.features.cryptogram.data.puzzle.PuzzleDefinitionRepository;

public class AssetPuzzleDefinitionRepository implements PuzzleDefinitionRepository {

    private final Context context;
    private final Gson gson = new Gson();
    private List<PuzzleDefinition> puzzlesCache;

    public AssetPuzzleDefinitionRepository(Context context) {
        this.context = context;
    }

    @Override
    public PuzzleDefinition getRandomPuzzleDefinition() {
        List<PuzzleDefinition> puzzles = getPuzzles();
        int randomIndex = (int) (Math.random() * puzzles.size());
        return puzzles.get(randomIndex);
    }

    @Override
    public Optional<PuzzleDefinition> getPuzzleDefinition(String id) {
        return getPuzzles().stream()
                .filter(puzzle -> puzzle.puzzleId().equals(id))
                .findFirst();
    }

    private List<PuzzleDefinition> getPuzzles() {
        if (puzzlesCache == null) {
            try (Reader reader = new InputStreamReader(context.getAssets().open("puzzles.json"))) {
                puzzlesCache = gson.fromJson(reader, new TypeToken<List<PuzzleDefinition>>() {}.getType());
            } catch (Exception e) {
                throw new RuntimeException("Failed to read puzzles.json", e);
            }
        }
        return puzzlesCache;
    }
}
