package edu.university.cs.automatonModel.operations;

import edu.university.cs.automaton.WeightedAutomaton;

public interface BinaryWeightedAutomatonOp {
    WeightedAutomaton op(WeightedAutomaton a1, WeightedAutomaton a2);
}
