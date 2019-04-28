package hr.fer.zemris.diplproj.heuristic.genprog.node.terminal;

import java.util.List;

/**
 * Implementation of a terminal node that takes the value of a function input.
 * As a boolean function can have multiple inputs, the node defines an index and
 * takes the value of just the input with the specified index.
 *
 * @author Kristijan Vulinovic
 */
public class ValueTerminalNode extends AbstractTerminalNode {
    /**
     * The index of the input value.
     */
    private int index;

    /**
     * Creates a new <code>ValueTerminalNode</code> for the given input index;
     *
     * @param index the index of the input value
     */
    public ValueTerminalNode(int index){
        this.index = index;
    }

    @Override
    public int evaluate(List<Integer> inputs) {
        return inputs.get(index);
    }
}
