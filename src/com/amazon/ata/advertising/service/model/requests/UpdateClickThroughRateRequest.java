package com.amazon.ata.advertising.service.model.requests;

public class UpdateClickThroughRateRequest {
    private String targetingGroupId;
    private double clickThroughRate;

    public UpdateClickThroughRateRequest(String targetingGroupId, double clickThroughRate) {
        this.targetingGroupId = targetingGroupId;
        this.clickThroughRate = clickThroughRate;
    }

    public UpdateClickThroughRateRequest() {
    }

    public String getTargetingGroupId() {
        return targetingGroupId;
    }

    public void setTargetingGroupId(String targetingGroupId) {
        this.targetingGroupId = targetingGroupId;
    }

    public double getClickThroughRate() {
        return clickThroughRate;
    }

    public void setClickThroughRate(double clickThroughRate) {
        this.clickThroughRate = clickThroughRate;
    }

    public UpdateClickThroughRateRequest(Builder builder) {
        this.targetingGroupId = builder.targetingGroupId;
        this.clickThroughRate = builder.clickThroughRate;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String targetingGroupId;
        private double clickThroughRate;

        private Builder() {

        }

        public Builder withTargetingGroupId(String targetingGroupIdToUse) {
            this.targetingGroupId = targetingGroupIdToUse;
            return this;
        }

        public Builder withClickThroughRate(double clickThroughRateToUse) {
            this.clickThroughRate = clickThroughRateToUse;
            return this;
        }

        public UpdateClickThroughRateRequest build() { return new UpdateClickThroughRateRequest(this); }
    }
}
