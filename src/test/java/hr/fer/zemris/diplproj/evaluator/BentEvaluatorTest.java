package hr.fer.zemris.diplproj.evaluator;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.walsh.FWT;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BentEvaluatorTest {
    @Test
    void testNotBentFunction1(){
        List<Integer> truthTable = Arrays.asList(0, 0, 1, 1, 1, 0, 1, 0);
        BoolFunction function = new BoolFunction(3, truthTable, new FWT());

        IEvaluator evaluator = new BentEvaluator();
        assertEquals(0.0, evaluator.evaluate(function));
    }

    @Test
    void testNotBentFunction2(){
        List<Integer> truthTable = Arrays.asList(0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1);
        BoolFunction function = new BoolFunction(4, truthTable, new FWT());

        IEvaluator evaluator = new BentEvaluator();
        assertEquals(0.0, evaluator.evaluate(function));
    }

    @Test
    void testBentFunction1(){
        List<Integer> truthTable = Arrays.asList(0, 1, 1, 1);
        BoolFunction function = new BoolFunction(2, truthTable, new FWT());

        IEvaluator evaluator = new BentEvaluator();
        assertEquals(1.0, evaluator.evaluate(function));
    }

    @Test
    void testBentFunction2(){
        List<Integer> truthTable = Arrays.asList(0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1);
        BoolFunction function = new BoolFunction(4, truthTable, new FWT());

        IEvaluator evaluator = new BentEvaluator();
        assertEquals(1.0, evaluator.evaluate(function));
    }
}
