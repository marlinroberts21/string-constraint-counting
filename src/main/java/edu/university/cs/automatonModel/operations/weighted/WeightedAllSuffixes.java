package edu.university.cs.automatonModel.operations.weighted;

import java.util.HashSet;
import java.util.Set;

import edu.university.cs.automaton.BasicWeightedAutomata;
import edu.university.cs.automaton.WeightedAutomaton;
import edu.university.cs.automaton.WeightedState;
import edu.university.cs.automaton.WeightedStatePair;

public class WeightedAllSuffixes
        extends UnaryWeightedOperation {
    @Override
    public String toString() {
        return "allSuffixes";
    }

    @Override
    public WeightedAutomaton op(WeightedAutomaton automaton) {
        WeightedAutomaton clone = automaton.clone();
        WeightedState initial = new WeightedState();
        Set<WeightedStatePair> epsilons = new HashSet<>();
        for (WeightedState state : clone.getStates()) {
            epsilons.add(new WeightedStatePair(initial, state));
        }
        clone.setInitialState(initial);
        clone.addEpsilons(epsilons);
        clone.minimize();
        return clone;
    }
}
