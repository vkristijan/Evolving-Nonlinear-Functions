package hr.fer.zemris.diplproj.heuristic;

import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;
import hr.fer.zemris.diplproj.evaluator.NonlinearityEvaluator;
import hr.fer.zemris.diplproj.heuristic.ga.GenerationGA;
import hr.fer.zemris.diplproj.heuristic.ga.crossover.ICrossover;
import hr.fer.zemris.diplproj.heuristic.ga.crossover.SinglePointCrossover;
import hr.fer.zemris.diplproj.heuristic.ga.mutation.IMutation;
import hr.fer.zemris.diplproj.heuristic.ga.mutation.RandomMutation;
import hr.fer.zemris.diplproj.heuristic.ga.selection.ISelection;
import hr.fer.zemris.diplproj.heuristic.ga.selection.TournamentSelection;
import hr.fer.zemris.diplproj.heuristic.genprog.generator.IPopulationGenerator;
import hr.fer.zemris.diplproj.heuristic.genprog.generator.RandomPopulationGenerator;

public class GARunner {
    public static void main(String[] args) {
        Config config = Config.getInstance();

        int populationSize = config.getGaPopulationSize();
        int maxGeneration = config.getGaMaxGeneration();
        int tournamentSize = config.getGaTournamentSize();

        IPopulationGenerator generator = new RandomPopulationGenerator();
        IEvaluator evaluator = new NonlinearityEvaluator();
        ISelection selection = new TournamentSelection(tournamentSize);
        ICrossover crossover = new SinglePointCrossover();
        IMutation mutation = new RandomMutation();

        ISolver solver = new GenerationGA(populationSize, maxGeneration,
                generator, evaluator, selection, crossover, mutation);

        var solution = solver.run();

        System.out.println(solution);
    }
}
