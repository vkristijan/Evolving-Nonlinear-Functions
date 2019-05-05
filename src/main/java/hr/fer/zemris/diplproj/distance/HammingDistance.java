package hr.fer.zemris.diplproj.distance;

import hr.fer.zemris.diplproj.BoolFunction;

/**
 * Calculates the distance between two {@link BoolFunction} by calculating the Hamming distance
 * between their truth tables.
 *
 * @author Kristijan Vulinovic
 */
public class HammingDistance implements IDistance {
    @Override
    public double calculateDistance(BoolFunction f1, BoolFunction f2) {
        if (f1.degree() != f2.degree()){
            throw new IllegalArgumentException("The given two functions are not of same degree!");
        }

        double dist = 0;

        var table1 = f1.getTruthTable();
        var table2 = f2.getTruthTable();
        int n = table1.size();

        for (int i = 0; i < n; ++i){
            if (table1.get(i).intValue() != table2.get(i).intValue()){
                dist++;
            }
        }

        return dist;
    }
}
