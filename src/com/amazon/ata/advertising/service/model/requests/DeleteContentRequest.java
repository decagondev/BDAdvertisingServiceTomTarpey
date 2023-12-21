package com.amazon.ata.advertising.service.model.requests;

public class DeleteContentRequest {
    private String contentId;

    public DeleteContentRequest(String contentId) {
        this.contentId = contentId;
    }

    public DeleteContentRequest() {
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public DeleteContentRequest(Builder builder) {
        this.contentId = builder.contentId;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String contentId;

        private Builder() {

        }

        public Builder withContentId(String contentIdToUse) {
            this.contentId = contentIdToUse;
            return this;
        }

        public DeleteContentRequest build() { return new DeleteContentRequest(this); }
    }
}
