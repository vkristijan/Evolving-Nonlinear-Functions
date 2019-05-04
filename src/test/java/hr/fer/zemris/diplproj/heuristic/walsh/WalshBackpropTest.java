package hr.fer.zemris.diplproj.heuristic.walsh;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.walsh.StepwiseFWT;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class WalshBackpropTest {
    @Test
    public void testBackpropBits1(){
        List<Integer> table = List.of(0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1);
        BoolFunction f = new BoolFunction(4, table);
        f.setWalshTransform(new StepwiseFWT());

        List<Integer> result = WalshBackprop.getBits(f);
        List<Integer> expected = List.of(2);
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void testBackpropBits2(){
        List<Integer> table = List.of(0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1);
        BoolFunction f = new BoolFunction(4, table);
        f.setWalshTransform(new StepwiseFWT());

        List<Integer> result = WalshBackprop.getBits(f);
        List<Integer> expected = List.of(0, 2, 5, 7, 8, 10, 13, 15);
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void testBackpropBits3(){
        List<Integer> table = List.of(0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0);
        BoolFunction f = new BoolFunction(4, table);
        f.setWalshTransform(new StepwiseFWT());

        List<Integer> result = WalshBackprop.getBits(f);
        List<Integer> expected = List.of(0, 1, 2, 3, 4, 6, 7, 9, 11, 12, 13, 14, 15);
        assertArrayEquals(expected.toArray(), result.toArray());
    }

    @Test
    public void testBackpropBits4(){
        List<Integer> table = List.of(0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0);
        BoolFunction f = new BoolFunction(4, table);
        f.setWalshTransform(new StepwiseFWT());

        List<Integer> result = WalshBackprop.getBits(f);
        List<Integer> expected = List.of();
        assertArrayEquals(expected.toArray(), result.toArray());
    }
}
