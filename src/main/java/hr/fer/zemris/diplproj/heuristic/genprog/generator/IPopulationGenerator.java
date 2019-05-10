package hr.fer.zemris.diplproj.heuristic.genprog.generator;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;

import java.util.List;

public interface IPopulationGenerator {
    List<BoolFunction> generatePopulation(int populationSize, IEvaluator evaluator);
}
