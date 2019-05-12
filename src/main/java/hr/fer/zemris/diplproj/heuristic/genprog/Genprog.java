package hr.fer.zemris.diplproj.heuristic.genprog;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.evaluator.IEvaluator;
import hr.fer.zemris.diplproj.heuristic.ISolver;
import hr.fer.zemris.diplproj.heuristic.genprog.crossover.ICrossover;
import hr.fer.zemris.diplproj.heuristic.genprog.mutation.IMutation;
import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;
import hr.fer.zemris.diplproj.heuristic.genprog.selection.ISelection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Genprog implements ISolver {
    private Solution bestSolution;
    private int populationSize;
    private int maxGeneration;
    private IEvaluator evaluator;
    private ISelection selection;
    private ICrossover crossover;
    private IMutation mutation;

    private int maxDepth;
    private int maxNodeCount;

    public Genprog(int populationSize, int maxGeneration,
                   IEvaluator evaluator, ISelection selection, ICrossover crossover, IMutation mutation, int maxDepth, int maxNodeCount){
        this.populationSize = populationSize;
        this.maxGeneration = maxGeneration;
        this.evaluator = evaluator;
        this.selection = selection;
        this.crossover = crossover;
        this.mutation = mutation;
        this.maxDepth = maxDepth;
        this.maxNodeCount = maxNodeCount;
    }

    public Solution getBestSolution() {
        return bestSolution;
    }

    @Override
    public BoolFunction run() {
        List<Solution> population = generatePopulation(populationSize, evaluator);
        population.sort(Comparator.reverseOrder());

        for (int i = 0; i < maxGeneration; ++i){
            List<Solution> newPopulation = new ArrayList<>();
            bestSolution = population.get(0);
            newPopulation.add(population.get(0));

            while (newPopulation.size() < populationSize){
                Solution p1 = selection.select(population);
                Solution p2 = selection.select(population);
                Solution child = crossover.crossover(p1, p2);
                mutation.mutate(child);
                child.setFitness(evaluator.evaluate(child.toBoolFunction()));

                newPopulation.add(child);
            }

            population = newPopulation;
            population.sort(Comparator.reverseOrder());
            System.out.printf("Iteration %4d:  %3.2f\n", i, population.get(0).getFitness());
        }

        bestSolution = population.get(0);
        return population.get(0).toBoolFunction();
    }

    private List<Solution> generatePopulation(int populationSize, IEvaluator evaluator) {
        List<Solution> population = new ArrayList<>();

        for (int i = 0; i < populationSize; ++i){
            INode node = Util.generateTree(maxDepth, maxNodeCount);
            Solution sol = new Solution(node);
            sol.setFitness(evaluator.evaluate(sol.toBoolFunction()));

            population.add(sol);
        }

        return population;
    }
}
