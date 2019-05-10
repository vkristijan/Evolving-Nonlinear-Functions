package hr.fer.zemris.diplproj.heuristic.genprog.generator;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;

import java.util.ArrayList;
import java.util.List;

public class RandomPopulationGenerator implements IPopulationGenerator {
    @Override
    public List<BoolFunction> generatePopulation(int populationSize, IEvaluator evaluator) {
        List<BoolFunction> population = new ArrayList<>();

        int degree = Config.getInstance().getFunctionDegree();
        for (int i = 0; i < populationSize; ++i){
            BoolFunction f = BoolFunction.getRandomFunction(degree);

            f.setFitness(evaluator.evaluate(f));
            population.add(f);
        }

        return population;
    }
}
