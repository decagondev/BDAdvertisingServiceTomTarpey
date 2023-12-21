package com.amazon.ata.advertising.service.model.responses;

import com.amazon.ata.advertising.service.model.TargetingGroup;

public class UpdateClickThroughRateResponse {
    private TargetingGroup targetingGroup;

    public UpdateClickThroughRateResponse(TargetingGroup targetingGroup) {
        this.targetingGroup = targetingGroup;
    }

    public UpdateClickThroughRateResponse() {
    }

    public TargetingGroup getTargetingGroup() {
        return targetingGroup;
    }

    public void setTargetingGroup(TargetingGroup targetingGroup) {
        this.targetingGroup = targetingGroup;
    }

    public UpdateClickThroughRateResponse(Builder builder) {
        this.targetingGroup = builder.targetingGroup;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private TargetingGroup targetingGroup;

        private Builder() {

        }

        public Builder withTargetingGroup(TargetingGroup targetingGroupToUse) {
            this.targetingGroup = targetingGroupToUse;
            return this;
        }

        public UpdateClickThroughRateResponse build() { return new UpdateClickThroughRateResponse(this); }
    }
}
