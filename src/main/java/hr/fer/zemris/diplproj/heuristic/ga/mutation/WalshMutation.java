package hr.fer.zemris.diplproj.heuristic.ga.mutation;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.heuristic.common.neighborhood.WalshNeighbor;

public class WalshMutation implements IMutation {
    private WalshNeighbor walsh;

    public WalshMutation(){
        walsh = new WalshNeighbor();
    }

    @Override
    public void mutate(BoolFunction f) {
        BoolFunction mutated = walsh.getNeighbor(f);

        int n = f.getTruthTable().size();
        for (int i = 0; i < n; ++i){
            f.getTruthTable().set(i, mutated.getTruthTable().get(i));
        }
    }
}
