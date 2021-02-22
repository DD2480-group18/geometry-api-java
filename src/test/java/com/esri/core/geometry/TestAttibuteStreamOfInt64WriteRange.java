package com.esri.core.geometry;

import junit.framework.TestCase;
import java.lang.IllegalArgumentException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestAttibuteStreamOfInt64WriteRange {

    /**
     * Before these tests are implemented, the class TestAttributeStreamOfInt64 had
     * 0% code coverage and thus 0% branch coverage
     */
    @Test
    public void testWriteRangeInvalidInput() {
        final AttributeStreamOfInt64 source = new AttributeStreamOfInt64(10, 5);
        assertThrows(IllegalArgumentException.class,
                new ThrowingRunnable() {
                    @Override
                    public void run() throws Throwable {
                        source.writeRange(0, -1, source, 3, true, 2);
                    }
                });
        assertThrows(IllegalArgumentException.class,
                new ThrowingRunnable() {
                    @Override
                    public void run() throws Throwable {
                        source.writeRange(0, 6, source, 5, false, 5);
                    }
                });
        assertThrows(IllegalArgumentException.class,
                new ThrowingRunnable() {
                    @Override
                    public void run() throws Throwable {
                        source.writeRange(0, 6, source, 5, true, 5);
                    }
                });
    }

    @Test
    public void testSize() {
        AttributeStreamOfInt64 src = new AttributeStreamOfInt64(10, 5);
        src.writeRange(6, 10, src, 0, true, 1);
        assertEquals(16, src.size());
    }

    @Test
    public void testDifferentStreamsWithbForward() {
        AttributeStreamOfInt64 stream1 = new AttributeStreamOfInt64(15);
        for (int i = 0; i < 15; i++)
            stream1.write(i, i);
        AttributeStreamOfInt64 stream2 = new AttributeStreamOfInt64(15, 6);
        stream2.writeRange(5,6, stream1, 0, true, 1);
        int[] stream2arr = new int[15];
        for (int i = 0; i < stream2.size(); i++)
            stream2arr[i] = stream2.readAsInt(i);
        assertArrayEquals(new int[] {6,6,6,6,6,0,1,2,3,4,5,6,6,6,6}, stream2arr);
    }

    @Test
    public void testDifferentStreamsWithoutbForward() {
        AttributeStreamOfInt64 stream1 = new AttributeStreamOfInt64(15);
        AttributeStreamOfInt64 stream2 = new AttributeStreamOfInt64(15, 6);
        for (int i = 0; i < 15; i++)
            stream1.write(i, i);
        stream2.writeRange(5,6, stream1, 0, false, 1);
        int[] stream2arr = new int[15];
        for (int i = 0; i < stream2.size(); i++)
            stream2arr[i] = stream2.readAsInt(i);
        assertArrayEquals(new int[] {6,6,6,6,6,5,4,3,2,1,0,6,6,6,6}, stream2arr);
    }

    @Test
    public void testDifferentStreamsWithoutbForwardAndNonZeroStride() {
        AttributeStreamOfInt64 stream1 = new AttributeStreamOfInt64(15);
        AttributeStreamOfInt64 stream2 = new AttributeStreamOfInt64(15, 6);
        for (int i = 0; i < 15; i++)
            stream1.write(i, i);
        stream2.writeRange(0,15, stream1, 0, false, 3);
        int[] stream2arr = new int[15];
        for (int i = 0; i < stream2.size(); i++)
            stream2arr[i] = stream2.readAsInt(i);
        assertArrayEquals(new int[] {12,13,14,9,10,11,6,7,8,3,4,5,0,1,2}, stream2arr);
    }

    @Test
    public void testSameStreamCountIsZero() {
        AttributeStreamOfInt64 stream1 = new AttributeStreamOfInt64(15);
        AttributeStreamOfInt64 stream2 = new AttributeStreamOfInt64(15, 6);
        int[] stream2ArrInit = new int[] {6,6,6,6,6,6,6,6,6,6,6,6,6,6,6};
        for (int i = 0; i < 15; i++)
            stream1.write(i, i);
        stream2.writeRange(0,0,stream1,0,true,0);
        int[] stream2Arr = new int[15];
        for (int i = 0; i < 15; i++)
            stream2Arr[i] = stream2.readAsInt(i);
        assertArrayEquals(stream2ArrInit, stream2Arr);
    }
}
