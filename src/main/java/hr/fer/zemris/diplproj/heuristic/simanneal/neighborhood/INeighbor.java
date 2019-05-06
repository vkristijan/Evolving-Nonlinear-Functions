package hr.fer.zemris.diplproj.heuristic.simanneal.neighborhood;

import hr.fer.zemris.diplproj.BoolFunction;

public interface INeighbor {
    BoolFunction getNeighbor(BoolFunction f);
}
