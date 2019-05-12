package hr.fer.zemris.diplproj.heuristic.genprog.crossover;

import hr.fer.zemris.diplproj.heuristic.genprog.Solution;
import hr.fer.zemris.diplproj.heuristic.genprog.Util;
import hr.fer.zemris.diplproj.heuristic.genprog.node.INode;

public class SubtreeChangeCrossover implements ICrossover {
    @Override
    public Solution crossover(Solution a, Solution b) {
        INode root = a.getRoot().copy();
        INode toChange = Util.getRandomNode(root);
        INode change = Util.getRandomNode(b.getRoot()).copy();

        INode parent = toChange.getParent();
        for (int i = 0; i < parent.childrenCount(); ++i){
            if (parent.getChild(i) == toChange){
                parent.setChild(change, i);
                break;
            }
        }

        return new Solution(root);
    }
}
