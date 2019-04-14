package hr.fer.zemris.diplproj.walsh;

import java.util.List;

/**
 * Basic interface for the Walsh-Hadamard transform.
 *
 * TODO: It could be slow to create a new array for the result, can be solved by reusing an existing one
 *
 * @author Kristijan Vulinovic
 */
public interface ITransform {
    /**
     * Calculates the Walsh-Hadamard transform for the given truth table values.
     *
     * @param values The values of a truth table of a boolean function.
     * @return Coefficients calculated using Walsh-Hadamard transform.
     */
    List<Integer> transform(List<Integer> values);
}
