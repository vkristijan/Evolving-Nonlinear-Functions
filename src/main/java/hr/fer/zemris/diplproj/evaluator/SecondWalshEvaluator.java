package hr.fer.zemris.diplproj.evaluator;

import hr.fer.zemris.diplproj.BoolFunction;

import java.util.Comparator;

public class SecondWalshEvaluator implements IEvaluator {
    @Override
    public double evaluate(BoolFunction function) {
        var maxW = function.getWalshSpectrum()
                .stream()
                .map(Math::abs)
                .max(Comparator.naturalOrder());

        if (!maxW.isPresent()) {
            throw new IllegalStateException("Unable to calculate nonlinearity due to missing walsh coefficients.");
        }

        var secondW = function.getWalshSpectrum()
                .stream()
                .map(Math::abs)
                .filter(x -> x < maxW.get())
                .max(Comparator.naturalOrder());

        if (!secondW.isPresent() ){
            return 0;
        }
        return secondW.get();
    }
}
