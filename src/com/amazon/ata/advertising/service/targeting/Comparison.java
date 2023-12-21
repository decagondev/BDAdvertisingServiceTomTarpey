package com.amazon.ata.advertising.service.targeting;

/**
 * How to compare a value against an expectation.
 */
public enum Comparison {
    LT(Integer.MIN_VALUE, -1), GT(1, Integer.MAX_VALUE), EQ(0, 0);

    private final int min;
    private int max;

    /**
     * Values to compare against.
     * @param min The minimum value to be true
     * @param max The maximum value to true
     */
    Comparison(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Compare two values.  i.e. for LT determines if left < right
     * @param left value on the left side of the operator
     * @param right value on the right side of the operator
     * @param <T> The type to compare.
     * @return true if the evaluation holds
     */
    public <T> boolean compare(Comparable<T> left, T right) {
        final int comparision = left.compareTo(right);
        return comparision >= min && comparision <= max;
    }
}
