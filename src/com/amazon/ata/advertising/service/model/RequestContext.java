package com.amazon.ata.advertising.service.model;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * Information derived from a service request object.
 */
public class RequestContext {
    private final boolean recognizedCustomer;
    private final String customerId;
    private final String marketplaceId;

    /**
     * Constructor of RequestContext objects. A flag denoting whether the customer is recognized is set based on the
     * passed customerId field. Empty/Null values will result in a false value for the recognizedCustomer flag.
     * @param customerId The unique identifier for the customer who will be viewing the advertisement, if the customer
     *                   is recognized.
     * @param marketplaceId The marketplace to view the advertisement in.
     */
    public RequestContext(String customerId, String marketplaceId) {
        if (StringUtils.isBlank(customerId)) {
            this.recognizedCustomer = false;
            this.customerId = null;
        } else {
            this.recognizedCustomer = true;
            this.customerId = customerId;
        }
        this.marketplaceId = marketplaceId;
    }

    public boolean isRecognizedCustomer() {
        return recognizedCustomer;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final RequestContext that = (RequestContext) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(marketplaceId, that.marketplaceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, marketplaceId);
    }
}
