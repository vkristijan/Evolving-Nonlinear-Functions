package hr.fer.zemris.diplproj.heuristic.simanneal;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.evaluator.BentEvaluator;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;
import hr.fer.zemris.diplproj.evaluator.NonlinearityEvaluator;
import hr.fer.zemris.diplproj.heuristic.simanneal.neighborhood.INeighbor;
import hr.fer.zemris.diplproj.heuristic.simanneal.temp.ITempSchedule;
import hr.fer.zemris.diplproj.walsh.FWT;
import hr.fer.zemris.diplproj.walsh.ITransform;

import java.util.Random;

public class SimulatedAnnealing {
    private ITempSchedule tempSchedule;
    private INeighbor neighbor;
    private IEvaluator evaluator;
    private ITransform transform;

    public SimulatedAnnealing(ITempSchedule tempSchedule, INeighbor neighbor){
        this.tempSchedule = tempSchedule;
        this.neighbor = neighbor;

        evaluator = new NonlinearityEvaluator();
        transform = new FWT();
    }

    public BoolFunction run() {
        IEvaluator bentEvaluator = new BentEvaluator();
        Random rnd = Config.getInstance().getRnd();
        int degree = Config.getInstance().getFunctionDegree();

        BoolFunction solution = BoolFunction.getRandomFunction(degree);
        solution.setWalshTransform(transform);
        double value = evaluator.evaluate(solution);

        int maxInner = tempSchedule.getInnerLoopCounter();
        int maxOuter = tempSchedule.getOuterLoopCounter();

        for (int i = 0; i < maxOuter; ++i){
            double temperature = tempSchedule.getNextTemperature();
            for (int j = 0; j < maxInner; ++j){
                BoolFunction newSolution = neighbor.getNeighbor(solution);
                double newValue = evaluator.evaluate(newSolution);

                double delta = value - newValue;

                boolean shouldUpdate;
                if (delta < 0){
                    shouldUpdate = true;
                } else {
                    double p = Math.exp(-delta/temperature);
                    shouldUpdate = p > rnd.nextDouble();
                }
                if (shouldUpdate) {
                    solution = newSolution;
                    value = newValue;
                    if (bentEvaluator.evaluate(solution) > 0){
                        return solution;
                    }
                }
            }

            System.out.printf("Temperature #%.2f : [%s]  -> Fitness: %.2f%n", temperature, solution, value);
        }

        return solution;
    }
}
