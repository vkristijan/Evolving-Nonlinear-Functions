package hr.fer.zemris.diplproj.distance;

import hr.fer.zemris.diplproj.BoolFunction;

/**
 * Calculates the distance between two {@link BoolFunction} by calculating the
 * mean squared distance between the Walsh coefficients of the functions.
 *
 * @author Kristijan Vulinovic
 */
public class MeanSquaredWalshDist implements IDistance {
    @Override
    public double calculateDistance(BoolFunction f1, BoolFunction f2) {
        if (f1.degree() != f2.degree()){
            throw new IllegalArgumentException("The given two functions are not of same degree!");
        }

        var w1 = f1.getWalshSpectrum();
        var w2 = f2.getWalshSpectrum();

        double dist = 0;

        int n = w1.size();
        for (int i = 0; i < n; ++i){
            double tmpDist = w1.get(i) - w2.get(i);
            dist += tmpDist * tmpDist;
        }

        return dist / n;
    }
}
