package dd2480;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to measure the coverage for a function. We need a datastructure to store the information.
 * I suggest we use hashmap that has index as key and coverage class as value. The coverage
 * can either be a numeric value that indicates how many times it has been called or something similar.
 */

public class Coverage {
    static public HashMap<Integer, Data> branchCoverageMap = new HashMap<>();
    static public int totalNumberOfRuns = 0;
    static {
        for (int i = 0; i < 60; i++) {
            branchCoverageMap.put(i, new Data());
        }
    }

    public static void incrementTrueRun(int branchID) {
        branchCoverageMap.get(branchID).trueRun++;
    }

    public static void incrementFalseRun(int branchID) {
        branchCoverageMap.get(branchID).falseRun++;
    }

    public static void resetHasRun() {
        for ( Map.Entry<Integer, Data> entry : branchCoverageMap.entrySet() ) {
            entry.getValue().hasRun = false;
//            data.falseRun = totalNumberOfRuns - data.trueRun;
        }
    }

    public static void calculateFalseRuns() {
        for ( Map.Entry<Integer, Data> entry : branchCoverageMap.entrySet() ) {
            Data data = entry.getValue();
            data.falseRun = totalNumberOfRuns - data.trueRun;
        }
    }

    public static void printTrueRuns() {
//        calculateFalseRuns();
        System.out.println("Total number of runs: " + totalNumberOfRuns);
        for ( Map.Entry<Integer, Data> entry : branchCoverageMap.entrySet() ) {
            Integer index = entry.getKey();
            Data data = entry.getValue();

            System.out.println("Index : " + index + " True runs: : " + data.trueRun + " False runs: " + data.falseRun);
        }
    }
}


