package edu.university.cs.automatonModel.operations.weighted;

import edu.university.cs.automaton.WeightedAutomaton;

abstract public class UnaryWeightedOperation extends WeightedOperation {

    /**
     * Unary operation on automata.
     *
     * @param automaton input automaton, should not be modified
     * @return output automaton
     */
    abstract public WeightedAutomaton op(WeightedAutomaton automaton);
}
