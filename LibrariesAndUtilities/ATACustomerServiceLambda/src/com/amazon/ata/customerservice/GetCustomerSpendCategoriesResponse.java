package com.amazon.ata.customerservice;

import java.util.Arrays;

public class GetCustomerSpendCategoriesResponse implements Comparable<GetCustomerSpendCategoriesResponse> {

  /**
   * Statically creates a builder instance for GetCustomerSpendCategoriesResponse.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of GetCustomerSpendCategoriesResponse.
   */
  public static class Builder {

    protected CustomerSpendCategories customerSpendCategories;
    /**
     * Sets the value of the field "customerSpendCategories" to be used for the constructed object.
     * @param customerSpendCategories
     *   The value of the "customerSpendCategories" field.
     * @return
     *   This builder.
     */
    public Builder withCustomerSpendCategories(CustomerSpendCategories customerSpendCategories) {
      this.customerSpendCategories = customerSpendCategories;
      return this;
    }

    /**
     * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
     * @param instance
     *   The instance to be populated.
     */
    protected void populate(GetCustomerSpendCategoriesResponse instance) {
      instance.setCustomerSpendCategories(this.customerSpendCategories);
    }

    /**
     * Builds an instance of GetCustomerSpendCategoriesResponse.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public GetCustomerSpendCategoriesResponse build() {
      GetCustomerSpendCategoriesResponse instance = new GetCustomerSpendCategoriesResponse();

      populate(instance);

      return instance;
    }
  };

  private CustomerSpendCategories customerSpendCategories;

  public CustomerSpendCategories getCustomerSpendCategories() {
    return this.customerSpendCategories;
  }

  public void setCustomerSpendCategories(CustomerSpendCategories customerSpendCategories) {
    this.customerSpendCategories = customerSpendCategories;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.customerservice.GetCustomerSpendCategoriesResponse");

  /**
   * HashCode implementation for GetCustomerSpendCategoriesResponse
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        classNameHashCode,
        getCustomerSpendCategories());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for GetCustomerSpendCategoriesResponse
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof GetCustomerSpendCategoriesResponse)) {
      return false;
    }

    GetCustomerSpendCategoriesResponse that = (GetCustomerSpendCategoriesResponse) other;

    return
        internalEqualityCheck(getCustomerSpendCategories(), that.getCustomerSpendCategories());
  }

  private static boolean internalEqualityCheck(Object a, Object b) {
    return a == b || (a != null && a.equals(b));
  }

  @SuppressWarnings({"rawtypes","unchecked"})
  /** @deprecated This is broken and should not be used for any critical business processing. Please see https://issues.amazon.com/SF-955 */
  @Deprecated
  public int compareTo(@Deprecated GetCustomerSpendCategoriesResponse o) {

    if(o == null)
      return -1;
    if(o == this)
      return 0;
    GetCustomerSpendCategoriesResponse t = o;
    {
      Object o1 = getCustomerSpendCategories();
      Object o2 = t.getCustomerSpendCategories();
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