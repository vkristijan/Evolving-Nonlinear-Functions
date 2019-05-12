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

    // Configuration related to Simulated Annealing
    private double temperatureAlpha;
    private int temperatureInnerLimit;
    private int temperatureOuterLimit;
    private double temperatureInitial;

    // Configuration related to Genetic algorithm
    private int gaPopulationSize;
    private int gaMaxGeneration;
    private int gaTournamentSize;

    // Configuration related to Genetic programming
    private int genprogPopulationSize;
    private int genprogMaxGeneration;
    private int genprogMaxDepth;
    private int genprogMaxNodeCount;
    private int genprogTournamentSize;

    /**
     * Private constructor to ensure that no one is able to create new instances.
     */
    private Config(){
        rnd = new Random();
        functionDegree = 8;

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

        temperatureAlpha = 0.995;
        temperatureInnerLimit = 25_000;
        temperatureOuterLimit = 1_000;
        temperatureInitial = 10;

        gaPopulationSize = 100;
        gaMaxGeneration = 1000;
        gaTournamentSize = 3;

        genprogPopulationSize = 100;
        genprogMaxGeneration = 1000;
        genprogMaxDepth = 7;
        genprogMaxNodeCount = 100;
        genprogTournamentSize = 3;
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

    public double getTemperatureAlpha() {
        return temperatureAlpha;
    }

    public int getTemperatureInnerLimit() {
        return temperatureInnerLimit;
    }

    public int getTemperatureOuterLimit() {
        return temperatureOuterLimit;
    }

    public double getTemperatureInitial() {
        return temperatureInitial;
    }

    public int getGaPopulationSize() {
        return gaPopulationSize;
    }

    public int getGaMaxGeneration() {
        return gaMaxGeneration;
    }

    public int getGaTournamentSize() {
        return gaTournamentSize;
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

    public int getGenprogPopulationSize() {
        return genprogPopulationSize;
    }

    public int getGenprogMaxGeneration() {
        return genprogMaxGeneration;
    }

    public int getGenprogMaxDepth() {
        return genprogMaxDepth;
    }

    public int getGenprogMaxNodeCount() {
        return genprogMaxNodeCount;
    }

    public int getGenprogTournamentSize() {
        return genprogTournamentSize;
    }
}
