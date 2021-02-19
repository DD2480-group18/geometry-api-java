package com.esri.coverage;

import org.junit.Test;

import java.util.Iterator;

/**
 * Tests the branch coverage as counted by com.esri.coverage.Coverage.java in the project's original test suite.
 * Prints the ID of the branches that were never taken in the original test suite, then prints the estimated percentage
 * of branch coverage. Finally it prints the HashMap that shows how many times the different branches have been taken.
 */
public class TestZCoverage {
    @Test
    public void coverage(){
        int takenBranches = 0;
        int numBranches = 52;
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        for (int id = 1; id <= numBranches; id++) {
            Integer val = Coverage.coverageMap.get(id);
            if(val == null){
                System.out.printf("Branch with ID %d is never taken\n", id);
                continue;
            }
            if(val > 0)
                takenBranches++;
        }
        System.out.printf("Coverage estimated to be %.2f%%\n", 100*(double)takenBranches/numBranches);
        System.out.println("====");
        System.out.println("Mapping between ID:NumTimesBranchTaken:");
        System.out.println(Coverage.coverageMap.toString());
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }
}
