package hr.fer.zemris.diplproj.heuristic.common.neighborhood;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.heuristic.walsh.WalshBackprop;

import java.util.*;

public class WalshNeighbor implements INeighbor {
    private static int counter;
    private static Set<Integer> visitedFunctions = new HashSet<>();


    @Override
    public BoolFunction getNeighbor(BoolFunction f) {
        Random rnd = Config.getInstance().getRnd();
        int n = f.getTruthTable().size();

        var bitsForChange = WalshBackprop.getBits(f);

        int bitIndex = -1;
        BoolFunction newF = null;
        while (bitIndex < 0) {
            counter++;
            if (counter % 1000 == 0){
                visitedFunctions = new HashSet<>();
            }
            if (bitsForChange.size() > 0) {
                int index = Config.getInstance().getRnd().nextInt(bitsForChange.size());
                bitIndex = bitsForChange.get(index);
                bitsForChange.remove(index);
            } else {
                bitIndex = Config.getInstance().getRnd().nextInt(f.getTruthTable().size());
            }

            List<Integer> table = new ArrayList<>(f.getTruthTable());
            int oldValue = table.get(bitIndex);
            table.set(bitIndex, 1 - oldValue);

            newF = new BoolFunction(f.degree(), table);
            newF.setWalshTransform(f.getWalshTransform());

            if (visitedFunctions.contains(newF.hashCode())) {
                bitIndex = -1;
            }
        }
        visitedFunctions.add(newF.hashCode());
        return newF;
    }
}
