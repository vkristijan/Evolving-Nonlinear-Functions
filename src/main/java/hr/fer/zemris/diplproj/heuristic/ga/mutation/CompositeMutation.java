package hr.fer.zemris.diplproj.heuristic.ga.mutation;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompositeMutation implements IMutation {
    private List<IMutation> mutations;

    public CompositeMutation(IMutation ... mutations){
        this.mutations = new ArrayList<>();
        this.mutations.addAll(Arrays.asList(mutations));
    }

    @Override
    public void mutate(BoolFunction f) {
        int n = mutations.size();
        int index = Config.getInstance().getRnd().nextInt(n);

        mutations.get(index).mutate(f);
    }
}
