package com.amazon.ata.customerservice;

import java.util.Map;
import java.util.Arrays;

public class CustomerSpendCategories implements Comparable<CustomerSpendCategories> {

  /**
   * Statically creates a builder instance for CustomerSpendCategories.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of CustomerSpendCategories.
   */
  public static class Builder {

    protected Map<String, Spend> spendCategories;
    /**
     * Sets the value of the field "spendCategories" to be used for the constructed object.
     * @param spendCategories
     *   The value of the "spendCategories" field.
     * @return
     *   This builder.
     */
    public Builder withSpendCategories(Map<String, Spend> spendCategories) {
      this.spendCategories = spendCategories;
      return this;
    }

    /**
     * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
     * @param instance
     *   The instance to be populated.
     */
    protected void populate(CustomerSpendCategories instance) {
      instance.setSpendCategories(this.spendCategories);
    }

    /**
     * Builds an instance of CustomerSpendCategories.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public CustomerSpendCategories build() {
      CustomerSpendCategories instance = new CustomerSpendCategories();

      populate(instance);

      return instance;
    }
  };

  private Map<String, Spend> spendCategories;

//@Required()
// @MapKeyConstraint(@NestedConstraints(  enumValues = {@EnumValues({"PRIME_VIDEO","AMAZON_MUSIC","KINDLE","ECHO","FRESH","TECHNICAL_BOOKS","CHILDRENS_BOOKS","MAGAZINES","VIDEO_GAMES","ELECTRONICS","COMPUTERS","HOME","PET"})}))
  public Map<String, Spend> getSpendCategories() {
    return this.spendCategories;
  }

  public void setSpendCategories(Map<String, Spend> spendCategories) {
    this.spendCategories = spendCategories;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.customerservice.CustomerSpendCategories");

  /**
   * HashCode implementation for CustomerSpendCategories
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        classNameHashCode,
        getSpendCategories());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for CustomerSpendCategories
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof CustomerSpendCategories)) {
      return false;
    }

    CustomerSpendCategories that = (CustomerSpendCategories) other;

    return
        internalEqualityCheck(getSpendCategories(), that.getSpendCategories());
  }

  private static boolean internalEqualityCheck(Object a, Object b) {
    return a == b || (a != null && a.equals(b));
  }

  @SuppressWarnings({"rawtypes","unchecked"})
  /** @deprecated This is broken and should not be used for any critical business processing. Please see https://issues.amazon.com/SF-955 */
  @Deprecated
  public int compareTo(@Deprecated CustomerSpendCategories o) {

    if(o == null)
      return -1;
    if(o == this)
      return 0;
    CustomerSpendCategories t = o;
    {
      Object o1 = getSpendCategories();
      Object o2 = t.getSpendCategories();
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