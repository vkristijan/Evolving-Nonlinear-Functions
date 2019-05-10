package hr.fer.zemris.diplproj.heuristic.ga.selection;

import hr.fer.zemris.diplproj.BoolFunction;

import java.util.List;

public interface ISelection {
    BoolFunction select(List<BoolFunction> population);
}
