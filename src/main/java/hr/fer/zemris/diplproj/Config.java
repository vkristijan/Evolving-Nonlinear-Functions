package hr.fer.zemris.diplproj;

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
     * The degree of the boolean function.
     */
    private int functionDegree;

    /**
     * Private constructor to ensure that no one is able to create new instances.
     */
    private Config(){
        functionDegree = 2;
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
