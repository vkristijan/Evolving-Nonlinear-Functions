package hr.fer.zemris.diplproj.heuristic;

import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;
import hr.fer.zemris.diplproj.evaluator.NonlinearityEvaluator;
import hr.fer.zemris.diplproj.heuristic.genprog.Genprog;
import hr.fer.zemris.diplproj.heuristic.genprog.crossover.ICrossover;
import hr.fer.zemris.diplproj.heuristic.genprog.crossover.SubtreeChangeCrossover;
import hr.fer.zemris.diplproj.heuristic.genprog.mutation.IMutation;
import hr.fer.zemris.diplproj.heuristic.genprog.mutation.RandomNodeMutation;
import hr.fer.zemris.diplproj.heuristic.genprog.selection.ISelection;
import hr.fer.zemris.diplproj.heuristic.genprog.selection.TournamentSelection;


public class GenprogRunner {
    public static void main(String[] args) {
        Config config = Config.getInstance();

        int populationSize = config.getGenprogPopulationSize();
        int maxGeneration = config.getGenprogMaxGeneration();
        int maxDepth = config.getGenprogMaxDepth();
        int maxNodeCount = config.getGenprogMaxNodeCount();
        int tournamentSize = config.getGenprogTournamentSize();

        IEvaluator evaluator = new NonlinearityEvaluator();
        ISelection selection = new TournamentSelection(tournamentSize);
        ICrossover crossover = new SubtreeChangeCrossover();
        IMutation mutation = new RandomNodeMutation(maxNodeCount, maxDepth);

        ISolver solver = new Genprog(populationSize, maxGeneration,
                evaluator, selection, crossover, mutation, maxDepth, maxNodeCount);

        var solution = solver.run();

        System.out.println(solution);
    }
}
