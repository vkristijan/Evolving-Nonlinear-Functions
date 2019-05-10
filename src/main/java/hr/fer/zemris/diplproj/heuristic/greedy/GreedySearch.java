package hr.fer.zemris.diplproj.heuristic.greedy;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;
import hr.fer.zemris.diplproj.heuristic.ISolver;
import hr.fer.zemris.diplproj.heuristic.common.neighborhood.INeighbor;
import hr.fer.zemris.diplproj.walsh.FWT;

public class GreedySearch implements ISolver {
    private INeighbor neighbor;
    private IEvaluator goal;
    private IEvaluator fitness;

    public GreedySearch(INeighbor neighbor, IEvaluator goal, IEvaluator fitness){
        this.neighbor = neighbor;
        this.goal = goal;
        this.fitness = fitness;
    }

    @Override
    public BoolFunction run() {
        Config config = Config.getInstance();
        BoolFunction f = BoolFunction.getRandomFunction(config.getFunctionDegree());
        f.setWalshTransform(new FWT());

        double fit = fitness.evaluate(f);
        while (goal.evaluate(f) < 1){
            BoolFunction candidate = neighbor.getNeighbor(f);
            double newFit = fitness.evaluate(candidate);

            if (newFit >= fit){
                fit = newFit;
                f = candidate;
            }
        }

        return f;
    }
}
