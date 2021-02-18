package com.esri.core.geometry;

import org.junit.Test;

public class TestZZZfinalTest {
	@Test
	public void testNative(){
		int T = 0;
		int F = 0;
		for (int i = 0; i < OperatorIntersectionCursor.IDs.length; i++) {
			System.out.println(i + ": " + OperatorIntersectionCursor.IDs[i]);
			if (OperatorIntersectionCursor.IDs[i])++T;
			else ++F;
		}
		System.out.println("Branches covered: " + T);
		System.out.println("Branches not covered: " + F);
		System.out.println("percentage: " + ((double)T/(F+T)));
	}
}
