package com.amazon.ata.advertising.service.model.requests;

import com.amazon.ata.advertising.service.model.AdvertisingContent;

public class UpdateContentRequest {
    private String contentId;
    private AdvertisingContent advertisingContent;

    public UpdateContentRequest(String contentid, AdvertisingContent advertisingContent) {
        this.contentId = contentid;
        this.advertisingContent = advertisingContent;
    }

    public UpdateContentRequest() {
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public AdvertisingContent getAdvertisingContent() {
        return advertisingContent;
    }

    public void setAdvertisingContent(AdvertisingContent advertisingContent) {
        this.advertisingContent = advertisingContent;
    }

    public UpdateContentRequest(Builder builder) {
        this.contentId = builder.contentId;
        this.advertisingContent = builder.advertisingContent;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String contentId;
        private AdvertisingContent advertisingContent;

        private Builder() {

        }

        public Builder withContentId(String contentIdToUse) {
            this.contentId = contentIdToUse;
            return this;
        }

        public Builder withAdvertisingContent(AdvertisingContent advertisingContentToUse) {
            this.advertisingContent = advertisingContentToUse;
            return this;
        }

        public UpdateContentRequest build() { return new UpdateContentRequest(this); }
    }
}
