package edu.university.cs.reporting;

import org.jgrapht.DirectedGraph;

import edu.university.cs.Parser_2;
import edu.university.cs.automatonModel.A_Model;
import edu.university.cs.automatonModel.Model_Concrete_Singleton_Manager;
import edu.university.cs.graph.PrintConstraint;
import edu.university.cs.graph.SymbolicEdge;
import edu.university.cs.solvers.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Reporter_SAT<T extends A_Model<T>> extends A_Reporter<T> {

    public Reporter_SAT(DirectedGraph<PrintConstraint, SymbolicEdge> graph,
                       Parser_2<T> parser,
                       Solver<T> solver,
                       boolean debug) {

        super(graph, parser, solver, debug);
    }

    @Override
    protected void outputHeader() {

        // gather headers in list
        List<String> headers = new ArrayList<>();
        headers.add("ID");
        headers.add("SING");
        headers.add("TSAT");
        headers.add("FSAT");
        headers.add("DISJ");
        headers.add("PREV OPS");

        // generate headers string
        String header = joinStrings(headers, "\t");

        // output header
        System.out.println(header);
    }


    protected void calculateStats(PrintConstraint constraint) {

        // get constraint info as variables
        Map<String, Integer> sourceMap = constraint.getSourceMap();
        String actualVal = constraint.getActualVal();
        int base = sourceMap.get("t");

        // get id of second symbolic string if it exists
        int arg = -1;
        if (sourceMap.get("s1") != null) {
            arg = sourceMap.get("s1");
        }

        // initialize boolean flags
        boolean isSingleton = false;
        boolean trueSat = false;
        boolean falseSat = false;

        

        // store symbolic string values
        solver.setLast(base, arg);
        
 

        // test if true branch is SAT
        parser.assertBooleanConstraint(true, constraint);
        if (solver.isSatisfiable(base)) {
            trueSat = true;
        }

        // revert symbolic string values
        solver.revertLastPredicate();

        // store symbolic string values
        solver.setLast(base, arg);

        // test if false branch is SAT
        parser.assertBooleanConstraint(false, constraint);
        //System.out.println("base " + base + " " + constraint);
        if (solver.isSatisfiable(base)) {
            falseSat = true;
        }

        // revert symbolic string values
        solver.revertLastPredicate();

        // if actual execution did not produce either true or false
        if (!actualVal.equals("true") && !actualVal.equals("false")) {

            System.err.println("warning constraint detected without " +
                               "true/false value");
            return;
        }

        // determine result of actual execution
        boolean result = true;
        if (actualVal.equals("false")) {
            result = false;
        }

        // branches disjoint?
        parser.assertBooleanConstraint(result, constraint);

        // store symbolic string values
        solver.setLast(base, arg);

        parser.assertBooleanConstraint(!result, constraint);

        // set yes or no for disjoint branches
        String disjoint = "yes";
        if (solver.isSatisfiable(base)) {
            disjoint = "no";
        }
        
        // revert symbolic string values
        solver.revertLastPredicate();
// determine if symbolic strings are singletons
        
        boolean argIsSingleton = false;
  // determine if symbolic strings are singletons
        
        if (arg != -1) {
        	argIsSingleton = solver.isSingleton(sourceMap.get("s1"));
        }
        
        
        //base would have a string value, which is not actualVal
      
        if (solver.isSingleton(base) &&
            (sourceMap.get("s1") == null || argIsSingleton)) {
            isSingleton = true;
        }

  
        

      
     

        // get constraint function name
        String constName = constraint.getSplitValue().split("!!")[0];

        // add boolean operation to operation list
        addBooleanOperation(base, arg, constName, constraint.getId(), argIsSingleton);

        // get operations
        String[] opsArray = this.operationsMap.get(base);
        String ops = joinStrings(Arrays.asList(opsArray), " -> ");

        // gather column data in list
        List<String> columns = new ArrayList<>();
        // id
        columns.add(String.valueOf(constraint.getId()));
        // is singleton?
        columns.add(String.valueOf(isSingleton));
        // true sat?
        String trueSatPrint = String.valueOf(trueSat);
        if(solver.modelManager instanceof Model_Concrete_Singleton_Manager && actualVal.equals("true")) {
        	trueSatPrint+="*";
        }
        columns.add(trueSatPrint);
        // false sat?
        String falseSatPrint = String.valueOf(falseSat);
        if(solver.modelManager instanceof Model_Concrete_Singleton_Manager && actualVal.equals("false")) {
        	falseSatPrint+="*";
        }
        columns.add(falseSatPrint);
        // disjoint?
        columns.add(String.valueOf(disjoint));
        // previous operations
        columns.add(ops);

        // generate row string
        String row = joinStrings(columns, "\t");

        // output row
        System.out.println(row);

    }

	@Override
	protected void solveInputs() {
		// TODO Auto-generated method stub
		
	}
}
