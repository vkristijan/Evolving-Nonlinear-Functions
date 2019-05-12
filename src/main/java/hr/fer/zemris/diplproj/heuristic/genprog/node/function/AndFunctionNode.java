package hr.fer.zemris.diplproj.heuristic.genprog.node.function;

import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;

import java.util.List;


/**
 * A node that calculates a logical 'and' operation on two boolean values.
 *
 * @author Kristijan Vulinovic
 */
public class AndFunctionNode extends AbstractFunctionNode {
    @Override
    public int childrenCount() {
        return 2;
    }

    @Override
    public INode copy() {
        INode node = new AndFunctionNode();

        node.getChildren().forEach(c -> node.addChild(c.copy()));
        return node;
    }

    @Override
    protected int function(List<Integer> arguments) {
        if (arguments.get(0) >= 1 && arguments.get(1) >= 1){
            return 1;
        }
        return 0;
    }
}
