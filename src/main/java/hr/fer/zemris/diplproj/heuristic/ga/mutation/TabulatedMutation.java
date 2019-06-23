package hr.fer.zemris.diplproj.heuristic.ga.mutation;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.heuristic.common.neighborhood.TabulatedNeighbor;

public class TabulatedMutation implements IMutation {
    private TabulatedNeighbor tabulated;

    public TabulatedMutation(){
        tabulated = new TabulatedNeighbor(4);
    }

    @Override
    public void mutate(BoolFunction f) {
        BoolFunction mutated = tabulated.getNeighbor(f);

        int n = f.getTruthTable().size();
        for (int i = 0; i < n; ++i){
            f.getTruthTable().set(i, mutated.getTruthTable().get(i));
        }
    }
}
