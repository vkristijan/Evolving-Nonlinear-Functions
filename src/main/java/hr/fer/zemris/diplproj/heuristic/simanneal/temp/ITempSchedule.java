package hr.fer.zemris.diplproj.heuristic.simanneal.temp;

/**
 * Interface defining a temperature schedule algorithm for simulated annealing.
 *
 * @author Kristijan Vulinovic
 */
public interface ITempSchedule {
    /**
     * Returns the next temperature to be used by the optimization process.
     *
     * @return the next temperature.
     */
    double getNextTemperature();

    /**
     * Returns the number of iterations for the inner loop.
     *
     * @return the number of iterations for the inner loop.
     */
    int getInnerLoopCounter();

    /**
     * Returns the number of iterations for the outer loop.
     *
     * @return the number of iterations for the outer loop.
     */
    int getOuterLoopCounter();
}
