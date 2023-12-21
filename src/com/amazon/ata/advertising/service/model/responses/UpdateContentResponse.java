package com.amazon.ata.advertising.service.model.responses;

import com.amazon.ata.advertising.service.model.AdvertisingContent;
import com.amazon.ata.advertising.service.model.TargetingGroup;

import java.util.List;

public class UpdateContentResponse {
    private AdvertisingContent advertisingContent;
    private List<TargetingGroup> targetingGroups;

    public UpdateContentResponse(AdvertisingContent advertisingContent, List<TargetingGroup> targetingGroupList) {
        this.advertisingContent = advertisingContent;
        this.targetingGroups = targetingGroupList;
    }

    public UpdateContentResponse() {
    }

    public AdvertisingContent getAdvertisingContent() {
        return advertisingContent;
    }

    public void setAdvertisingContent(AdvertisingContent advertisingContent) {
        this.advertisingContent = advertisingContent;
    }

    public List<TargetingGroup> getTargetingGroups() {
        return targetingGroups;
    }

    public void setTargetingGroupList(List<TargetingGroup> targetingGroupList) {
        this.targetingGroups = targetingGroupList;
    }

    public UpdateContentResponse(Builder builder) {
        this.targetingGroups = builder.targetingGroups;
        this.advertisingContent = builder.advertisingContent;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private AdvertisingContent advertisingContent;
        private List<TargetingGroup> targetingGroups;

        private Builder() {

        }

        public Builder withTargetingGroups(List<TargetingGroup> targetingGroupsToUse) {
            this.targetingGroups = targetingGroupsToUse;
            return this;
        }

        public Builder withAdvertisingContent(AdvertisingContent advertisingContentToUse) {
            this.advertisingContent = advertisingContentToUse;
            return this;
        }

        public UpdateContentResponse build() { return new UpdateContentResponse(this); }
    }
}
