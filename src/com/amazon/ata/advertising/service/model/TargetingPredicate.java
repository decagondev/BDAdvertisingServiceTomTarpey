package com.amazon.ata.advertising.service.model;

import java.util.Map;
import java.util.Objects;

public class TargetingPredicate {
    private TargetingPredicateType targetingPredicateType;
    private boolean negate;
    private Map<String, String> attributes;

    public TargetingPredicate(TargetingPredicateType targetingPredicateType, boolean negate, Map<String, String> targetingPredicateAttributes) {
        this.targetingPredicateType = targetingPredicateType;
        this.negate = negate;
        this.attributes = targetingPredicateAttributes;
    }

    public TargetingPredicate() {
    }

    public TargetingPredicateType getTargetingPredicateType() {
        return targetingPredicateType;
    }

    public void setTargetingPredicateType(TargetingPredicateType targetingPredicateType) {
        this.targetingPredicateType = targetingPredicateType;
    }

    public boolean isNegate() {
        return negate;
    }

    public void setNegate(boolean negate) {
        this.negate = negate;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TargetingPredicate that = (TargetingPredicate) o;
        return negate == that.negate &&
                targetingPredicateType == that.targetingPredicateType &&
                Objects.equals(attributes, that.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(targetingPredicateType, negate, attributes);
    }

    public TargetingPredicate(Builder builder) {
        targetingPredicateType = builder.targetingPredicateType;
        negate = builder.negate;
        attributes = builder.attributes;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private TargetingPredicateType targetingPredicateType;
        private boolean negate;
        private Map<String, String> attributes;

        private Builder() {

        }

        public Builder withTargetingPredicateType(TargetingPredicateType targetingPredicateTypeToUse) {
            this.targetingPredicateType = targetingPredicateTypeToUse;
            return this;
        }

        public Builder withNegate(boolean negateToUse) {
            this.negate = negateToUse;
            return this;
        }

        public Builder withAttributes(Map<String, String> attributesToUse) {
            this.attributes = attributesToUse;
            return this;
        }

        public TargetingPredicate build() { return new TargetingPredicate(this); }
    }
}
