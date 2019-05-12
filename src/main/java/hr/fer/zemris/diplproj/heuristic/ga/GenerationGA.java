package hr.fer.zemris.diplproj.heuristic.ga;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;
import hr.fer.zemris.diplproj.heuristic.ISolver;
import hr.fer.zemris.diplproj.heuristic.ga.crossover.ICrossover;
import hr.fer.zemris.diplproj.heuristic.ga.mutation.IMutation;
import hr.fer.zemris.diplproj.heuristic.ga.selection.ISelection;
import hr.fer.zemris.diplproj.heuristic.common.generator.IPopulationGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GenerationGA implements ISolver {
    private int populationSize;
    private int maxGeneration;
    private IPopulationGenerator generator;
    private IEvaluator evaluator;
    private ISelection selection;
    private ICrossover crossover;
    private IMutation mutation;

    public GenerationGA(int populationSize, int maxGeneration, IPopulationGenerator generator,
                        IEvaluator evaluator, ISelection selection, ICrossover crossover, IMutation mutation){
        this.populationSize = populationSize;
        this.maxGeneration = maxGeneration;
        this.generator = generator;
        this.evaluator = evaluator;
        this.selection = selection;

        this.crossover = crossover;
        this.mutation = mutation;
    }

    @Override
    public BoolFunction run() {
        List<BoolFunction> population = generator.generatePopulation(populationSize, evaluator);
        population.sort(Comparator.reverseOrder());

        for (int i = 0; i < maxGeneration; ++i){
            List<BoolFunction> newPopulation = new ArrayList<>();
            newPopulation.add(population.get(0));

            while (newPopulation.size() < populationSize){
                BoolFunction p1 = selection.select(population);
                BoolFunction p2 = selection.select(population);
                BoolFunction child = crossover.crossover(p1, p2);
                mutation.mutate(child);
                child.setFitness(evaluator.evaluate(child));

                newPopulation.add(child);
            }

            population = newPopulation;
            population.sort(Comparator.reverseOrder());
            System.out.printf("Iteration %4d:  %3.2f\n", i, population.get(0).getFitness());
        }

        return population.get(0);
    }
}
