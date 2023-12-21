package com.amazon.ata.advertising.service.targeting.predicate;

/**
 * Possible outcomes from a targeting predicate's evaluate method. If a TRUE/FALSE value cannot be evaluated,
 * INDETERMINATE will be returned.
 */
public enum TargetingPredicateResult {
    TRUE(true), FALSE(false), INDETERMINATE(false);

    private boolean isTrueResult;

    /**
     * Each targeting predicate result value defines if it could be considered true.
     * @param isTrueResult Should this result be considered a true result.
     */
    TargetingPredicateResult(boolean isTrueResult) {
        this.isTrueResult = isTrueResult;
    }

    public boolean isTrue() {
        return isTrueResult;
    }

    /**
     * Calculate the inverse of the passed result.
     * @return the inverse TargetingPredicateResult value
     */
    public TargetingPredicateResult invert() {
        switch (this) {
            case TRUE:
                return FALSE;
            case FALSE:
                return TRUE;
            case INDETERMINATE:
                return INDETERMINATE;
            default:
                throw new IllegalArgumentException("Unable to calculate the inverse for result: " + this);
        }

    }
}
