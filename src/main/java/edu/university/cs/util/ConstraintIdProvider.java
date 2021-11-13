package edu.university.cs.util;

import org.jgrapht.ext.VertexNameProvider;

import edu.university.cs.graph.PrintConstraint;

public class ConstraintIdProvider implements VertexNameProvider<PrintConstraint> {

    @Override
    public String getVertexName(PrintConstraint constraint) {
        return String.valueOf(constraint.getId());
    }
}
