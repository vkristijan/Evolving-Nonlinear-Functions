package hr.fer.zemris.diplproj.heuristic.genprog.node.function;

import java.util.List;


/**
 * A node that calculates a logical 'or' operation on two boolean values.
 *
 * @author Kristijan Vulinovic
 */
public class OrFunctionNode extends AbstractFunctionNode {
    @Override
    public int childrenCount() {
        return 2;
    }

    @Override
    protected int function(List<Integer> arguments) {
        if (arguments.get(0) >= 1 || arguments.get(1) >= 1){
            return 1;
        }
        return 0;
    }
}
