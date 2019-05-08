package hr.fer.zemris.diplproj.heuristic;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.heuristic.simanneal.SimulatedAnnealing;
import hr.fer.zemris.diplproj.heuristic.simanneal.neighborhood.INeighbor;
import hr.fer.zemris.diplproj.heuristic.simanneal.neighborhood.RandomNeighbor;
import hr.fer.zemris.diplproj.heuristic.simanneal.neighborhood.TabulatedNeighbor;
import hr.fer.zemris.diplproj.heuristic.simanneal.temp.GeometricTempSchedule;
import hr.fer.zemris.diplproj.heuristic.simanneal.temp.ITempSchedule;

public class SimulatedAnnealingRunner {
    public static void main(String[] args) {
        Config config = Config.getInstance();

        double alfa = config.getTemperatureAlpha();
        int innerLimit = config.getTemperatureInnerLimit();
        int outerLimit = config.getTemperatureOuterLimit();
        double initialTemp = config.getTemperatureInitial();

        ITempSchedule tempSchedule = new GeometricTempSchedule(alfa, innerLimit, outerLimit, initialTemp);
        //INeighbor neighbor = new TabulatedNeighbor(4);
        INeighbor neighbor = new RandomNeighbor();

        SimulatedAnnealing solver = new SimulatedAnnealing(tempSchedule, neighbor);
        BoolFunction solution = solver.run();

        System.out.println(solution);
    }
}
