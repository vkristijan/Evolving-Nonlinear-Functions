package hr.fer.zemris.diplproj.heuristic.genprog;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;
import hr.fer.zemris.diplproj.walsh.FWT;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a single solution to the Genetic programming algorithm.
 * Every solution is represented by a tree structure of nodes.
 *
 * @author Kristijan Vulinovic
 */
public class Solution implements Comparable<Solution> {
    /**
     * Root of the tree representing the solution.
     */
    private INode root;

    /**
     * Fitness of the solution.
     */
    private double fitness;

    public Solution(INode root) {
        this.root = root;
    }

    public double getFitness(){
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public INode getRoot() {
        return root;
    }

    public BoolFunction toBoolFunction(){
        List<Integer> input = new ArrayList<>();
        int degree = Config.getInstance().getFunctionDegree();

        for (int i = 0; i < degree; ++i){
            input.add(0);
        }

        List<Integer> table = new ArrayList<>();
        do {
            table.add(root.evaluate(input));
        } while (nextInput(input));

        BoolFunction f = new BoolFunction(degree, table);
        f.setWalshTransform(new FWT());
        return f;
    }

    public void setRoot(INode root) {
        this.root = root;
    }

    private boolean nextInput(List<Integer> input){
        int n = input.size() - 1;

        boolean carry = true;
        while (carry){
             if (n < 0){
                 return false;
             }

             if (input.get(n) == 0){
                 input.set(n, 1);
                 carry = false;
             } else {
                 input.set(n, 0);
                 n--;
             }
        }

        return true;
    }

    @Override
    public int compareTo(Solution o) {
        return Double.compare(fitness, o.fitness);
    }
}
