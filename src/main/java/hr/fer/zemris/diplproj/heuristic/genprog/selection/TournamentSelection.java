package hr.fer.zemris.diplproj.heuristic.genprog.selection;

import hr.fer.zemris.diplproj.Config;
import hr.fer.zemris.diplproj.heuristic.genprog.Solution;

import java.util.List;

/**
 * Implementation of tournament selection.
 *
 * @author Kristijan Vulinovic
 */
public class TournamentSelection implements ISelection {
    /**
     * The number of solutions that take part in the tournament.
     */
    private int tournamentSize;

    /**
     * Creates a new {@link TournamentSelection} with the given tournamentSize.
     *
     * @param tournamentSize the number of solutions to take part in the tournament.
     */
    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    @Override
    public Solution select(List<Solution> population) {
        Solution best = null;

        int n = population.size();
        for (int i = 0; i < tournamentSize; ++i){
            Solution chosen = population.get(Config.getInstance().getRnd().nextInt(n));
            if (best == null || best.getFitness() < chosen.getFitness()){
                best = chosen;
            }
        }

        return best;
    }
}
