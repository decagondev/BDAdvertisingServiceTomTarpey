package com.amazon.ata.customerservice;


import java.util.Arrays;

public class Spend implements Comparable<Spend> {

  /**
   * Statically creates a builder instance for Spend.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of Spend.
   */
  public static class Builder {

    protected int numberOfPurchases;
    /**
     * Sets the value of the field "numberOfPurchases" to be used for the constructed object.
     * @param numberOfPurchases
     *   The value of the "numberOfPurchases" field.
     * @return
     *   This builder.
     */
    public Builder withNumberOfPurchases(int numberOfPurchases) {
      this.numberOfPurchases = numberOfPurchases;
      return this;
    }

    protected int usdSpent;
    /**
     * Sets the value of the field "usdSpent" to be used for the constructed object.
     * @param usdSpent
     *   The value of the "usdSpent" field.
     * @return
     *   This builder.
     */
    public Builder withUsdSpent(int usdSpent) {
      this.usdSpent = usdSpent;
      return this;
    }

    /**
     * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
     * @param instance
     *   The instance to be populated.
     */
    protected void populate(Spend instance) {
      instance.setNumberOfPurchases(this.numberOfPurchases);
      instance.setUsdSpent(this.usdSpent);
    }

    /**
     * Builds an instance of Spend.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public Spend build() {
      Spend instance = new Spend();

      populate(instance);

      return instance;
    }
  };

  private int numberOfPurchases;
  private int usdSpent;

  public int getNumberOfPurchases() {
    return this.numberOfPurchases;
  }

  public void setNumberOfPurchases(int numberOfPurchases) {
    this.numberOfPurchases = numberOfPurchases;
  }

  public int getUsdSpent() {
    return this.usdSpent;
  }

  public void setUsdSpent(int usdSpent) {
    this.usdSpent = usdSpent;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.customerservice.Spend");

  /**
   * HashCode implementation for Spend
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        classNameHashCode,
        getNumberOfPurchases(),
        getUsdSpent());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for Spend
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof Spend)) {
      return false;
    }

    Spend that = (Spend) other;

    return
        internalEqualityCheck(getNumberOfPurchases(), that.getNumberOfPurchases())
        && internalEqualityCheck(getUsdSpent(), that.getUsdSpent());
  }

  private static boolean internalEqualityCheck(Object a, Object b) {
    return a == b || (a != null && a.equals(b));
  }

  @SuppressWarnings({"rawtypes","unchecked"})
  /** @deprecated This is broken and should not be used for any critical business processing. Please see https://issues.amazon.com/SF-955 */
  @Deprecated
  public int compareTo(@Deprecated Spend o) {

    if(o == null)
      return -1;
    if(o == this)
      return 0;
    Spend t = o;
    if(getNumberOfPurchases() < t.getNumberOfPurchases())
      return -1;
    if(getNumberOfPurchases() > t.getNumberOfPurchases())
      return 1;
    if(getUsdSpent() < t.getUsdSpent())
      return -1;
    if(getUsdSpent() > t.getUsdSpent())
      return 1;
    return 0;

  }

}