package hr.fer.zemris.diplproj.heuristic.genprog.node.function;

import java.util.List;


/**
 * A node that calculates a logical implication operation on two boolean values.
 *
 * @author Kristijan Vulinovic
 */
public class ImplicationFunctionNode extends AbstractFunctionNode {
    @Override
    public int childrenCount() {
        return 2;
    }

    @Override
    protected int function(List<Integer> arguments) {
        if (arguments.get(0) == 0 || arguments.get(1) == 1){
            return 1;
        }
        return 0;
    }
}
