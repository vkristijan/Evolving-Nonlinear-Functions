package hr.fer.zemris.diplproj.evaluator;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.walsh.FWT;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NonlinearityEvaluatorTest {
    @Test
    void testFunctionNonlinearity1(){
        List<Integer> truthTable = Arrays.asList(0, 0, 1, 1, 1, 0, 1, 0);
        BoolFunction function = new BoolFunction(3, truthTable, new FWT());

        IEvaluator evaluator = new NonlinearityEvaluator();
        assertEquals(2.0, evaluator.evaluate(function));
    }

    @Test
    void testFunctionNonlinearity2(){
        List<Integer> truthTable = Arrays.asList(0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1);
        BoolFunction function = new BoolFunction(4, truthTable, new FWT());

        IEvaluator evaluator = new NonlinearityEvaluator();
        assertEquals(4.0, evaluator.evaluate(function));
    }

    @Test
    void testFunctionNonlinearity3(){
        List<Integer> truthTable = Arrays.asList(0, 1, 1, 1);
        BoolFunction function = new BoolFunction(2, truthTable, new FWT());

        IEvaluator evaluator = new NonlinearityEvaluator();
        assertEquals(1.0, evaluator.evaluate(function));
    }

    @Test
    void testFunctionNonlinearity4(){
        List<Integer> truthTable = Arrays.asList(0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1);
        BoolFunction function = new BoolFunction(4, truthTable, new FWT());

        IEvaluator evaluator = new NonlinearityEvaluator();
        assertEquals(6.0, evaluator.evaluate(function));
    }

    @Test
    void testFunctionNonlinearity5(){
        List<Integer> truthTable = Arrays.asList(0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1);
        BoolFunction function = new BoolFunction(4, truthTable, new FWT());

        IEvaluator evaluator = new NonlinearityEvaluator();
        assertEquals(5.0, evaluator.evaluate(function));
    }

    @Test
    void testFunctionNonlinearity6(){
        List<Integer> truthTable = Arrays.asList(0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0);
        BoolFunction function = new BoolFunction(4, truthTable, new FWT());

        IEvaluator evaluator = new NonlinearityEvaluator();
        assertEquals(3.0, evaluator.evaluate(function));
    }

    @Test
    void testFunctionNonlinearity7(){
        List<Integer> truthTable = Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1);
        BoolFunction function = new BoolFunction(4, truthTable, new FWT());

        IEvaluator evaluator = new NonlinearityEvaluator();
        assertEquals(2.0, evaluator.evaluate(function));
    }

    @Test
    void testFunctionNonlinearity8(){
        List<Integer> truthTable = Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0);
        BoolFunction function = new BoolFunction(4, truthTable, new FWT());

        IEvaluator evaluator = new NonlinearityEvaluator();
        assertEquals(1.0, evaluator.evaluate(function));
    }

    @Test
    void testFunctionNonlinearity9(){
        List<Integer> truthTable = Arrays.asList(0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0);
        BoolFunction function = new BoolFunction(4, truthTable, new FWT());

        IEvaluator evaluator = new NonlinearityEvaluator();
        assertEquals(0.0, evaluator.evaluate(function));
    }
}
