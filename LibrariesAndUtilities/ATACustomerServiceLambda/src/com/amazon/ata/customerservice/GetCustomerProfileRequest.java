package com.amazon.ata.customerservice;

import java.util.Arrays;

public class GetCustomerProfileRequest implements Comparable<GetCustomerProfileRequest> {

  /**
   * Statically creates a builder instance for GetCustomerProfileRequest.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of GetCustomerProfileRequest.
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

    /**
     * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
     * @param instance
     *   The instance to be populated.
     */
    protected void populate(GetCustomerProfileRequest instance) {
      instance.setCustomerId(this.customerId);
    }

    /**
     * Builds an instance of GetCustomerProfileRequest.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public GetCustomerProfileRequest build() {
      GetCustomerProfileRequest instance = new GetCustomerProfileRequest();

      populate(instance);

      return instance;
    }
  };

  private String customerId;

  public String getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.customerservice.GetCustomerProfileRequest");

  /**
   * HashCode implementation for GetCustomerProfileRequest
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        classNameHashCode,
        getCustomerId());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for GetCustomerProfileRequest
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof GetCustomerProfileRequest)) {
      return false;
    }

    GetCustomerProfileRequest that = (GetCustomerProfileRequest) other;

    return
        internalEqualityCheck(getCustomerId(), that.getCustomerId());
  }

  private static boolean internalEqualityCheck(Object a, Object b) {
    return a == b || (a != null && a.equals(b));
  }

  @SuppressWarnings({"rawtypes","unchecked"})
  /** @deprecated This is broken and should not be used for any critical business processing. Please see https://issues.amazon.com/SF-955 */
  @Deprecated
  public int compareTo(@Deprecated GetCustomerProfileRequest o) {

    if(o == null)
      return -1;
    if(o == this)
      return 0;
    GetCustomerProfileRequest t = o;
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
    return 0;

  }

}