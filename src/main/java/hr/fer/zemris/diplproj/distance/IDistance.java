package hr.fer.zemris.diplproj.distance;

import hr.fer.zemris.diplproj.BoolFunction;

/**
 * Interface modeling a single function to calculate the distance between two {@link BoolFunction}.
 *
 * @author Kristijan Vulinovic
 */
public interface IDistance {
    /**
     * Calculates the distance between two given {@link BoolFunction}.
     *
     * @param f1 the first function.
     * @param f2 the second function.
     * @return the distance between the given functions.
     */
    double calculateDistance(BoolFunction f1, BoolFunction f2);
}
