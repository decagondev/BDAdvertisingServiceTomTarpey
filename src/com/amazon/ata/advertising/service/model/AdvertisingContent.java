package com.amazon.ata.advertising.service.model;

public class AdvertisingContent {
    private String id;
    private String marketplaceId;
    private String content;

    public AdvertisingContent(String id, String marketplaceId, String content) {
        this.id = id;
        this.marketplaceId = marketplaceId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AdvertisingContent(Builder builder) {
        this.id = builder.id;
        this.marketplaceId = builder.marketplaceId;
        this.content = builder.content;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String id;
        private String marketplaceId;
        private String content;

        private Builder() {

        }

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

        public Builder withMarketplaceId(String marketplaceIdToUse) {
            this.marketplaceId = marketplaceIdToUse;
            return this;
        }

        public Builder withContent(String contentToUse) {
            this.content = contentToUse;
            return this;
        }

        public AdvertisingContent build() { return new AdvertisingContent(this); }
    }
}
