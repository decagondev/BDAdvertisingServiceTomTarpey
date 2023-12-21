package com.amazon.ata.advertising.service.model.responses;

import com.amazon.ata.advertising.service.model.TargetingGroup;

public class AddTargetingGroupResponse {
    private TargetingGroup targetingGroup;

    public AddTargetingGroupResponse(TargetingGroup targetingGroup) {
        this.targetingGroup = targetingGroup;
    }

    public AddTargetingGroupResponse() {
    }

    public TargetingGroup getTargetingGroup() {
        return targetingGroup;
    }

    public void setTargetingGroup(TargetingGroup targetingGroup) {
        this.targetingGroup = targetingGroup;
    }

    public AddTargetingGroupResponse(Builder builder) {
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

        public AddTargetingGroupResponse build() { return new AddTargetingGroupResponse(this); }
    }
}
