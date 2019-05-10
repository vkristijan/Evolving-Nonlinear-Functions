package hr.fer.zemris.diplproj.heuristic.ga.crossover;

import hr.fer.zemris.diplproj.BoolFunction;

public interface ICrossover {
    BoolFunction crossover(BoolFunction parent1, BoolFunction parent2);
}
