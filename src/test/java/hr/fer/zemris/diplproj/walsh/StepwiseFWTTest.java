package hr.fer.zemris.diplproj.walsh;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class StepwiseFWTTest {
    @Test
    void testStepwiseFWT(){
        List<Integer> truthTable = Arrays.asList(0, 0, 1, 1, 1, 0, 1, 0);

        ITransform fwt = new StepwiseFWT();
        List<Integer> result = fwt.transform(truthTable);

        List<Integer> expected = Arrays.asList(0, 4, -4, 0, 0, -4, -4, 0);
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    void testStepwiseFWT2(){
        List<Integer> truthTable = Arrays.asList(1, 0, 0, 1, 1, 1, 0, 0);

        ITransform fwt = new StepwiseFWT();
        List<Integer> result = fwt.transform(truthTable);

        List<Integer> expected = Arrays.asList(0, 0, 4, 4, 0, 0, -4, 4);
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    void testStepwiseFWT3(){
        List<Integer> truthTable = Arrays.asList(0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0);

        ITransform fwt = new StepwiseFWT();
        List<Integer> result = fwt.transform(truthTable);

        List<Integer> expected = Arrays.asList(-6, -2, -2, 2, -2, -6, 2, -2, 2, -10, -2, 2, 6, 2, 2, -2);
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    void testStepwiseFWT4(){
        List<Integer> truthTable = Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0);

        ITransform fwt = new StepwiseFWT();
        List<Integer> result = fwt.transform(truthTable);

        List<Integer> expected = Arrays.asList(-2, -14, 2, -2, 2, -2, -2, 2, 2, -2, -2, 2, -2, 2, 2, -2);
        assertArrayEquals(expected.toArray(), result.toArray());
    }
}
