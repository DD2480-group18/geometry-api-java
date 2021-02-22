package com.esri.core.geometry;

import org.junit.Test;

import junit.framework.TestCase;

import static org.junit.Assert.*;

public class TestWkt {
	/**
	 * Test that gets tolerance from input when PROJCS
	 */
	@Test
	public void testPROJCS(){
		String input = "[PROJCS UNIT , 2 ]]";
		double result = Wkt.find_tolerance_from_wkt(input);
		assertEquals(Double.compare(result, 0.001/2), 0);
	}

	/**
	 * Test that gets tolerance from input when GEOGCS
	 */
	@Test
	public void testGEOGCS(){
		String input = "[GEOGCS SPHEROID , 2 , UNIT , 2 ]]";
		double result = Wkt.find_tolerance_from_wkt(input);
		assertEquals(Double.compare(result, 0.001/4), 0);
	}

	/**
	 * Test that makes sure GEOGCS is a SPHEROID
	 */
	@Test
	public void testNotSPHEROID(){
		String input = "[GEOGCS SMTH , 2 , UNIT , 2 ]]";
		double result = Wkt.find_tolerance_from_wkt(input);
		assertEquals(Double.compare(result, -1), 0);
	}

	/**
	 * Test that makes sure that -1 is returned when a null string is sent as argument
	 */
	@Test
	public void testNull(){
		double result = Wkt.find_tolerance_from_wkt(null);
		assertEquals(Double.compare(result, -1), 0);
	}

	/**
	 * This test makes sure that when axis is <= 0, -1 is returned
	 */
	@Test
	public void testSmallAxis(){
		String input = "[GEOGCS SPHEROID , 0 , UNIT , 2 ]]";
		double result = Wkt.find_tolerance_from_wkt(input);
		assertEquals(Double.compare(result, -1), 0);
	}
}
