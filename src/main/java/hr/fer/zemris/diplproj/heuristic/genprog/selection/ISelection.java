package hr.fer.zemris.diplproj.heuristic.genprog.selection;

import hr.fer.zemris.diplproj.heuristic.genprog.Solution;

import java.util.List;

/**
 * Interface used for selection in genetic programming.
 *
 * @author Kristijan Vulinovic
 */
public interface ISelection {
    Solution select(List<Solution> population);
}
