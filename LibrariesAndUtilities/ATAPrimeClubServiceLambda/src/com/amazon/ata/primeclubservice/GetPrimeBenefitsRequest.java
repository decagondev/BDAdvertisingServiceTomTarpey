package com.amazon.ata.primeclubservice;

import java.util.Arrays;

public class GetPrimeBenefitsRequest implements Comparable<GetPrimeBenefitsRequest> {

  /**
   * Statically creates a builder instance for GetPrimeBenefitsRequest.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of GetPrimeBenefitsRequest.
   */
  public static class Builder {

    protected String customerId;
    /**
     * Sets the value of the field "customerId" to be used for the constructed object.
     * @param customerId
     *   The value of the "customerId" field.
     * @return
     *   This builder.
     */
    public Builder withCustomerId(String customerId) {
      this.customerId = customerId;
      return this;
    }

    protected String marketplaceId;
    /**
     * Sets the value of the field "marketplaceId" to be used for the constructed object.
     * @param marketplaceId
     *   The value of the "marketplaceId" field.
     * @return
     *   This builder.
     */
    public Builder withMarketplaceId(String marketplaceId) {
      this.marketplaceId = marketplaceId;
      return this;
    }

    /**
     * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
     * @param instance
     *   The instance to be populated.
     */
    protected void populate(GetPrimeBenefitsRequest instance) {
      instance.setCustomerId(this.customerId);
      instance.setMarketplaceId(this.marketplaceId);
    }

    /**
     * Builds an instance of GetPrimeBenefitsRequest.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public GetPrimeBenefitsRequest build() {
      GetPrimeBenefitsRequest instance = new GetPrimeBenefitsRequest();

      populate(instance);

      return instance;
    }
  };

  private String customerId;
  private String marketplaceId;

  public String getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getMarketplaceId() {
    return this.marketplaceId;
  }

  public void setMarketplaceId(String marketplaceId) {
    this.marketplaceId = marketplaceId;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.primeclubservice.GetPrimeBenefitsRequest");

  /**
   * HashCode implementation for GetPrimeBenefitsRequest
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        classNameHashCode,
        getCustomerId(),
        getMarketplaceId());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for GetPrimeBenefitsRequest
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof GetPrimeBenefitsRequest)) {
      return false;
    }

    GetPrimeBenefitsRequest that = (GetPrimeBenefitsRequest) other;

    return
        internalEqualityCheck(getCustomerId(), that.getCustomerId())
        && internalEqualityCheck(getMarketplaceId(), that.getMarketplaceId());
  }

  private static boolean internalEqualityCheck(Object a, Object b) {
    return a == b || (a != null && a.equals(b));
  }

  @SuppressWarnings({"rawtypes","unchecked"})
  /** @deprecated This is broken and should not be used for any critical business processing. Please see https://issues.amazon.com/SF-955 */
  @Deprecated
  public int compareTo(@Deprecated GetPrimeBenefitsRequest o) {

    if(o == null)
      return -1;
    if(o == this)
      return 0;
    GetPrimeBenefitsRequest t = o;
    {
      Object o1 = getCustomerId();
      Object o2 = t.getCustomerId();
      if(o1 != o2) {
        if(o1 == null)
          return -1;
        if(o2 == null)
          return 1;
        if(o1 instanceof Comparable<?>) {
          Comparable c1 = (Comparable)o1;
          int ret = c1.compareTo(o2);
          if(ret != 0)
            return ret;
        }
        else if(!o1.equals(o2)) {
          int hc1 = o1.hashCode();
          int hc2 = o2.hashCode();
          if(hc1 < hc2) return -1;
          if(hc1 > hc2) return 1;
        }
      }
    }
    {
      Object o1 = getMarketplaceId();
      Object o2 = t.getMarketplaceId();
      if(o1 != o2) {
        if(o1 == null)
          return -1;
        if(o2 == null)
          return 1;
        if(o1 instanceof Comparable<?>) {
          Comparable c1 = (Comparable)o1;
          int ret = c1.compareTo(o2);
          if(ret != 0)
            return ret;
        }
        else if(!o1.equals(o2)) {
          int hc1 = o1.hashCode();
          int hc2 = o2.hashCode();
          if(hc1 < hc2) return -1;
          if(hc1 > hc2) return 1;
        }
      }
    }
    return 0;

  }

}