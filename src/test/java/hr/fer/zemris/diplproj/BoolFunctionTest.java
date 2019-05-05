package hr.fer.zemris.diplproj;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoolFunctionTest {
    @Test
    void testCreateZeroFunction(){
        BoolFunction function = BoolFunction.getZeroFunction(8);

        assertEquals(8, function.degree());
        assertEquals(256, function.getTruthTable().size());

        for (int i = 0; i < 256; ++i){
            assertEquals(0, function.getTruthTable().get(i));
        }
    }

    @Test
    void testCreateFunction1stDegree0(){
        BoolFunction function = BoolFunction.getNthFunction(1, 0);

        assertEquals(1, function.degree());
        assertEquals(2, function.getTruthTable().size());

        assertEquals(0, function.getTruthTable().get(0));
        assertEquals(0, function.getTruthTable().get(1));
    }

    @Test
    void testCreateFunction1stDegree1(){
        BoolFunction function = BoolFunction.getNthFunction(1, 1);

        assertEquals(1, function.degree());
        assertEquals(2, function.getTruthTable().size());

        assertEquals(0, function.getTruthTable().get(0));
        assertEquals(1, function.getTruthTable().get(1));
    }

    @Test
    void testCreateFunction1stDegree2(){
        BoolFunction function = BoolFunction.getNthFunction(1, 2);

        assertEquals(1, function.degree());
        assertEquals(2, function.getTruthTable().size());

        assertEquals(1, function.getTruthTable().get(0));
        assertEquals(0, function.getTruthTable().get(1));
    }

    @Test
    void testCreateFunction1stDegree3(){
        BoolFunction function = BoolFunction.getNthFunction(1, 3);

        assertEquals(1, function.degree());
        assertEquals(2, function.getTruthTable().size());

        assertEquals(1, function.getTruthTable().get(0));
        assertEquals(1, function.getTruthTable().get(1));
    }

    @Test
    void testCreateFunction3rdDegree3(){
        BoolFunction function = BoolFunction.getNthFunction(3, 3);

        assertEquals(3, function.degree());
        assertEquals(8, function.getTruthTable().size());

        assertArrayEquals(
                Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1).toArray(),
                function.getTruthTable().toArray()
        );
    }

    @Test
    void testCreateFunction3rdDegree42(){
        BoolFunction function = BoolFunction.getNthFunction(3, 42);

        assertEquals(3, function.degree());
        assertEquals(8, function.getTruthTable().size());

        assertArrayEquals(
                Arrays.asList(0, 0, 1, 0, 1, 0, 1, 0).toArray(),
                function.getTruthTable().toArray()
        );
    }

    @Test
    void testCreateFunction3rdDegree137(){
        BoolFunction function = BoolFunction.getNthFunction(3, 137);

        assertEquals(3, function.degree());
        assertEquals(8, function.getTruthTable().size());

        assertArrayEquals(
                Arrays.asList(1, 0, 0, 0, 1, 0, 0, 1).toArray(),
                function.getTruthTable().toArray()
        );
    }

    @Test
    void testNullWalshTransform(){
        BoolFunction function = BoolFunction.getZeroFunction(4);
        
        assertThrows(IllegalStateException.class, function::getWalshSpectrum);

    }
}
