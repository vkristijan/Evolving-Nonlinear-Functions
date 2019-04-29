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
        nodeCount = 1;
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
        childNode.setParent(this);
        nodeCount += childNode.nodeCount();
    }

    @Override
    public void setChild(INode childNode, int index) {
        if (index < 0 || index > children.size()){
            throw new IndexOutOfBoundsException("The node doesn't contain a child at the given index!");
        }

        nodeCount -= children.get(index).nodeCount();
        children.set(index, childNode);
        childNode.setParent(this);
        nodeCount += childNode.nodeCount();
    }

    @Override
    public int evaluate(List<Integer> inputs) {
        if (children.size() != childrenCount()){
            throw new IllegalStateException("The node doesn't have all it's children assigned!");
        }

        List<Integer> childValues = new ArrayList<>();
        children.forEach(c -> childValues.add(c.evaluate(inputs)));
        return function(childValues);
    }

    protected abstract int function(List<Integer> arguments);
}
