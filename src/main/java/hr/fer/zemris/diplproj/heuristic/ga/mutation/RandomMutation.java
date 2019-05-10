package hr.fer.zemris.diplproj.heuristic.ga.mutation;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;

import java.util.Random;

public class RandomMutation implements IMutation {
    @Override
    public void mutate(BoolFunction f) {
        Random rnd = Config.getInstance().getRnd();

        var table = f.getTruthTable();
        int n = table.size();

        int index = rnd.nextInt(n);
        int oldValue = table.get(index);
        table.set(index, 1-oldValue);
    }
}
