package hr.fer.zemris.diplproj.heuristic.genprog.node;

/**
 * Implementation of basic <code>INode</code> methods shared with all node types.
 *
 * @author Kristijan Vulinovic
 */
public abstract class AbstractNode implements INode {
    /**
     * The depth of the node.
     */
    protected int depth;

    /**
     * The parent of the node.
     */
    protected INode parent;

    @Override
    public INode getParent() {
        return parent;
    }

    @Override
    public void setParent(INode parent) {
        this.parent = parent;
    }

    @Override
    public int getDepth() {
        return depth;
    }
}
