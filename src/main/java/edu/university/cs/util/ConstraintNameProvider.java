package edu.university.cs.util;

import org.jgrapht.ext.VertexNameProvider;

import edu.university.cs.graph.PrintConstraint;

public class ConstraintNameProvider implements
                                    VertexNameProvider<PrintConstraint> {

    @Override
    public String getVertexName(PrintConstraint constraint) {
        String value = constraint.getValue();
        value = value.replace("\\\"", "\"").replace("\"", "\\\"");
//        value = value.replaceAll("[^\\\\]\\\\\"", "\\\"");
        if(constraint.getActualVal().equals("false")) {
        	value="F:"+value;
        } else if (constraint.getActualVal().equals("true")){
        	value = "T:" + value;
        }
        return String.format("%s!-!%d", value, constraint.getId());
    }
}
