package hr.fer.zemris.diplproj;

import hr.fer.zemris.diplproj.walsh.ITransform;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a single boolean function using truth table.
 *
 * @author Kristijan Vulinovic
 */
public class BoolFunction {
    private int degree;
    private List<Integer> truthTable;
    private List<Integer> walshSpectrum;
    private ITransform walshTransform;

    public BoolFunction(int degree, List<Integer> truthTable){
        this.degree = degree;
        this.truthTable = truthTable;
    }

    public BoolFunction(int degree, List<Integer> truthTable, ITransform walshTransform){
        this(degree, truthTable);
        this.walshTransform = walshTransform;
    }

    public static BoolFunction getNthFunction(int degree, int n){
        int size = (1 << degree);

        List<Integer> table = new ArrayList<>(size);

        for (int i = size - 1; i >= 0; --i){
            if ((n & (1 << i)) == 0){
                table.add(0);
            } else {
                table.add(1);
            }
        }

        return new BoolFunction(degree, table);
    }

    public static BoolFunction getZeroFunction(int degree){
        int size = (1 << degree);

        List<Integer> table = new ArrayList<>(size);

        for (int i = 0; i < size; ++i){
            table.add(0);
        }

        return new BoolFunction(degree, table);
    }

    public int degree(){
        return degree;
    }

    public List<Integer> getTruthTable(){
        return truthTable;
    }

    public List<Integer> getWalshSpectrum(){
        if (walshSpectrum == null && walshTransform == null){
            throw new IllegalStateException("Unable to return the walsh spectrum without a specified transformer.");
        }

        if (walshSpectrum == null){
            walshSpectrum = walshTransform.transform(truthTable);
        }
        return walshSpectrum;
    }

    public void setWalshTransform(ITransform walshTransform){
        this.walshTransform = walshTransform;
    }
}
