package com.amazon.ata.advertising.service.model.requests;

import com.amazon.ata.advertising.service.model.TargetingPredicate;

import java.util.List;

public class AddTargetingGroupRequest {
    private String contentId;
    private List<TargetingPredicate> targetingPredicates;

    public AddTargetingGroupRequest(String id, List<TargetingPredicate> targetingPredicates) {
        this.contentId = id;
        this.targetingPredicates = targetingPredicates;
    }

    public AddTargetingGroupRequest() {
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public List<TargetingPredicate> getTargetingPredicates() {
        return targetingPredicates;
    }

    public void setTargetingPredicates(List<TargetingPredicate> targetingPredicates) {
        this.targetingPredicates = targetingPredicates;
    }

    public AddTargetingGroupRequest(Builder builder) {
        this.contentId = builder.contentId;
        this.targetingPredicates = builder.targetingPredicates;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String contentId;
        private List<TargetingPredicate> targetingPredicates;

        private Builder() {

        }

        public Builder withContentId(String contentIdToUse) {
            this.contentId = contentIdToUse;
            return this;
        }

        public Builder withTargetingPredicates(List<TargetingPredicate> targetingPredicatesToUse) {
            this.targetingPredicates = targetingPredicatesToUse;
            return this;
        }

        public AddTargetingGroupRequest build() { return new AddTargetingGroupRequest(this); }
    }
}
