package edu.university.cs.automatonModel.operations;


import edu.university.cs.automaton.WeightedAutomaton;

public interface UnaryWeightedAutomatonOp {
    WeightedAutomaton op(WeightedAutomaton a1);
}
