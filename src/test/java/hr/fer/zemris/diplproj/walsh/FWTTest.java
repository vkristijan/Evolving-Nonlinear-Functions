package hr.fer.zemris.diplproj.walsh;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class FWTTest {
    @Test
    public void testFWT(){
        List<Integer> truthTable = Arrays.asList(0, 0, 1, 1, 1, 0, 1, 0);

        ITransform fwt = new FWT();
        List<Integer> result = fwt.transform(truthTable);

        List<Integer> expected = Arrays.asList(0, 4, -4, 0, 0, -4, -4, 0);
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void testFWT2(){
        List<Integer> truthTable = Arrays.asList(1, 0, 0, 1, 1, 1, 0, 0);

        ITransform fwt = new FWT();
        List<Integer> result = fwt.transform(truthTable);

        List<Integer> expected = Arrays.asList(0, 0, 4, 4, 0, 0, -4, 4);
        assertArrayEquals(expected.toArray(), result.toArray());
    }
}
