package com.amazon.ata.advertising.service.model.responses;

import com.amazon.ata.advertising.service.model.Advertisement;

public class GenerateAdvertisementResponse {
    private Advertisement advertisement;

    public GenerateAdvertisementResponse(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public GenerateAdvertisementResponse() {
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }

    public GenerateAdvertisementResponse(Builder builder) {
        this.advertisement = builder.advertisement;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private Advertisement advertisement;

        private Builder() {

        }

        public Builder withAdvertisement(Advertisement advertisementToUse) {
            this.advertisement = advertisementToUse;
            return this;
        }

        public GenerateAdvertisementResponse build() { return new GenerateAdvertisementResponse(this); }
    }
}
