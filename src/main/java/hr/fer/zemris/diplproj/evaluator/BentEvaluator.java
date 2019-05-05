package hr.fer.zemris.diplproj.evaluator;

import hr.fer.zemris.diplproj.BoolFunction;

import java.util.Comparator;

/**
 * Implementation of an <code>IEvaluator</code> that returns the
 * 1 if the given boolean function if bent, calculated by its walsh coefficients.
 *
 * @author Kristijan Vulinovic
 */
public class BentEvaluator implements IEvaluator {
    @Override
    public double evaluate(BoolFunction function) {
        var maxW = function.getWalshSpectrum()
                .stream()
                .map(Math::abs)
                .max(Comparator.naturalOrder());

        var minW = function.getWalshSpectrum()
                .stream()
                .map(Math::abs)
                .min(Comparator.naturalOrder());

        if (!maxW.isPresent() || !minW.isPresent()) {
            throw new IllegalStateException("Unable to calculate nonlinearity due to missing walsh coefficients.");
        }

        int n = function.degree();
        if (minW.get().intValue() == maxW.get().intValue() && minW.get() == (1 << (n/2))){
            return 1;
        }
        return 0;
    }
}
