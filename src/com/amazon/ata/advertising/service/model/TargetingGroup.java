package com.amazon.ata.advertising.service.model;

import java.util.List;

public class TargetingGroup {
    private String targetingGroupId;
    private String contentId;
    private double clickThroughRate;
    private List<TargetingPredicate> targetingPredicates;

    public TargetingGroup(String targetingGroupId, String contentId, double clickThroughRate, List<TargetingPredicate> targetingPredicates) {
        this.targetingGroupId = targetingGroupId;
        this.contentId = contentId;
        this.clickThroughRate = clickThroughRate;
        this.targetingPredicates = targetingPredicates;
    }

    public String getTargetingGroupId() {
        return targetingGroupId;
    }

    public void setTargetingGroupId(String targetingGroupId) {
        this.targetingGroupId = targetingGroupId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public double getClickThroughRate() {
        return clickThroughRate;
    }

    public void setClickThroughRate(double clickThroughRate) {
        this.clickThroughRate = clickThroughRate;
    }

    public List<TargetingPredicate> getTargetingPredicates() {
        return targetingPredicates;
    }

    public void setTargetingPredicates(List<TargetingPredicate> targetingPredicates) {
        this.targetingPredicates = targetingPredicates;
    }

    public TargetingGroup(Builder builder) {
        this.contentId = builder.contentId;
        this.targetingPredicates = builder.targetingPredicates;
        this.targetingGroupId = builder.targetingGroupId;
        this.clickThroughRate = builder.clickThroughRate;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String targetingGroupId;
        private String contentId;
        private double clickThroughRate;
        private List<TargetingPredicate> targetingPredicates;

        private Builder() {

        }

        public Builder withTargetingGroupId(String targetingGroupIdToUse) {
            this.targetingGroupId = targetingGroupIdToUse;
            return this;
        }

        public Builder withContentId(String contentIdToUse) {
            this.contentId = contentIdToUse;
            return this;
        }

        public Builder withClickThroughRate(double clickThroughRateToUse) {
            this.clickThroughRate = clickThroughRateToUse;
            return this;
        }

        public Builder withTargetingPredicates(List<TargetingPredicate> targetingPredicatesToUse) {
            this.targetingPredicates = targetingPredicatesToUse;
            return this;
        }

        public TargetingGroup build() { return new TargetingGroup(this); }
    }
}
