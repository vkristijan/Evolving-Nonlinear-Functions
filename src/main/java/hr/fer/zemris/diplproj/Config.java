package hr.fer.zemris.diplproj;

import hr.fer.zemris.diplproj.heuristic.genprog.node.function.*;
import hr.fer.zemris.diplproj.heuristic.genprog.node.terminal.AbstractTerminalNode;
import hr.fer.zemris.diplproj.heuristic.genprog.node.terminal.ValueTerminalNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Singleton class containing configurations for other parts of the project.
 *
 * @author Kristijan Vulinovic
 */
public class Config {
    /**
     * The instance of the config class.
     */
    private static Config instance;

    /**
     * Random number generator used across the project.
     */
    private Random rnd;

    /**
     * The degree of the boolean function.
     */
    private int functionDegree;

    // Configuration related to Genetic programming
    /**
     * List containing all available terminal nodes.
     */
    private List<AbstractTerminalNode> terminalNodes;

    /**
     * List containing all available function nodes.
     */
    private List<AbstractFunctionNode> functionNodes;

    /**
     * Private constructor to ensure that no one is able to create new instances.
     */
    private Config(){
        rnd = new Random();
        functionDegree = 2;

        terminalNodes = new ArrayList<>();
        for (int i = 0; i < functionDegree; ++i){
            terminalNodes.add(new ValueTerminalNode(i));
        }

        functionNodes = new ArrayList<>();
        functionNodes.add(new AndFunctionNode());
        functionNodes.add(new BiconditionFunctionNode());
        functionNodes.add(new ImplicationFunctionNode());
        functionNodes.add(new NotFunctionNode());
        functionNodes.add(new OrFunctionNode());
        functionNodes.add(new XorFunctionNode());
    }

    /**
     * Returns the random number generator.
     *
     * @return the random number generator.
     */
    public Random getRnd(){
        return rnd;
    }

    /**
     * Returns the degree of the boolean function.
     *
     * @return the degree of the boolean function.
     */
    public int getFunctionDegree(){
        return functionDegree;
    }

    /**
     * Returns all the available terminal nodes.
     *
     * @return all the available terminal nodes.
     */
    public List<AbstractTerminalNode> getTerminalNodes(){
        return terminalNodes;
    }

    /**
     * Returns all the available function nodes.
     *
     * @return all the available function nodes.
     */
    public List<AbstractFunctionNode> getFunctionNodes(){
        return functionNodes;
    }

    /**
     * Returns an instance of the configuration class.
     *
     * @return an instance of the configuration class.
     */
    public static Config getInstance(){
        if (instance == null){
            instance = new Config();
        }

        return instance;
    }
}
