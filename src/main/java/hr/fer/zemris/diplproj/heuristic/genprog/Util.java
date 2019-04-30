package hr.fer.zemris.diplproj.heuristic.genprog;

import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;
import hr.fer.zemris.diplproj.heuristic.genprog.node.function.AbstractFunctionNode;
import hr.fer.zemris.diplproj.heuristic.genprog.node.terminal.AbstractTerminalNode;

import java.util.List;
import java.util.Random;

/**
 * Utility class providing methods for genetic programming.
 *
 * @author Kristijan Vulinovic
 */
public class Util {
    /**
     * Instance of the random number generator.
     */
    private static Random rnd = Config.getInstance().getRnd();

    /**
     * Creates a tree with the given maximal depth and maximal node count.
     *
     * @param maxDepth the maximal allowed depth of the tree.
     * @param maxNodes the maximal number of nodes in the tree.
     * @return the root of the newly created tree.
     */
    public static INode generateTree(int maxDepth, int maxNodes){
        INode root;
        if (maxDepth == 1 || maxNodes < 1){
            root = getRandomTerminalNode();
        } else {
            root = getRandomNode();
        }

        maxNodes--;
        for (int i = 0; i < root.childrenCount(); ++i){
            INode child = generateTree(maxDepth - 1, maxNodes);
            maxNodes -= child.nodeCount();
            root.addChild(child);
        }

        return root;
    }

    /**
     * Creates a tree with the given maximal depth.
     *
     * @param maxDepth the maximal allowed depth of the tree.
     * @return the root of the newly created tree.
     */
    public static INode generateTree(int maxDepth){
        return generateTree(maxDepth, Integer.MAX_VALUE);
    }

    /**
     * Generates a new node.
     *
     * @return a randomly generated node.
     */
    public static INode getRandomNode(){
        if (rnd.nextBoolean()){
            return getRandomFunctionNode();
        } else {
            return getRandomTerminalNode();
        }
    }

    /**
     * Returns a copy of a random {@link AbstractTerminalNode}.
     *
     * @return a copy of a random {@link AbstractTerminalNode}.
     */
    public static AbstractTerminalNode getRandomTerminalNode(){
        List<AbstractTerminalNode> nodes = Config.getInstance().getTerminalNodes();

        int index = rnd.nextInt(nodes.size());
        return (AbstractTerminalNode) nodes.get(index).copy();
    }

    /**
     * Returns a copy of a random {@link AbstractFunctionNode}.
     *
     * @return a copy of a random {@link AbstractFunctionNode}.
     */
    public static AbstractFunctionNode getRandomFunctionNode(){
        List<AbstractFunctionNode> nodes = Config.getInstance().getFunctionNodes();

        int index = rnd.nextInt(nodes.size());
        return (AbstractFunctionNode) nodes.get(index).copy();
    }
}
