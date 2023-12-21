package com.amazon.ata.advertising.service.model;

public class Advertisement {
    private String id;
    private String content;

    public Advertisement(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Advertisement(Builder builder) {
        this.id = builder.id;
        this.content = builder.content;
    }

    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private String id;
        private String content;

        private Builder() {

        }

        public Builder withId(String idToUse) {
            this.id = idToUse;
            return this;
        }

        public Builder withContent(String contentToUse) {
            this.content = contentToUse;
            return this;
        }

        public Advertisement build() { return new Advertisement(this); }
    }
}
