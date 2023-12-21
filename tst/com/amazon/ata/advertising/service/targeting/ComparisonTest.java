package com.amazon.ata.advertising.service.targeting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComparisonTest {

    @Test
    public void lessThan_True() {
        assertTrue(Comparison.LT.compare(4, 5));
        assertTrue(Comparison.LT.compare(-5, -4));
    }

    @Test
    public void lessThan_False() {
        assertFalse(Comparison.LT.compare(5, 4));
        assertFalse(Comparison.LT.compare(5, 5));
        assertFalse(Comparison.LT.compare(-4, -5));
        assertFalse(Comparison.LT.compare(-5, -5));
    }

    @Test
    public void greaterThan_True() {
        assertTrue(Comparison.GT.compare(5, 4));
        assertTrue(Comparison.GT.compare(-4, -5));
    }

    @Test
    public void greaterThan_False() {
        assertFalse(Comparison.GT.compare(4, 5));
        assertFalse(Comparison.GT.compare(5, 5));
        assertFalse(Comparison.GT.compare(-5, -4));
        assertFalse(Comparison.GT.compare(-5, -5));
    }

    @Test
    public void equals_True() {
        assertTrue(Comparison.EQ.compare(5, 5));
        assertTrue(Comparison.EQ.compare(-5, -5));
    }

    @Test
    public void equals_False() {
        assertFalse(Comparison.EQ.compare(5, 4));
        assertFalse(Comparison.EQ.compare(4, 5));
        assertFalse(Comparison.EQ.compare(-4, -5));
        assertFalse(Comparison.EQ.compare(-5, -4));
    }

}