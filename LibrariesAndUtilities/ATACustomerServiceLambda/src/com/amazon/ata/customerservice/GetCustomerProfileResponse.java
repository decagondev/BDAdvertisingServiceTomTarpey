package com.amazon.ata.customerservice;

import java.util.Arrays;

public class GetCustomerProfileResponse implements Comparable<GetCustomerProfileResponse> {

  /**
   * Statically creates a builder instance for GetCustomerProfileResponse.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of GetCustomerProfileResponse.
   */
  public static class Builder {

    protected CustomerProfile customerProfile;
    /**
     * Sets the value of the field "customerProfile" to be used for the constructed object.
     * @param customerProfile
     *   The value of the "customerProfile" field.
     * @return
     *   This builder.
     */
    public Builder withCustomerProfile(CustomerProfile customerProfile) {
      this.customerProfile = customerProfile;
      return this;
    }

    /**
     * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
     * @param instance
     *   The instance to be populated.
     */
    protected void populate(GetCustomerProfileResponse instance) {
      instance.setCustomerProfile(this.customerProfile);
    }

    /**
     * Builds an instance of GetCustomerProfileResponse.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public GetCustomerProfileResponse build() {
      GetCustomerProfileResponse instance = new GetCustomerProfileResponse();

      populate(instance);

      return instance;
    }
  };

  private CustomerProfile customerProfile;

  public CustomerProfile getCustomerProfile() {
    return this.customerProfile;
  }

  public void setCustomerProfile(CustomerProfile customerProfile) {
    this.customerProfile = customerProfile;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.customerservice.GetCustomerProfileResponse");

  /**
   * HashCode implementation for GetCustomerProfileResponse
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        classNameHashCode,
        getCustomerProfile());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for GetCustomerProfileResponse
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof GetCustomerProfileResponse)) {
      return false;
    }

    GetCustomerProfileResponse that = (GetCustomerProfileResponse) other;

    return
        internalEqualityCheck(getCustomerProfile(), that.getCustomerProfile());
  }

  private static boolean internalEqualityCheck(Object a, Object b) {
    return a == b || (a != null && a.equals(b));
  }

  @SuppressWarnings({"rawtypes","unchecked"})
  /** @deprecated This is broken and should not be used for any critical business processing. Please see https://issues.amazon.com/SF-955 */
  @Deprecated
  public int compareTo(@Deprecated GetCustomerProfileResponse o) {

    if(o == null)
      return -1;
    if(o == this)
      return 0;
    GetCustomerProfileResponse t = o;
    {
      Object o1 = getCustomerProfile();
      Object o2 = t.getCustomerProfile();
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