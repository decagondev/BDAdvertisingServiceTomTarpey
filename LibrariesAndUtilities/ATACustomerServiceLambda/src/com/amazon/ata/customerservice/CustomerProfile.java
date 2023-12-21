package com.amazon.ata.customerservice;

import java.util.Arrays;

public class CustomerProfile implements Comparable<CustomerProfile> {

  /**
   * Statically creates a builder instance for CustomerProfile.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of CustomerProfile.
   */
  public static class Builder {

    protected String homeState;
    /**
     * Sets the value of the field "homeState" to be used for the constructed object.
     * @param homeState
     *   The value of the "homeState" field.
     * @return
     *   This builder.
     */
    public Builder withHomeState(String homeState) {
      this.homeState = homeState;
      return this;
    }

    protected String ageRange;
    /**
     * Sets the value of the field "ageRange" to be used for the constructed object.
     * @param ageRange
     *   The value of the "ageRange" field.
     * @return
     *   This builder.
     */
    public Builder withAgeRange(String ageRange) {
      this.ageRange = ageRange;
      return this;
    }

    protected Boolean parent;
    /**
     * Sets the value of the field "parent" to be used for the constructed object.
     * @param parent
     *   The value of the "parent" field.
     * @return
     *   This builder.
     */
    public Builder withParent(Boolean parent) {
      this.parent = parent;
      return this;
    }

    /**
     * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
     * @param instance
     *   The instance to be populated.
     */
    protected void populate(CustomerProfile instance) {
      instance.setHomeState(this.homeState);
      instance.setAgeRange(this.ageRange);
      instance.setParent(this.parent);
    }

    /**
     * Builds an instance of CustomerProfile.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public CustomerProfile build() {
      CustomerProfile instance = new CustomerProfile();

      populate(instance);

      return instance;
    }
  };

  private String homeState;
  private String ageRange;
  private Boolean parent;

//@EnumValues(value={"AL","AK","AZ","AR","CA","CO","CT","DE","FL","GA","HI","ID","IL","IN","IA","KS","KY","LA","ME","MD","MA","MI","MN","MS","MO","MT","NE","NV","NH","NJ","NM","NY","NC","ND","OH","OK","OR","PA","RI","SC","SD","TN","TX","UT","VT","VA","WA","WV","WI","WY"})
  public String getHomeState() {
    return this.homeState;
  }

  public void setHomeState(String homeState) {
    this.homeState = homeState;
  }

//@EnumValues(value={"UNDER_18","AGE_18_TO_21","AGE_22_TO_25","AGE_26_TO_30","AGE_31_TO_35","AGE_36_TO_45","AGE_46_TO_60","OVER_60"})
  public String getAgeRange() {
    return this.ageRange;
  }

  public void setAgeRange(String ageRange) {
    this.ageRange = ageRange;
  }

  public Boolean isParent() {
    return this.parent;
  }

  public void setParent(Boolean parent) {
    this.parent = parent;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.customerservice.CustomerProfile");

  /**
   * HashCode implementation for CustomerProfile
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        classNameHashCode,
        getHomeState(),
        getAgeRange(),
        isParent());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for CustomerProfile
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof CustomerProfile)) {
      return false;
    }

    CustomerProfile that = (CustomerProfile) other;

    return
        internalEqualityCheck(getHomeState(), that.getHomeState())
        && internalEqualityCheck(getAgeRange(), that.getAgeRange())
        && internalEqualityCheck(isParent(), that.isParent());
  }

  private static boolean internalEqualityCheck(Object a, Object b) {
    return a == b || (a != null && a.equals(b));
  }

  @SuppressWarnings({"rawtypes","unchecked"})
  /** @deprecated This is broken and should not be used for any critical business processing. Please see https://issues.amazon.com/SF-955 */
  @Deprecated
  public int compareTo(@Deprecated CustomerProfile o) {

    if(o == null)
      return -1;
    if(o == this)
      return 0;
    CustomerProfile t = o;
    {
      Object o1 = getHomeState();
      Object o2 = t.getHomeState();
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
      Object o1 = getAgeRange();
      Object o2 = t.getAgeRange();
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
      Object o1 = isParent();
      Object o2 = t.isParent();
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