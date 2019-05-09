package hr.fer.zemris.diplproj.heuristic.common.neighborhood;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.distance.HammingDistance;
import hr.fer.zemris.diplproj.distance.IDistance;
import hr.fer.zemris.diplproj.evaluator.BentEvaluator;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;
import hr.fer.zemris.diplproj.walsh.FWT;
import hr.fer.zemris.diplproj.walsh.ITransform;

import java.util.*;

/**
 * Generates a new neighbor by keeping a table of all the bits that need to be changed for
 * lower degree functions. Using that table, a sublist of the truth table is taken and
 * compared against the table to find which bits need to be changed for that part to become
 * bent.
 *
 * @author Kristijan Vulinovic
 */
public class TabulatedNeighbor implements INeighbor {
    /**
     * The degree of the functions to tabulate.
     */
    private int tabulateDegree;
    /**
     * The table of bits that need to be changed.
     */
    private Map<Integer, List<Integer>> bitsToChange;

    /**
     * Creates a new {@link TabulatedNeighbor} by tabulating the functions
     * of the given degree.
     *
     * @param tabulateDegree the degree of the tabulated functions.
     */
    public TabulatedNeighbor(int tabulateDegree){
        this.tabulateDegree = tabulateDegree;

        ITransform transform = new FWT();
        IEvaluator evaluator = new BentEvaluator();
        Map<Integer, Set<Integer>> mapOfChangeSets = new HashMap<>();

        List<BoolFunction> functions = BoolFunction.getFunctions(tabulateDegree);
        for (var function : functions) {
            function.setWalshTransform(transform);
        }

        IDistance distance = new HammingDistance();

        List<BoolFunction> bent = new ArrayList<>();
        for (BoolFunction function1 : functions) {
            if (evaluator.evaluate(function1) > 0) {
                bent.add(function1);
            }
            mapOfChangeSets.put(function1.hashCode(), new HashSet<>());
        }

        for (BoolFunction function : functions) {
            double minDst = Double.MAX_VALUE;

            for (BoolFunction b : bent) {
                double d = distance.calculateDistance(function, b);
                minDst = Math.min(minDst, d);
            }

            for (BoolFunction b : bent) {
                double d = distance.calculateDistance(function, b);
                if (d == minDst) {

                    for (int k = 0; k < function.getTruthTable().size(); ++k) {
                        if (function.getTruthTable().get(k).intValue() != b.getTruthTable().get(k)) {
                            mapOfChangeSets.get(function.hashCode()).add(k);
                        }
                    }
                }
            }
        }

        bitsToChange = new HashMap<>();
        for (var f : functions){
            int h = f.hashCode();
            bitsToChange.put(h, new ArrayList<>(mapOfChangeSets.get(h)));
        }
    }

    @Override
    public BoolFunction getNeighbor(BoolFunction f) {
        Random rnd = Config.getInstance().getRnd();
        int n = f.getTruthTable().size();
        int tabSize = 1 << tabulateDegree;
        int bound = n - tabSize + 1;

        List<Integer> table = new ArrayList<>(f.getTruthTable());
        int offset = 0;

        List<Integer> toChange = new ArrayList<>();
        while (toChange.size() == 0){   // picked a bent part of the function
            offset = rnd.nextInt(bound);

            BoolFunction functionPart = new BoolFunction(tabulateDegree, table.subList(offset, offset + tabSize));
            toChange = bitsToChange.get(functionPart.hashCode());
        }

        int index = rnd.nextInt(toChange.size());

        int oldVal = table.get(offset + toChange.get(index));
        table.set(offset + toChange.get(index), 1-oldVal);

        BoolFunction newF = new BoolFunction(f.degree(), table);
        newF.setWalshTransform(f.getWalshTransform());
        return newF;
    }
}
