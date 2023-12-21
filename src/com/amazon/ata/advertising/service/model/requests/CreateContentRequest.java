package com.amazon.ata.advertising.service.model.requests;

import com.amazon.ata.advertising.service.model.TargetingPredicate;

import java.util.List;

public class CreateContentRequest {
    private String content;
    private String marketplaceId;
    private List<TargetingPredicate> targetingPredicates;

    public CreateContentRequest(String content, String marketplaceId, List<TargetingPredicate> targetingPredicates) {
        this.content = content;
        this.marketplaceId = marketplaceId;
        this.targetingPredicates = targetingPredicates;
    }

    public CreateContentRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketPlaceId) {
        this.marketplaceId = marketPlaceId;
    }

    public List<TargetingPredicate> getTargetingPredicates() {
        return targetingPredicates;
    }

    public void setTargetingPredicates(List<TargetingPredicate> targetingPredicates) {
        this.targetingPredicates = targetingPredicates;
    }

    public CreateContentRequest(Builder builder) {
        this.content = builder.content;
        this.marketplaceId = builder.marketplaceId;
        this.targetingPredicates = builder.targetingPredicates;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String content;
        private String marketplaceId;
        private List<TargetingPredicate> targetingPredicates;

        private Builder() {

        }

        public Builder withContent(String contentToUse) {
            this.content = contentToUse;
            return this;
        }

        public Builder withMarketplaceId(String marketplaceIdToUse) {
            this.marketplaceId = marketplaceIdToUse;
            return this;
        }

        public Builder withTargetingPredicates(List<TargetingPredicate> targetingPredicatesToUse) {
            this.targetingPredicates = targetingPredicatesToUse;
            return this;
        }

        public CreateContentRequest build() { return new CreateContentRequest(this); }
    }
}
