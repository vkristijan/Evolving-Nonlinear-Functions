package hr.fer.zemris.diplproj.heuristic.ga.mutation;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.heuristic.common.neighborhood.WalshNeighbor;

public class BalancedWalshMutation implements IMutation {
    private WalshNeighbor walsh;

    public BalancedWalshMutation(){
        walsh = new WalshNeighbor();
    }

    @Override
    public void mutate(BoolFunction f) {
        BoolFunction mutated = walsh.getNeighbor(f);

        int n = f.getTruthTable().size();
        int changed = 0;
        for (int i = 0; i < n; ++i){
            if (!f.getTruthTable().get(i).equals(mutated.getTruthTable().get(i))) {
                changed = mutated.getTruthTable().get(i);
            }
            f.getTruthTable().set(i, mutated.getTruthTable().get(i));
        }

        while (true){
            int index = Config.getInstance().getRnd().nextInt(n);
            if (f.getTruthTable().get(index) == changed){
                f.getTruthTable().set(index, 1 - changed);
                return;
            }
        }
    }
}
