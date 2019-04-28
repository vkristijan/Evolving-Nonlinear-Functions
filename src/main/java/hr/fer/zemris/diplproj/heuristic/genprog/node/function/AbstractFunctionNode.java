package hr.fer.zemris.diplproj.heuristic.genprog.node.function;

import hr.fer.zemris.diplproj.heuristic.genprog.node.AbstractNode;
import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of methods handling child nodes for function nodes.
 *
 * @author Kristijan Vulinovic
 */
public abstract class AbstractFunctionNode extends AbstractNode {
    /**
     * List of child nodes.
     */
    protected List<INode> children;

    /**
     * Creates an empty function node.
     */
    public AbstractFunctionNode(){
        children = new ArrayList<>();
    }

    @Override
    public List<INode> getChildren() {
        return null;
    }

    @Override
    public void addChild(INode childNode) {
        if (children.size() >= childrenCount()){
            throw new IndexOutOfBoundsException("The node already has all it's children!");
        }

        children.add(childNode);
    }

    @Override
    public void setChild(INode childNode, int index) {
        if (index < 0 || index > children.size()){
            throw new IndexOutOfBoundsException("The node doesn't contain a child at the given index!");
        }

        children.set(index, childNode);
    }

}
