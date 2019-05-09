package hr.fer.zemris.diplproj.heuristic;

import hr.fer.zemris.diplproj.BoolFunction;

/**
 * Common interface defining a solver using a certain algorithm.
 *
 * @author Kristijan Vulinovic
 */
public interface ISolver {
    BoolFunction run();
}
