package com.esri.core.geometry;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test class is implemented to raise the branch coverage of GeoDist.geodesic_distance_ngs.
 * Branch coverage before these test were implemented was: 34.52%
 * Code coverage before tests was: 56.2% (OpenClover)
 */

public class TestGeodesicDistanceNgs {

    /**
     * The first if-statement should fire when PeDouble
     * parameters p_dist, p_az12 and p_az21 are all null, and no
     * NullPointerException should be thrown
     */
    @Test
    public void invalidInput() {
    }

    /**
     * If the input points are the same, the p_dist, p_az12 and p_az21
     * variables should all contain the value 0.0.
     */
    @Test
    public void pointsAreTheSame() {
    }

    /**
     * Special branch if points are perfectly antipodal i.e. the shortest
     * distance from one point to the other is a line that passes through
     * the center of the earth.
     *
     * Need public versions of: q90 and lam_delta
     */
    @Test
    public void pointsPerfectlyAntipodal() {
    }

    /**
     * There is a branch that deals with spheres and a subbranch of that one
     * checks if a point is originated at either the north or the south pole
     * and if the destination is at either the north or south pole.
     * Need public version of lam_phi_reduction
     *
     * if the origin and destination is on the south pole (phi1 = phi2 = -PE_PI2):
     * p_dist should be zero
     * p_az12 should contain the value of lam2
     * p_az21 should contain the value of lam1
     */
    @Test
    public void sphere() {
    }

}
