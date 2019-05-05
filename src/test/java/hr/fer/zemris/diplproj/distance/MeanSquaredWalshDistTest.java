package hr.fer.zemris.diplproj.distance;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.walsh.FWT;
import hr.fer.zemris.diplproj.walsh.ITransform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MeanSquaredWalshDistTest {
    private static double DELTA = 0.01;

    private IDistance distance;
    private ITransform transform;

    @BeforeEach
    void prepare(){
        distance = new MeanSquaredWalshDist();
        transform = new FWT();
    }

    @Test
    void testMeanSquaredWalshDist1(){
        BoolFunction f1 = new BoolFunction(3, List.of(0, 1, 0, 1, 0, 1, 1, 1, 0));
        BoolFunction f2 = new BoolFunction(3, List.of(0, 1, 0, 1, 0, 1, 1, 1, 0));
        f1.setWalshTransform(transform);
        f2.setWalshTransform(transform);

        double result = distance.calculateDistance(f1, f2);
        assertEquals(0.0, result);
    }

    @Test
    void testMeanSquaredWalshDist2(){
        BoolFunction f1 = new BoolFunction(3, List.of(0, 1, 0, 1, 0, 1, 1, 1, 0));
        BoolFunction f2 = new BoolFunction(3, List.of(0, 1, 1, 1, 0, 1, 1, 1, 0));
        f1.setWalshTransform(transform);
        f2.setWalshTransform(transform);

        double result = distance.calculateDistance(f1, f2);
        assertEquals(3.55, result, DELTA);
    }

    @Test
    void testMeanSquaredWalshDist3(){
        BoolFunction f1 = new BoolFunction(2, List.of(0, 1, 1, 1, 0));
        BoolFunction f2 = new BoolFunction(2, List.of(1, 0, 1, 1, 0));
        f1.setWalshTransform(transform);
        f2.setWalshTransform(transform);

        double result = distance.calculateDistance(f1, f2);
        assertEquals(6.40, result, DELTA);
    }

    @Test
    void testMeanSquaredWalshDist4(){
        BoolFunction f1 = new BoolFunction(4, List.of(0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0));
        BoolFunction f2 = new BoolFunction(4, List.of(0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0));
        f1.setWalshTransform(transform);
        f2.setWalshTransform(transform);

        double result = distance.calculateDistance(f1, f2);
        assertEquals(15.06, result, DELTA);
    }
}
