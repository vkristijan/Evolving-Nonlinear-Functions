package hr.fer.zemris.diplproj.heuristic.genprog.node.function;

import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;

import java.util.List;


/**
 * A node that calculates a logical 'xor' operation on two boolean values.
 *
 * @author Kristijan Vulinovic
 */
public class XorFunctionNode extends AbstractFunctionNode {
    @Override
    public int childrenCount() {
        return 2;
    }

    @Override
    public INode copy() {
        return new XorFunctionNode();
    }

    @Override
    protected int function(List<Integer> arguments) {
        if (arguments.get(0).intValue() != arguments.get(1).intValue()){
            return 1;
        }
        return 0;
    }
}
