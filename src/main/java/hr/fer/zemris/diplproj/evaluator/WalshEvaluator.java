package hr.fer.zemris.diplproj.evaluator;

import hr.fer.zemris.diplproj.BoolFunction;

public class WalshEvaluator implements IEvaluator {
    private double R;
    private double X;

    public WalshEvaluator(double r, double x){
        this.R = r;
        this.X = x;
    }

    public WalshEvaluator(){
        this(3, 4);
    }

    @Override
    public double evaluate(BoolFunction function) {
        double cost = 0;

        for (var w : function.getWalshSpectrum()){
            cost += Math.pow(Math.abs(Math.abs(w) - X), R);
        }

        return 100000.0 / cost;
    }
}
