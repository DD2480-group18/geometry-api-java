package com.esri.core.geometry;

import org.junit.Test;

import static org.junit.Assert.*;

public class ZZZ {
    @Test
    public void printMap() {
        System.out.println(Cutter.map.toString());
    }
    @Test
    public void printCoverage() {
        boolean[] arr = GeoDist.flags;
        int visitedBranches = 0, totalBranches = arr.length;
        for (boolean b : arr) {
            visitedBranches = b ? visitedBranches + 1 : visitedBranches;
        }
        System.out.println("Visited branches: " + visitedBranches + "\nTotal branches: " + totalBranches + "\nCoverage: " + ((double)visitedBranches/totalBranches*100) + "%");
    }
}
