package hr.fer.zemris.diplproj.walsh;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the {@link ITransform} that not only calculates the
 * Walsh coefficients of a given {@link hr.fer.zemris.diplproj.BoolFunction},
 * but also all the intermediate results, as well as the connections between
 * them. It is also possible to retrieve all the values for further analysis,
 * such as backward tracking.
 *
 * @author Kristijan Vulinovic
 */
public class StepwiseFWT implements ITransform {
    private List<List<FWTNode>> layers;

    @Override
    public List<Integer> transform(List<Integer> values) {
        layers = new ArrayList<>();
        List<FWTNode> input = new ArrayList<>();
        values.forEach(x -> input.add(new FWTNode(x > 0 ? x : -1)));
        layers.add(input);

        int n = values.size();
        List<FWTNode> layer = input;
        for (int step = 1; step < n; step *= 2){
            List<FWTNode> nextLayer = new ArrayList<>();
            values.forEach(x -> nextLayer.add(new FWTNode()));

            int blockSize = step * 2;
            for (int block = 0; block < n / blockSize; ++block){
                for (int i = block * blockSize; i < block * blockSize + step; ++i){
                    FWTNode parentA = layer.get(i);
                    FWTNode parentB = layer.get(i + step);
                    FWTNode childA = nextLayer.get(i);
                    FWTNode childB = nextLayer.get(i + step);

                    adjustNodes(parentA, parentB, childA, childB);
                }
            }

            layers.add(nextLayer);
            layer = nextLayer;
        }

        List<Integer> result = new ArrayList<>();
        layer.forEach(x -> result.add(x.value));
        return result;
    }

    /**
     * Returns all the intermediate results, as a matrix of {@link FWTNode}.
     *
     * @return all the intermediate results.
     */
    public List<List<FWTNode>> getLayers() {
        return layers;
    }


    /**
     * Calculates the value for the child nodes and sets the relationships between parent and children.
     *
     * @param parentA the first parent
     * @param parentB the second parent
     * @param childA the first child
     * @param childB the second child
     */
    private void adjustNodes(FWTNode parentA, FWTNode parentB, FWTNode childA, FWTNode childB) {
        childA.value = parentA.value + parentB.value;
        childA.parentA = parentA;
        childA.parentB = parentB;

        childB.value = parentA.value - parentB.value;
        childB.parentA = parentA;
        childB.parentB = parentB;

        parentA.childA = childA;
        parentA.childB = childB;

        parentB.childA = childA;
        parentB.childB = childB;
        parentB.invertB = true;
    }

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

    /**
     * A node representing a single value in the {@link StepwiseFWT} transform matrix.
     *
     * @author Kristijan Vulinovic
     */
    public static class FWTNode {
        /**
         * The value of the node.
         */
        private int value;
        /**
         * The way we need to change the node.
         */
        private NodeChange change;

        /**
         * The first parent.
         */
        private FWTNode parentA;
        /**
         * The second parent.
         */
        private FWTNode parentB;

        /**
         * The first child.
         */
        private FWTNode childA;
        /**
         * Whether the invert the value of the node when calculating the first child or not.
         */
        private boolean invertA;
        /**
         * The second child.
         */
        private FWTNode childB;
        /**
         * Whether the invert the value of the node when calculating the second child or not.
         */
        private boolean invertB;

        /**
         * Creates a new {@link FWTNode} with the given value.
         *
         * @param value the value of the node.
         */
        private FWTNode(int value){
            this();
            this.value = value;
        }

        /**
         * Creates an empty new {@link FWTNode}.
         */
        private FWTNode(){
             change = NodeChange.UNKNOWN;
             invertA = false;
             invertB = false;
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
}
