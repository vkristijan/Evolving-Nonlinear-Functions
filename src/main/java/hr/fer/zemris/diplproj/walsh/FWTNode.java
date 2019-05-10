package hr.fer.zemris.diplproj.walsh;

/**
     * A node representing a single value in the {@link StepwiseFWT} transform matrix.
     *
     * @author Kristijan Vulinovic
     */
    public class FWTNode {
        /**
         * The value of the node.
         */
        int value;
        /**
         * The way we need to change the node.
         */
        NodeChange change;

        /**
         * The first parent.
         */
        FWTNode parentA;
        /**
         * The second parent.
         */
        FWTNode parentB;

        /**
         * The first child.
         */
        FWTNode childA;
        /**
         * Whether the invert the value of the node when calculating the first child or not.
         */
        boolean invertA;
        /**
         * The second child.
         */
        FWTNode childB;
        /**
         * Whether the invert the value of the node when calculating the second child or not.
         */
        boolean invertB;

        /**
         * Creates a new {@link FWTNode} with the given value.
         *
         * @param value the value of the node.
         */
        FWTNode(int value){
            this();
            this.value = value;
        }

        /**
         * Creates an empty new {@link FWTNode}.
         */
        FWTNode(){
             change = NodeChange.UNKNOWN;
             invertA = false;
             invertB = false;
        }

        /**
         * Returns the value of the node.
         *
         * @return the value of the node.
         */
        public int getValue() {
            return value;
        }

        /**
         * Returns the first parent.
         *
         * @return the first parent.
         */
        public FWTNode getParentA() {
            return parentA;
        }

        /**
         * Returns the second parent.
         *
         * @return the second parent.
         */
        public FWTNode getParentB() {
            return parentB;
        }

        /**
         * Returns the first child.
         *
         * @return the first child.
         */
        public FWTNode getChildA() {
            return childA;
        }

        /**
         * Returns the second child.
         *
         * @return the second child.
         */
        public FWTNode getChildB() {
            return childB;
        }

        /**
         * Returns whether the value is inverted to calculate the first child or not.
         *
         * @return whether the value is inverted to calculate the first child or not.
         */
        public boolean isInvertA() {
            return invertA;
        }

        /**
         * Returns whether the value is inverted to calculate the second child or not.
         *
         * @return whether the value is inverted to calculate the second child or not.
         */
        public boolean isInvertB() {
            return invertB;
        }

        /**
         * Returns the {@link NodeChange} for the node.
         *
         * @return the {@link NodeChange} for the node.
         */
        public NodeChange getChange() {
            return change;
        }

        /**
         * Sets the {@link NodeChange} for the node.
         *
         * @param change the {@link NodeChange} for the node.
         */
        public void setChange(NodeChange change) {
            this.change = change;
        }
    }