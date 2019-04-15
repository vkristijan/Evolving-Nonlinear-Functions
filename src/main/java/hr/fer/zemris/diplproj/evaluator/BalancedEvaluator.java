package hr.fer.zemris.diplproj.evaluator;

import hr.fer.zemris.diplproj.BoolFunction;

/**
 * Implementation of an <code>IEvaluator</code> that returns 1 if the
 * given function is balanced and 0 otherwise.
 *
 * @author Kristijan Vulinovic
 */
public class BalancedEvaluator implements IEvaluator {
    @Override
    public double evaluate(BoolFunction function) {
        try {
            int w0 = function.getWalshSpectrum().get(0);
            return w0 == 0 ? 1 : 0;

        } catch (IllegalStateException e){
            int oneCount = function.getTruthTable().stream().mapToInt(Integer::intValue).sum();
            int count = function.getTruthTable().size();

            if (2*oneCount == count) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
