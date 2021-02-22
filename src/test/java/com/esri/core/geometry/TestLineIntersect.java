package com.esri.core.geometry;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TestLineIntersect {

    private static final double PARAM_PRECISION = 0.01;


    @Test
    public void nonNullPointsLineIntersectStartLineTwo(){
        // %%% Covers Branch ID 6 %%%
        // set up line 1
        double x1 = 3.0, y1 = 3.0;
        double x2 = 6.0, y2 = 3.0;
        Line l1 = new Line(x1, y1, x2, y2);
        // set up line 2
        double x3 = 5.0, y3 = 3.0;
        double x4 = 5.0, y4 = 4.0;
        Line l2 = new Line(x3, y3, x4, y4);
        // param arrays
        double[] params1 = new double[1];
        double[] params2 = new double[1];
        // distance threshold
        double tolerance = 0.1;
        // intersection points
        Point2D[] intersection = new Point2D[1];
        int counter = l1._intersectLineLine(l1, l2, intersection, params1, params2, tolerance);
        assertEquals(1, counter);
        assertEquals(0.67, params1[0], PARAM_PRECISION);
        assertEquals(0.0, params2[0], PARAM_PRECISION);
        assertEquals(l2.getStartX(), intersection[0].x, PARAM_PRECISION);
        assertEquals(l2.getStartY(), intersection[0].y, PARAM_PRECISION);
    }


    @Test
    public void nonNullPointsLineIntersectEndLineTwo(){
        // %%% Covers Branch ID 14 %%%
        // set up line 1
        double x1 = 3.0, y1 = 4.0;
        double x2 = 7.0, y2 = 4.0;
        Line l1 = new Line(x1, y1, x2, y2);
        // set up line 2
        double x3 = 4.0, y3 = 3.0;
        double x4 = 4.0, y4 = 4.0;
        Line l2 = new Line(x3, y3, x4, y4);
        // param arrays
        double[] params1 = new double[1];
        double[] params2 = new double[1];
        // distance threshold
        double tolerance = 0.1;
        // intersection points
        Point2D[] intersection = new Point2D[1];
        int counter = l1._intersectLineLine(l1, l2, intersection, params1, params2, tolerance);
        assertEquals(1, counter);
        assertEquals(0.25, params1[0], PARAM_PRECISION);
        assertEquals(1.0, params2[0], PARAM_PRECISION);
        assertEquals(l2.getEndX(), intersection[0].x, PARAM_PRECISION);
        assertEquals(l2.getEndY(), intersection[0].y, PARAM_PRECISION);
    }

    @Test
    public void nonNullPointsLineIntersectStartLineOne(){
        // %%% Covers Branch ID 23 %%%
        // set up line 1
        double x1 = 3.0, y1 = 3.5;
        double x2 = 5.0, y2 = 3.5;
        Line l1 = new Line(x1, y1, x2, y2);
        // set up line 2
        double x3 = 3.0, y3 = 3.0;
        double x4 = 3.0, y4 = 4.0;
        Line l2 = new Line(x3, y3, x4, y4);
        // param arrays
        double[] params1 = new double[1];
        double[] params2 = new double[1];
        // distance threshold
        double tolerance = 0.1;
        // intersection points
        Point2D[] intersection = new Point2D[1];
        int counter = l1._intersectLineLine(l1, l2, intersection, params1, params2, tolerance);
        assertEquals(1, counter);
        assertEquals(0.0, params1[0], PARAM_PRECISION);
        assertEquals(0.5, params2[0], PARAM_PRECISION);
        assertEquals(l1.getStartX(), intersection[0].x, PARAM_PRECISION);
        assertEquals(l1.getStartY(), intersection[0].y, PARAM_PRECISION);
    }

    @Test
    public void nonNullPointsLineIntersectEndLineOne(){
        // %%% Covers Branch ID 33 %%%
        // set up line 1
        double x1 = 3.0, y1 = 3.5;
        double x2 = 5.0, y2 = 3.5;
        Line l1 = new Line(x1, y1, x2, y2);
        // set up line 2
        double x3 = 5.0, y3 = 3.0;
        double x4 = 5.0, y4 = 4.0;
        Line l2 = new Line(x3, y3, x4, y4);
        // param arrays
        double[] params1 = new double[1];
        double[] params2 = new double[1];
        // distance threshold
        double tolerance = 0.1;
        // intersection points
        Point2D[] intersection = new Point2D[1];
        int counter = l1._intersectLineLine(l1, l2, intersection, params1, params2, tolerance);
        assertEquals(1, counter);
        assertEquals(1.0, params1[0], PARAM_PRECISION);
        assertEquals(0.5, params2[0], PARAM_PRECISION);
        assertEquals(l1.getEndX(), intersection[0].x, PARAM_PRECISION);
        assertEquals(l1.getEndY(), intersection[0].y, PARAM_PRECISION);
        // bug ??
        // should probably be end of line1, not line2? see Line.java:1007
        // adjusted in source code
        //System.err.println(l1.getEndY());
        //System.err.println(intersection[0].y);
    }

}
