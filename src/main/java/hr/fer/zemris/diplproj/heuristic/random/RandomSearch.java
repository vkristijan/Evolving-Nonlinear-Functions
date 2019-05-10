package hr.fer.zemris.diplproj.heuristic.random;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;
import hr.fer.zemris.diplproj.heuristic.ISolver;
import hr.fer.zemris.diplproj.heuristic.common.neighborhood.INeighbor;
import hr.fer.zemris.diplproj.walsh.FWT;

public class RandomSearch implements ISolver {
    private INeighbor neighbor;
    private IEvaluator evaluator;

    public RandomSearch(INeighbor neighbor, IEvaluator evaluator){
        this.neighbor = neighbor;
        this.evaluator = evaluator;
    }

    @Override
    public BoolFunction run() {
        Config config = Config.getInstance();
        BoolFunction f = BoolFunction.getRandomFunction(config.getFunctionDegree());
        f.setWalshTransform(new FWT());

        while (evaluator.evaluate(f) < 1){
            f = neighbor.getNeighbor(f);
        }

        return f;
    }
}
