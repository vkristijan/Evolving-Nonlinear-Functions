package hr.fer.zemris.diplproj.heuristic.ga.crossover;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SinglePointCrossover implements ICrossover {
    @Override
    public BoolFunction crossover(BoolFunction parent1, BoolFunction parent2) {
        Random rnd = Config.getInstance().getRnd();

        var t1 = parent1.getTruthTable();
        var t2 = parent2.getTruthTable();
        int n = t1.size();

        int index = rnd.nextInt(n);
        List<Integer> childTable = new ArrayList<>();
        for (int i = 0; i < n; ++i){
            if (i < index){
                childTable.add(t1.get(i));
            } else {
                childTable.add(t2.get(i));
            }
        }

        BoolFunction child = new BoolFunction(parent1.degree(), childTable);
        child.setWalshTransform(parent1.getWalshTransform());
        return child;
    }
}
