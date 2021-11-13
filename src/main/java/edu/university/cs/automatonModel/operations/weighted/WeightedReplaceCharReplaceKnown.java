package edu.university.cs.automatonModel.operations.weighted;

import java.util.ArrayList;
import java.util.Set;

import edu.university.cs.automaton.WeightedAutomaton;
import edu.university.cs.automaton.WeightedState;
import edu.university.cs.automaton.WeightedTransition;

public class WeightedReplaceCharReplaceKnown
        extends UnaryWeightedOperation {

    private char replace;

    public WeightedReplaceCharReplaceKnown(char replace) {
        this.replace = replace;
    }

    @Override
    public String toString() {
        return "replace(?, '" + replace + "')";
    }

    @Override
    public WeightedAutomaton op(WeightedAutomaton a) {
        WeightedAutomaton b = a.clone();
        for (WeightedState s : b.getStates()) {
            Set<WeightedTransition> transitions = s.getTransitions();
            for (WeightedTransition t : new ArrayList<>(transitions)) {
                WeightedState dest = t.getDest();
                s.addTransition(new WeightedTransition(replace, dest, t.getWeightInt()));
            }
        }
        b.setDeterministic(false);
        b.reduce();
        return b;
    }
}
