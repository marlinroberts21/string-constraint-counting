package edu.university.cs.automatonModel.operations.weighted;

import java.util.HashSet;
import java.util.Set;

import edu.university.cs.automaton.BasicWeightedAutomata;
import edu.university.cs.automaton.WeightedAutomaton;
import edu.university.cs.automaton.WeightedState;
import edu.university.cs.automaton.WeightedStatePair;

public class WeightedAllSubstrings extends UnaryWeightedOperation {
    @Override
    public String toString() {
        return "allSubstrings";
    }

    @Override
    public WeightedAutomaton op(WeightedAutomaton automaton) {
        if (automaton.isEmpty()) {
            return BasicWeightedAutomata.makeEmpty();
        }
        WeightedAutomaton clone = automaton.clone();
        WeightedState initial = new WeightedState();
        WeightedState accept = new WeightedState();
        accept.setAccept(true);
        Set<WeightedStatePair> epsilons = new HashSet<>();
        for (WeightedState state : clone.getStates()) {
            epsilons.add(new WeightedStatePair(initial, state));
            epsilons.add(new WeightedStatePair(state, accept));
        }
        clone.setInitialState(initial);
        clone.addEpsilons(epsilons);
        clone.minimize();
        return clone;
    }
}
