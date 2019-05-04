package hr.fer.zemris.diplproj.walsh;

import java.util.ArrayList;
import java.util.List;

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
    private enum NodeChange {
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


    private static class FWTNode {
        private int value;
        private NodeChange change;

        private FWTNode parentA;
        private FWTNode parentB;

        private FWTNode childA;
        private boolean invertA;
        private FWTNode childB;
        private boolean invertB;

        public FWTNode(int value){
            this();
            this.value = value;
        }

        public FWTNode(){
             change = NodeChange.UNKNOWN;
             invertA = false;
             invertB = false;
        }
    }
}
