package hr.fer.zemris.diplproj.heuristic.genprog.mutation;

import hr.fer.zemris.diplproj.heuristic.genprog.Solution;

/**
 * Interface used for mutation in genetic programming.
 *
 * @author Kristijan Vulinovic
 */
public interface IMutation {
    /**
     * Mutates the given solution.
     *
     * @param solution the solution to be mutated.
     * @return the mutated solution.
     */
    Solution mutate(Solution solution);
}
