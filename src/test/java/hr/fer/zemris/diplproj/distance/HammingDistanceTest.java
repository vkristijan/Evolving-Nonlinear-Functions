package hr.fer.zemris.diplproj.distance;

import hr.fer.zemris.diplproj.BoolFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HammingDistanceTest {
    private IDistance distance;

    @BeforeEach
    void prepare(){
        distance = new HammingDistance();
    }

    @Test
    void testHammingDistance1(){
        BoolFunction f1 = new BoolFunction(3, List.of(0, 1, 0, 1, 0, 1, 1, 1, 0));
        BoolFunction f2 = new BoolFunction(3, List.of(0, 1, 0, 1, 0, 1, 1, 1, 0));

        double result = distance.calculateDistance(f1, f2);
        assertEquals(0.0, result);
    }

    @Test
    void testHammingDistance2(){
        BoolFunction f1 = new BoolFunction(3, List.of(0, 1, 0, 1, 0, 1, 1, 1, 0));
        BoolFunction f2 = new BoolFunction(3, List.of(0, 1, 1, 1, 0, 1, 1, 1, 0));

        double result = distance.calculateDistance(f1, f2);
        assertEquals(1.0, result);
    }

    @Test
    void testHammingDistance3(){
        BoolFunction f1 = new BoolFunction(2, List.of(0, 1, 1, 1, 0));
        BoolFunction f2 = new BoolFunction(2, List.of(1, 0, 1, 1, 0));

        double result = distance.calculateDistance(f1, f2);
        assertEquals(2.0, result);
    }

    @Test
    void testHammingDistance4(){
        BoolFunction f1 = new BoolFunction(4, List.of(0, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0));
        BoolFunction f2 = new BoolFunction(4, List.of(0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0));

        double result = distance.calculateDistance(f1, f2);
        assertEquals(4.0, result);
    }
}
