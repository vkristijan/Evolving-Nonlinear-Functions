package hr.fer.zemris.diplproj.evaluator;

import hr.fer.zemris.diplproj.BoolFunction;

/**
 * Interface modeling a single evaluator function used on boolean functions.
 *
 * @author Kristijan Vulinovic
 */
public interface IEvaluator {
    /**
     * Calculates the fitness of the given function.
     *
     * @param function a boolean function
     * @return the value of it's fitness
     */
    double evaluate(BoolFunction function);
}
