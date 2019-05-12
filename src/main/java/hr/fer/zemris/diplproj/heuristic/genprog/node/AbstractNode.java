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
     * Number of nodes in the subtree.
     */
    protected int nodeCount;

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
    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int getDepth() {
        return depth;
    }

    @Override
    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    @Override
    public int nodeCount() {
        if (nodeCount <= 0){
            nodeCount = 1;
            for (var child : getChildren()){
                nodeCount += child.nodeCount();
            }
        }
        return nodeCount;
    }

    @Override
    public void updateDepth(int depth) {
        this.depth = depth;
        getChildren().forEach(c -> c.updateDepth(depth + 1));
    }

    @Override
    public INode deepCopy() {
        INode node = copy();
        getChildren().forEach(c -> node.addChild(c.deepCopy()));
        node.setDepth(depth);
        node.setNodeCount(nodeCount);

        return node;
    }

    public abstract INode copy();
}
