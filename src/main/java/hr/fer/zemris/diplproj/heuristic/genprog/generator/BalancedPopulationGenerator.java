package hr.fer.zemris.diplproj.heuristic.genprog.generator;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BalancedPopulationGenerator implements IPopulationGenerator {
    @Override
    public List<BoolFunction> generatePopulation(int populationSize, IEvaluator evaluator) {
        List<BoolFunction> population = new ArrayList<>();

        int degree = Config.getInstance().getFunctionDegree();
        for (int i = 0; i < populationSize; ++i){
            BoolFunction f = BoolFunction.getRandomFunction(degree);
            balance(f);

            f.setFitness(evaluator.evaluate(f));
            population.add(f);
        }

        return population;
    }

    private void balance(BoolFunction f) {
        int onesCount = 0;
        int zeroCount = 0;

        var table = f.getTruthTable();
        int n = table.size();

        for(int x : table) {
            if (x == 0) {
                zeroCount++;
            } else {
                onesCount++;
            }
        }

        if (onesCount > zeroCount){
            remove(f,1, onesCount - zeroCount);
        } else {
            remove(f,0, zeroCount - onesCount);
        }
    }

    private void remove(BoolFunction f, int element, int count) {
        Random rnd = Config.getInstance().getRnd();
        int n = f.getTruthTable().size();

        var table = f.getTruthTable();
        while (count > 0){
            int index = rnd.nextInt(n);
            while (table.get(index) != element){
                index = rnd.nextInt(n);
            }
            table.set(index, 1-element);

            //change by two because we changed 1 element for the other
            count -= 2;
        }
    }
}
