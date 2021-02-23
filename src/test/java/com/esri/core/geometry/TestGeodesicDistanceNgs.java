package com.esri.core.geometry;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test class is implemented to raise the branch coverage of GeoDist.geodesic_distance_ngs.
 * Branch coverage before these test were implemented was: 34.52%
 * Branch coverage after these tests are implemented is: 46.43%
 * Code coverage before tests was: 56.2% (OpenClover)
 * Code coverage after tests is: 65.4% (OpenClover)
 */

public class TestGeodesicDistanceNgs {

    /**
     * The first if-statement should fire when PeDouble
     * parameters p_dist, p_az12 and p_az21 are all null, and no
     * NullPointerException should be thrown
     */
    @Test
    public void invalidInput() {
        final double a = 0;
        final double e2 = 0;
        final double lam1 = 0;
        final double phi1 = 0;
        final double lam2 = 0;
        final double phi2 = 0;
        final PeDouble p_dist = null;
        final PeDouble p_az12 = null;
        final PeDouble p_az21 = null;
        GeoDist.geodesic_distance_ngs(a, e2, lam1, phi1, lam2, phi2, p_dist, p_az12, p_az21);
        assertNull(p_dist);
        assertNull(p_az12);
        assertNull(p_az21);
    }

    /**
     * If the input points are the same, the p_dist, p_az12 and p_az21
     * variables should all contain the value 0.0.
     */
    @Test
    public void pointsAreTheSame() {
        double a = 0;
        double e2 = 0;
        double lam1 = 0;
        double phi1 = 0;
        double lam2 = 0;
        double phi2 = 0;
        PeDouble p_dist = new PeDouble(-65);
        PeDouble p_az12 = new PeDouble(-64);
        PeDouble p_az21 = new PeDouble(-63);
        GeoDist.geodesic_distance_ngs(a, e2, lam1, phi1, lam2, phi2, p_dist, p_az12, p_az21);
        assertEquals(0, Double.compare(0.0, p_dist.val));
        assertEquals(0, Double.compare(0.0, p_dist.val));
        assertEquals(0, Double.compare(0.0, p_dist.val));
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
        final double PE_PI = 3.14159265358979323846264;
        final double PE_PI2 = 1.57079632679489661923132;
        final double a = 0;
        final double e2 = 0;
        final double lam1 = 0;
        final double phi1 = -PE_PI2;
        final double lam2 = 0;
        PeDouble p_dist = new PeDouble(-65);
        PeDouble p_az12 = new PeDouble(-64);
        PeDouble p_az21 = new PeDouble(-63);
        GeoDist.geodesic_distance_ngs(a, e2, lam1, phi1, lam2, PE_PI2, p_dist, p_az12, p_az21);
        assertEquals(0, Double.compare(2.0*GeoDist.public_q90(a, e2), p_dist.val));
        assertEquals(0, Double.compare(GeoDist.public_lam_delta(lam2), p_az12.val));
        assertEquals(0, Double.compare(GeoDist.public_lam_delta(PE_PI-GeoDist.public_lam_delta(lam2)), p_az21.val));
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
        final double PE_PI = 3.14159265358979323846264;
        final double PE_PI2 = 1.57079632679489661923132;
        final double a = 1;
        final double e2 = 0;
        final double lam1 = 5;
        final double phi1 = -PE_PI2;
        final double lam2 = 10;
        final double phi2 = PE_PI2-1;
        PeDouble p_dist = new PeDouble(-65);
        PeDouble p_az12 = new PeDouble(-64);
        PeDouble p_az21 = new PeDouble(-63);
        PeDouble lam = new PeDouble(lam2), phi = new PeDouble(phi2);
        GeoDist.public_lam_phi_reduction(lam, phi);
        double dlam = GeoDist.public_lam_delta(lam2 - lam1);
        double cos_phi1 = Math.cos(phi1), cos_phi2 = Math.cos(phi2);
        double sin_phi1 = Math.sin(phi1), sin_phi2 = Math.sin(phi2);
        double tem1 = Math.sin((phi1-phi2)/2.0);
        double tem2 = Math.sin((dlam)/2.0);
        double sigma = 2.0 * Math.asin(Math.sqrt(tem1 * tem1 + cos_phi1 * cos_phi2 * tem2 * tem2));
        double expected_p_az21 = GeoDist.public_lam_delta(Math.atan2(cos_phi1 * Math.sin(dlam), sin_phi2
                * cos_phi1 * Math.cos(dlam) - cos_phi2 * sin_phi1) + PE_PI);
        GeoDist.geodesic_distance_ngs(a, e2, lam1, phi1, lam2, phi2, p_dist, p_az12, p_az21);


        assertEquals(0, Double.compare(sigma, p_dist.val));
        assertEquals(0, Double.compare(lam.val, p_az12.val));
        assertEquals(0, Double.compare(expected_p_az21, p_az21.val));
    }

}
