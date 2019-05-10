package hr.fer.zemris.diplproj.heuristic;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.evaluator.BentEvaluator;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;
import hr.fer.zemris.diplproj.evaluator.NonlinearityEvaluator;
import hr.fer.zemris.diplproj.heuristic.common.neighborhood.INeighbor;
import hr.fer.zemris.diplproj.heuristic.common.neighborhood.RandomNeighbor;
import hr.fer.zemris.diplproj.heuristic.common.neighborhood.TabulatedNeighbor;
import hr.fer.zemris.diplproj.heuristic.greedy.GreedySearch;

public class GreedyRunner {
    public static void main(String[] args) {
        INeighbor neighbor = new TabulatedNeighbor(4);
        //INeighbor neighbor = new RandomNeighbor();
        IEvaluator goal = new BentEvaluator();
        IEvaluator fitness = new NonlinearityEvaluator();

        ISolver solver = new GreedySearch(neighbor, goal, fitness);

        BoolFunction solution = solver.run();
        System.out.println(solution);
    }
}
