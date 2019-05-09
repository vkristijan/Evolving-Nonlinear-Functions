package hr.fer.zemris.diplproj.heuristic.common.neighborhood;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates a new neighbor by randomly changing a bit of the function's truth table.
 *
 * @author Kristijan Vulinovic
 */
public class RandomNeighbor implements INeighbor {
    @Override
    public BoolFunction getNeighbor(BoolFunction f) {
        Random rnd = Config.getInstance().getRnd();
        int n = f.getTruthTable().size();

        int index = rnd.nextInt(n);
        List<Integer> table = new ArrayList<>(f.getTruthTable());
        int oldValue = table.get(index);
        table.set(index, 1 - oldValue);

        BoolFunction newF = new BoolFunction(f.degree(), table);
        newF.setWalshTransform(f.getWalshTransform());

        return newF;
    }
}
