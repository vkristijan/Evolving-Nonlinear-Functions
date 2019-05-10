package hr.fer.zemris.diplproj.heuristic.ga.selection;

import hr.fer.zemris.diplproj.BoolFunction;
import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.heuristic.genprog.Solution;

import java.util.List;
import java.util.Random;

public class TournamentSelection implements ISelection {
    private int tournamentSize;

    public TournamentSelection(int tournamentSize){
        this.tournamentSize = tournamentSize;
    }

    @Override
    public BoolFunction select(List<BoolFunction> population) {
        Random rnd = Config.getInstance().getRnd();
        BoolFunction best = null;

        int n = population.size();
        for (int i = 0; i < tournamentSize; ++i){
            BoolFunction chosen = population.get(rnd.nextInt(n));
            if (best == null || best.getFitness() < chosen.getFitness()){
                best = chosen;
            }
        }

        return best;
    }
}
