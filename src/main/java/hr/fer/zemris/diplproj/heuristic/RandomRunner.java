package hr.fer.zemris.diplproj.heuristic;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.evaluator.BentEvaluator;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;
import hr.fer.zemris.diplproj.heuristic.common.neighborhood.INeighbor;
import hr.fer.zemris.diplproj.heuristic.common.neighborhood.RandomNeighbor;
import hr.fer.zemris.diplproj.heuristic.random.RandomSearch;

public class RandomRunner {
    public static void main(String[] args) {
        //INeighbor neighbor = new TabulatedNeighbor(4);
        INeighbor neighbor = new RandomNeighbor();
        IEvaluator evaluator = new BentEvaluator();
        ISolver solver = new RandomSearch(neighbor, evaluator);

        BoolFunction solution = solver.run();
        System.out.println(solution);
    }
}
