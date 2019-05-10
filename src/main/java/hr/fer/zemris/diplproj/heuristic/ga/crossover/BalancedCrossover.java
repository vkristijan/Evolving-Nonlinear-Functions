package hr.fer.zemris.diplproj.heuristic.ga.crossover;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BalancedCrossover implements ICrossover {
    @Override
    public BoolFunction crossover(BoolFunction parent1, BoolFunction parent2) {
        Random rnd = Config.getInstance().getRnd();
        int x = rnd.nextInt(2);

        var t1 = parent1.getTruthTable();
        var t2 = parent2.getTruthTable();
        int n = t1.size();

        List<Integer> table = new ArrayList<>();
        for (int i = 0; i < n; ++i){
            if (t1.get(i).intValue() == t2.get(i).intValue()){
                table.add(t1.get(i));
            } else {
                table.add(x);
                x = 1 - x;
            }
        }

        BoolFunction child = new BoolFunction(parent1.degree(), table);
        child.setWalshTransform(parent1.getWalshTransform());
        return child;
    }
}
