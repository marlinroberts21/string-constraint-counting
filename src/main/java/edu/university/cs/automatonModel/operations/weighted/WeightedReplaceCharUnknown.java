package edu.university.cs.automatonModel.operations.weighted;

import java.util.ArrayList;
import java.util.Set;

import edu.university.cs.automaton.WeightedAutomaton;
import edu.university.cs.automaton.WeightedState;
import edu.university.cs.automaton.WeightedTransition;

public class WeightedReplaceCharUnknown extends UnaryWeightedOperation {
    @Override
    public String toString() {
        return "replace(?, ?)";
    }

    @Override
    public WeightedAutomaton op(WeightedAutomaton a) {
        WeightedAutomaton b = a.clone();
        for (WeightedState s : b.getStates()) {
            Set<WeightedTransition> transitions = s.getTransitions();
            for (WeightedTransition t : new ArrayList<>(transitions)) {
                WeightedState dest = t.getDest();
                transitions.remove(t);
                s.addTransition(new WeightedTransition(Character.MIN_VALUE, Character.MAX_VALUE, dest, t.getWeightInt()));
            }
        }
        b.setDeterministic(false);
        b.reduce();
        return b;
    }
}
