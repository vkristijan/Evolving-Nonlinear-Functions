package hr.fer.zemris.diplproj;

import hr.fer.zemris.diplproj.walsh.ITransform;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static BoolFunction getRandomFunction(int degree){
        Random rnd = Config.getInstance().getRnd();
        int n = 1 << degree;
        List<Integer> tt = new ArrayList<>(n);
        for (int i = 0; i < n; ++i){
            if (rnd.nextBoolean()){
                tt.add(0);
            } else {
                tt.add(1);
            }
        }
        return new BoolFunction(degree, tt);
    }

    public static List<BoolFunction> getFunctions(int degree) {
        List<BoolFunction> functions = new ArrayList<>();

        int n = 1 << (1 << degree);
        for (int i = 0; i < n; ++i){
            functions.add(BoolFunction.getNthFunction(degree, i));
        }

        return functions;
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

    public ITransform getWalshTransform() {
        return walshTransform;
    }

    public void reset(){
        this.walshSpectrum = null;
        hash = 0;
    }

    private int hash = 0;
    @Override
    public int hashCode() {
        if (hash != 0) return hash;

        long sol = 0;
        long tmp = 1;

        for (var b : getTruthTable()){
            if (b == 1){
                sol += tmp;
            }
            tmp *= 2;

            sol %= Integer.MAX_VALUE;
            tmp %= Integer.MAX_VALUE;
        }

        hash = (int)(sol % Integer.MAX_VALUE);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        getTruthTable().forEach(sb::append);
        return sb.toString();
    }
}
