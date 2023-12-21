package com.amazon.ata.advertising.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

/**
 * An Advertisement's content contains an id that is unique to the template used to generate its renderable content.
 */
@DynamoDBTable(tableName = "Content")
public class AdvertisementContent {
    public static final String MARKETPLACE_ID_INDEX = "MarketplaceIdIndex";

    private String contentId;
    private String renderableContent;
    private String marketplaceId;

    /**
     * An Advertisement's content contains an id that is unique to the template used to generate its renderable content.
     * @param contentId The unique identifier for this piece of content.
     * @param renderableContent Html and css to be displayed on the retail website.
     * @param marketplaceId Which marketplace this advertisement should display in
     */
    protected AdvertisementContent(String contentId, String renderableContent, String marketplaceId) {
        this.contentId = contentId;
        this.renderableContent = renderableContent;
        this.marketplaceId = marketplaceId;
    }

    /**
     * Empty constructor for DynamoDB.
     */
    public AdvertisementContent() {}

    @DynamoDBAttribute(attributeName = "RenderableContent")
    public String getRenderableContent() {
        return this.renderableContent;
    }

    public void setRenderableContent(String renderableContent) {
        this.renderableContent = renderableContent;
    }

    @DynamoDBHashKey(attributeName = "ContentId")
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = MARKETPLACE_ID_INDEX, attributeName = "MarketplaceId")
    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof AdvertisementContent)) {
            return false;
        }

        if (this == other) {
            return true;
        }

        AdvertisementContent otherAdvertisementContent = (AdvertisementContent) other;
        return this.contentId.equals(otherAdvertisementContent.contentId);
    }

    /**
     * A builder to create a new AdvertisementContent.
     * @return The fluent builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class used to create AdvertisementContent.
     */
    public static class Builder {

        private String contentId;
        private String renderableContent;
        private String marketplaceId;

        /**
         * Use the properties provided to create an AdvertisementContent.
         * @return The content of an advertisement.
         */
        public AdvertisementContent build() {
            return new AdvertisementContent(contentId, renderableContent, marketplaceId);
        }

        /**
         * The unique identifier of the content.
         * @param id A unique id for the content.
         * @return The fluent builder.
         */
        public Builder withContentId(String id) {
            this.contentId = id;
            return this;
        }

        /**
         * The html and css of the advertisement to be displayed on the retail website.
         * @param content html and css
         * @return The fluent builder
         */
        public Builder withRenderableContent(String content) {
            this.renderableContent = content;
            return this;
        }

        /**
         * The marketplace ID to render the advertisement in.
         * @param marketplaceIdToUse marketplace to display ad in
         * @return The fluent builder
         */
        public Builder withMarketplaceId(String marketplaceIdToUse) {
            this.marketplaceId = marketplaceIdToUse;
            return this;
        }
    }
}
