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
}
