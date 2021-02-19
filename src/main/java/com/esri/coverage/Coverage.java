package com.esri.coverage;

import java.util.HashMap;

/**
 * Counts the branch coverage of com.esri.core.geometry.Line.java::_intersectLineLine(6) in a mapping
 * between branch ID and number of times the branch is taken.
 */
public class Coverage {
    // counts the number of times=V a branch with ID=K is taken
    public static HashMap<Integer, Integer> coverageMap = new HashMap<>();

    /**
     * Increments the number of times a branch with ID <b>id</b> has been taken.
     * @param id The ID of the branch that was taken.
     */
    public static void incrementCoverage(Integer id){
        if(coverageMap.get(id) == null){
            coverageMap.put(id, 1);
        }else{
            Integer v = coverageMap.get(id);
            coverageMap.put(id, v+1);
        }
    }
}
