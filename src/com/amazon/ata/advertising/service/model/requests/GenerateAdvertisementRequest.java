package com.amazon.ata.advertising.service.model.requests;

public class GenerateAdvertisementRequest {
    private String customerId;
    private String marketplaceId;

    public GenerateAdvertisementRequest(String customerId, String marketplaceId) {
        this.customerId = customerId;
        this.marketplaceId = marketplaceId;
    }

    public GenerateAdvertisementRequest() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketPlaceId) {
        this.marketplaceId = marketPlaceId;
    }

    public GenerateAdvertisementRequest(Builder builder) {
        this.customerId = builder.customerId;
        this.marketplaceId = builder.marketplaceId;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String customerId;
        private String marketplaceId;

        private Builder() {

        }

        public Builder withCustomerId(String customerIdToUse) {
            this.customerId = customerIdToUse;
            return this;
        }

        public Builder withMarketplaceId(String marketplaceIdToUse) {
            this.marketplaceId = marketplaceIdToUse;
            return this;
        }

        public GenerateAdvertisementRequest build() { return new GenerateAdvertisementRequest(this); }
    }
}
