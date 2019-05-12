package hr.fer.zemris.diplproj.heuristic.common.generator;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;

import java.util.List;

public interface IPopulationGenerator {
    List<BoolFunction> generatePopulation(int populationSize, IEvaluator evaluator);
}
