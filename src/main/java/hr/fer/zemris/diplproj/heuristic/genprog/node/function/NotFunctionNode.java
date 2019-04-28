package hr.fer.zemris.diplproj.heuristic.genprog.node.function;

import java.util.List;


/**
 * A node that calculates a logical not operation on two boolean values.
 *
 * @author Kristijan Vulinovic
 */
public class NotFunctionNode extends AbstractFunctionNode {
    @Override
    public int childrenCount() {
        return 1;
    }

    @Override
    protected int function(List<Integer> arguments) {
        if (arguments.get(0) == 0){
            return 1;
        }
        return 0;
    }
}
