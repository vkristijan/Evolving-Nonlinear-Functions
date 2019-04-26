package hr.fer.zemris.diplproj.heuristic.genprog.node;

import java.util.List;

/**
 * A node in the genetic programming solution.
 * Nodes are structured as a tree, forming a boolean function.
 *
 * @author Kristijan Vulinovic
 */
public interface INode {
    /**
     * Returns the depth at which this node is placed.
     * @return the depth of the node.
     */
    int getDepth();

    /**
     * Returns the parent node if it exists, and null otherwise (root node).
     * @return the parent node.
     */
    INode getParent();

    /**
     * Sets the parent to the given <code>INode</code>.
     *
     * @param parent the new parent
     */
    void setParent(INode parent);

    /**
     * Returns the expected number of children for this node.
     *
     * @return the expected number of children for this node.
     */
    int childrenCount();

    /**
     * Returns a list containing the children of the node.
     *
     * @return <code>List</code> containing all the children of the node.
     */
    List<INode> getChildren();

    /**
     * Adds he given child to the current node.
     *
     * @param childNode The child node to be inserted.
     * @throws IndexOutOfBoundsException in case of the node already having the maximum number of children.
     */
    void addChild(INode childNode);

    /**
     * Sets the child at the index <code>index</code> of the current node to the one given.
     *
     * @param childNode The new child node.
     * @param index Index of the child to replace.
     *
     * @throws IndexOutOfBoundsException in case of the index being outside the acceptable bounds.
     */
    void setChild(INode childNode, int index);

    /**
     * Evaluates the current node and calculates the value of the function it represents.
     * The value can be either true (1) or false (0), depending on the function.
     *
     * @param inputs the values of the function input
     * @return an integer value representing the function output.
     */
    int evaluate(List<Integer> inputs);
}
