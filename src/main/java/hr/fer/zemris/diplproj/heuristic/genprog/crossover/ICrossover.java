package hr.fer.zemris.diplproj.heuristic.genprog.crossover;

import hr.fer.zemris.diplproj.heuristic.genprog.Solution;

/**
 * Interface used for crossover in genetic programming.
 *
 * @author Kristijan Vulinovic
 */
public interface ICrossover {
    /**
     * Creates a new {@link Solution} by combining the two given solutions.
     *
     * @param a the first given solution.
     * @param b the second given solution.
     * @return the new created solution.
     */
    Solution crossover(Solution a, Solution b);
}
