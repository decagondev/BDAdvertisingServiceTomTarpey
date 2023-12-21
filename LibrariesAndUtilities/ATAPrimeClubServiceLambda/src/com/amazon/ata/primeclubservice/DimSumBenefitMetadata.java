package com.amazon.ata.primeclubservice;

import java.util.Arrays;

public class DimSumBenefitMetadata extends BenefitMetadata {

  /**
   * Statically creates a builder instance for DimSumBenefitMetadata.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of DimSumBenefitMetadata.
   */
  public static class Builder extends BenefitMetadata.Builder {

    protected long checkoutsPerMonth;
    /**
     * Sets the value of the field "checkoutsPerMonth" to be used for the constructed object.
     * @param checkoutsPerMonth
     *   The value of the "checkoutsPerMonth" field.
     * @return
     *   This builder.
     */
    public Builder withCheckoutsPerMonth(long checkoutsPerMonth) {
      this.checkoutsPerMonth = checkoutsPerMonth;
      return this;
    }

    protected long amountOfBooksAvailable;
    /**
     * Sets the value of the field "amountOfBooksAvailable" to be used for the constructed object.
     * @param amountOfBooksAvailable
     *   The value of the "amountOfBooksAvailable" field.
     * @return
     *   This builder.
     */
    public Builder withAmountOfBooksAvailable(long amountOfBooksAvailable) {
      this.amountOfBooksAvailable = amountOfBooksAvailable;
      return this;
    }

    @Override
    public Builder withBenefitLevel(String benefitLevel) {
      super.withBenefitLevel(benefitLevel);
      return this;
    }

    /**
     * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
     * @param instance
     *   The instance to be populated.
     */
    protected void populate(DimSumBenefitMetadata instance) {
      super.populate(instance);

      instance.setCheckoutsPerMonth(this.checkoutsPerMonth);
      instance.setAmountOfBooksAvailable(this.amountOfBooksAvailable);
    }

    /**
     * Builds an instance of DimSumBenefitMetadata.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public DimSumBenefitMetadata build() {
      DimSumBenefitMetadata instance = new DimSumBenefitMetadata();

      populate(instance);

      return instance;
    }
  };

  private long checkoutsPerMonth;
  private long amountOfBooksAvailable;

  public long getCheckoutsPerMonth() {
    return this.checkoutsPerMonth;
  }

  public void setCheckoutsPerMonth(long checkoutsPerMonth) {
    this.checkoutsPerMonth = checkoutsPerMonth;
  }

  public long getAmountOfBooksAvailable() {
    return this.amountOfBooksAvailable;
  }

  public void setAmountOfBooksAvailable(long amountOfBooksAvailable) {
    this.amountOfBooksAvailable = amountOfBooksAvailable;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.primeclubservice.DimSumBenefitMetadata");

  /**
   * HashCode implementation for DimSumBenefitMetadata
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        super.hashCode(),
        classNameHashCode,
        getCheckoutsPerMonth(),
        getAmountOfBooksAvailable());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for DimSumBenefitMetadata
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof DimSumBenefitMetadata)) {
      return false;
    }

    DimSumBenefitMetadata that = (DimSumBenefitMetadata) other;

    return
        super.equals(other)
        && internalEqualityCheck(getCheckoutsPerMonth(), that.getCheckoutsPerMonth())
        && internalEqualityCheck(getAmountOfBooksAvailable(), that.getAmountOfBooksAvailable());
  }

  private static boolean internalEqualityCheck(Object a, Object b) {
    return a == b || (a != null && a.equals(b));
  }

  @SuppressWarnings({"rawtypes","unchecked"})
  /** @deprecated This is broken and should not be used for any critical business processing. Please see https://issues.amazon.com/SF-955 */
  @Deprecated
  public int compareTo(@Deprecated BenefitMetadata o) {

    if(o == null)
      return -1;
    if(o == this)
      return 0;
    if(!(o instanceof DimSumBenefitMetadata))
      return 1;
    DimSumBenefitMetadata t = (DimSumBenefitMetadata)o;
    if(getCheckoutsPerMonth() < t.getCheckoutsPerMonth())
      return -1;
    if(getCheckoutsPerMonth() > t.getCheckoutsPerMonth())
      return 1;
    if(getAmountOfBooksAvailable() < t.getAmountOfBooksAvailable())
      return -1;
    if(getAmountOfBooksAvailable() > t.getAmountOfBooksAvailable())
      return 1;
    return super.compareTo(o);

  }

}