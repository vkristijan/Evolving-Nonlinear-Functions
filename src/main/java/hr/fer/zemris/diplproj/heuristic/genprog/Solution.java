package hr.fer.zemris.diplproj.heuristic.genprog;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;

/**
 * Class representing a single solution to the Genetic programming algorithm.
 * Every solution is represented by a tree structure of nodes.
 *
 * @author Kristijan Vulinovic
 */
public class Solution {
    /**
     * Root of the tree representing the solution.
     */
    private INode root;

    /**
     * Fitness of the solution.
     */
    private double fitness;

    public double getFitness(){
        return fitness;
    }

    public INode getRoot() {
        return root;
    }

    public BoolFunction toBoolFunction(){
        throw new IllegalStateException();
    }
}
