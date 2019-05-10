package hr.fer.zemris.diplproj.heuristic.ga.mutation;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;

import java.util.Random;

public class BalancedMutation implements IMutation {
    @Override
    public void mutate(BoolFunction f) {
        Random rnd = Config.getInstance().getRnd();

        var table = f.getTruthTable();
        int n = table.size();
        int zeroIndex, oneIndex;

        int tmp = rnd.nextInt(n);
        while (table.get(tmp) != 0){
            tmp = rnd.nextInt(n);
        }
        zeroIndex = tmp;

        while (table.get(tmp) != 1){
            tmp = rnd.nextInt(n);
        }
        oneIndex = tmp;

        table.set(zeroIndex, 1);
        table.set(oneIndex, 0);
    }
}
