package edu.university.cs.util;

import org.jgrapht.ext.EdgeNameProvider;

import edu.university.cs.graph.SymbolicEdge;

public class EdgeInfoProvider implements EdgeNameProvider<SymbolicEdge> {

    @Override
    public String getEdgeName(SymbolicEdge symbolicEdge) {
        return symbolicEdge.getType();
    }
}
