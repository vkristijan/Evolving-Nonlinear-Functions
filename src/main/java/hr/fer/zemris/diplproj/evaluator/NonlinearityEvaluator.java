package hr.fer.zemris.diplproj.evaluator;

import hr.fer.zemris.diplproj.BoolFunction;

import java.util.Comparator;

/**
 * Implementation of an <code>IEvaluator</code> that returns the
 * nonlinearity of the given boolean function, calculated by its walsh coefficients.
 *
 * @author Kristijan Vulinovic
 */
public class NonlinearityEvaluator implements IEvaluator {
    @Override
    public double evaluate(BoolFunction function) {
        var maxW = function.getWalshSpectrum()
                           .stream()
                           .map(Math::abs)
                           .max(Comparator.naturalOrder());

        if (!maxW.isPresent()) {
            throw new IllegalStateException("Unable to calculate nonlinearity due to missing walsh coefficients.");
        }

        int n = function.degree();
        return (1 << (n - 1)) - maxW.get() / 2.0;
    }
}
