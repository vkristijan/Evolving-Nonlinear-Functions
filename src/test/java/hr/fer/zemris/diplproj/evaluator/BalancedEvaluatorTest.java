package hr.fer.zemris.diplproj.evaluator;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.walsh.FWT;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalancedEvaluatorTest {
    @Test
    public void testBalancedFWT(){
        List<Integer> truthTable = Arrays.asList(0, 0, 1, 1, 1, 0, 1, 0);
        BoolFunction function = new BoolFunction(3, truthTable, new FWT());

        IEvaluator evaluator = new BalancedEvaluator();
        assertEquals(1.0, evaluator.evaluate(function));
    }

    @Test
    public void testNotBalancedFWT(){
        List<Integer> truthTable = Arrays.asList(1, 0, 1, 1, 1, 0, 1, 0);
        BoolFunction function = new BoolFunction(3, truthTable, new FWT());

        IEvaluator evaluator = new BalancedEvaluator();
        assertEquals(0.0, evaluator.evaluate(function));
    }

    @Test
    public void testBalanced(){
        List<Integer> truthTable = Arrays.asList(0, 0, 1, 1, 1, 0, 1, 0);
        BoolFunction function = new BoolFunction(3, truthTable);

        IEvaluator evaluator = new BalancedEvaluator();
        assertEquals(1.0, evaluator.evaluate(function));
    }

    @Test
    public void testNotBalanced(){
        List<Integer> truthTable = Arrays.asList(1, 0, 1, 1, 1, 0, 1, 0);
        BoolFunction function = new BoolFunction(3, truthTable);

        IEvaluator evaluator = new BalancedEvaluator();
        assertEquals(0.0, evaluator.evaluate(function));
    }
}
