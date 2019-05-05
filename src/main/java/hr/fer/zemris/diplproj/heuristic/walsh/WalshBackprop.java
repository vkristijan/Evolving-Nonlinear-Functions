package hr.fer.zemris.diplproj.heuristic.walsh;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.evaluator.BentEvaluator;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;
import hr.fer.zemris.diplproj.evaluator.NonlinearityEvaluator;
import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;
import hr.fer.zemris.diplproj.walsh.ITransform;
import hr.fer.zemris.diplproj.walsh.StepwiseFWT;

import java.util.*;

import static hr.fer.zemris.diplproj.walsh.StepwiseFWT.NodeChange.UNKNOWN;
import static hr.fer.zemris.diplproj.walsh.StepwiseFWT.NodeChange.INCREMENT;
import static hr.fer.zemris.diplproj.walsh.StepwiseFWT.NodeChange.DECREMENT;
import static hr.fer.zemris.diplproj.walsh.StepwiseFWT.NodeChange.INVALID;

public class WalshBackprop {
    private static Set<Integer> changedBits = new HashSet<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("The program expects 1 argument, the degree of the function.");
        }

        int degree = Integer.parseInt(args[0]);

        BoolFunction f = BoolFunction.getRandomFunction(degree);
        System.out.println(f);

        f.setWalshTransform(new StepwiseFWT());
        IEvaluator evaluator = new BentEvaluator();
        IEvaluator nonlinear = new NonlinearityEvaluator();
        while (evaluator.evaluate(f) == 0){
            var bitsForChange = getBits(f);

            //TODO implement changedBits memory
            int bitIndex = -1;

            while (bitIndex < 0) {
                if (bitsForChange.size() > 0) {
                    int index = Config.getInstance().getRnd().nextInt(bitsForChange.size());
                    bitIndex = bitsForChange.get(index);
                    bitsForChange.remove(index);
                } else {
                    bitIndex = Config.getInstance().getRnd().nextInt(f.getTruthTable().size());
                }

                if (changedBits.contains(bitIndex)){
                    bitIndex = -1;
                }
            }

            changedBits.add(bitIndex);
            int oldVal = f.getTruthTable().get(bitIndex);
            f.getTruthTable().set(bitIndex, 1 - oldVal);
            f.reset();

            System.out.println(f + " - " + nonlinear.evaluate(f));
        }
    }

    public static List<Integer> getBits(BoolFunction f) {
        int maxWalsh = f.getWalshSpectrum().stream()
                                           .mapToInt(Integer::intValue)
                                           .map(Math::abs)
                                           .max()
                                           .orElseThrow();

        int degree = f.degree();
        int expected = (1 << (degree/2));

        ITransform transform = f.getWalshTransform();
        if (!(transform instanceof StepwiseFWT)){
            throw new UnsupportedOperationException("The operation is not supported without StepwiseFWT!");
        }

        var fwt = ((StepwiseFWT) transform).getLayers();
        setConstraints(fwt, maxWalsh, expected);
        propagate(fwt);

        List<Integer> result = new ArrayList<>();
        var layer = fwt.get(0);
        for (int i = 0; i < layer.size(); ++i){
            var node = layer.get(i);

            if (node.getChange() == INCREMENT && node.getValue() < 0){
                result.add(i);
            }
            if (node.getChange() == DECREMENT && node.getValue() > 0){
                result.add(i);
            }
        }

        return result;
    }


    private static void setConstraints(List<List<StepwiseFWT.FWTNode>> fwt, int maxWalsh, int expected) {
        var layer = fwt.get(fwt.size() - 1);

        int maxChange = maxWalsh - expected;
        for (var node : layer){
            int value = node.getValue();

            node.setChange(UNKNOWN);
            if (value >= maxWalsh - 2){
                node.setChange(DECREMENT);
            } else if (value <= -maxWalsh + 2){
                node.setChange(INCREMENT);
            } else if (value > -expected && value < expected){
                int lowDist = Math.abs(value + expected);
                int hiDist  = Math.abs(value - expected);

                if (lowDist < hiDist && lowDist == maxChange){
                    node.setChange(DECREMENT);
                } else if (hiDist < lowDist && hiDist == maxChange){
                    node.setChange(INCREMENT);
                }
            }
        }
    }

    private static void propagate(List<List<StepwiseFWT.FWTNode>> fwt) {
        int n = fwt.size();

        //start from n-2, as the last layer is already set
        for (int i = n-2; i >=0; --i){
            var layer = fwt.get(i);

            for (var node : layer){
                node.setChange(UNKNOWN);

                StepwiseFWT.NodeChange a = node.getChildA().getChange();
                StepwiseFWT.NodeChange b = node.getChildB().getChange();
                if (node.isInvertA()){
                    a = invert(a);
                }
                if (node.isInvertB()){
                    b = invert(b);
                }

                if (b == UNKNOWN){
                    node.setChange(a);

                } else if (a == UNKNOWN){
                    node.setChange(b);

                } else if (a == b) {
                    node.setChange(a);

                } else {
                    node.setChange(INVALID);
                }
            }
        }
    }

    private static StepwiseFWT.NodeChange invert(StepwiseFWT.NodeChange node) {
        if (node == INVALID || node == UNKNOWN){
            return node;
        }

        if (node == INCREMENT) {
            return DECREMENT;
        }

        return INCREMENT;
    }
}
