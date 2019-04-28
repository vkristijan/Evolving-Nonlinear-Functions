package hr.fer.zemris.diplproj.heuristic.genprog.node.terminal;

import hr.fer.zemris.diplproj.heuristic.genprog.node.AbstractNode;
import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of methods handling child nodes for terminal nodes.
 * As this node is terminal, it doesn't support child nodes.
 *
 * @author Kristijan Vulinovic
 */
public abstract class AbstractTerminalNode extends AbstractNode {
    @Override
    public int childrenCount() {
        return 0;
    }

    @Override
    public List<INode> getChildren() {
        return new ArrayList<>();
    }

    @Override
    public void addChild(INode childNode) {
        throw new IndexOutOfBoundsException("The node doesn't support children.");
    }

    @Override
    public void setChild(INode childNode, int index) {
        throw new IndexOutOfBoundsException("The node doesn't support children.");

    }
}
