package hr.fer.zemris.diplproj.walsh;

/**
     * Enumeration of all possible states of node change for it to get to the
     * desired nonlinearity.
     *
     * Possible states are:
     * <ul>
     *     <li>UNKNOWN</li>
     *     <li>INCREMENT</li>
     *     <li>DECREMENT</li>
     *     <li>INVALID</li>
     * </ul>
     *
     * @author Kristijan Vulinovic
     */
    public enum NodeChange {
        /**
         * Represents a node which value we don't know hot to change.
         */
        UNKNOWN,
        /**
         * Represents a node which value should be incremented.
         */
        INCREMENT,
        /**
         * Represents a node which value should be decremented.
         */
        DECREMENT,
        /**
         * Represents a node that should not be changed.
         */
        INVALID
    }