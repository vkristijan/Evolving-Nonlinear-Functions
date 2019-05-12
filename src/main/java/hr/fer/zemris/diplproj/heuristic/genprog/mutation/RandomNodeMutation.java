package hr.fer.zemris.diplproj.heuristic.genprog.mutation;

import hr.fer.zemris.diplproj.heuristic.genprog.Solution;
import hr.fer.zemris.diplproj.heuristic.genprog.Util;
import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;

public class RandomNodeMutation implements IMutation {
    private int maxNodes;
    private int maxDepth;

    public RandomNodeMutation(int maxNodes, int maxDepth) {
        this.maxNodes = maxNodes;
        this.maxDepth = maxDepth;
    }

    @Override
    public Solution mutate(Solution solution) {
        INode root = solution.getRoot();
        root.updateDepth(1);

        INode node = Util.getRandomNode(root);

        if (node.getParent() == null){
            INode newNode = Util.generateTree(maxDepth, maxNodes);
            return new Solution(newNode);
        }

        INode parent = node.getParent();
        int index = 0;
        for (int i = 0; i < parent.childrenCount(); ++i){
            if (parent.getChild(i) == node){
                index = i;
                break;
            }
        }

        INode newNode = Util.generateTree(
                maxDepth - node.getDepth(),
                maxNodes - node.nodeCount()
        );
        node.getParent().setChild(newNode, index);
        solution.setRoot(root);
        return solution;
    }
}
