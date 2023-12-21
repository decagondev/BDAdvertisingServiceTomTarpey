package com.amazon.ata.primeclubservice;

import java.util.Arrays;

public class DVDRentalBenefitMetadata extends BenefitMetadata {

  /**
   * Statically creates a builder instance for DVDRentalBenefitMetadata.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of DVDRentalBenefitMetadata.
   */
  public static class Builder extends BenefitMetadata.Builder {

    protected long noOfDiscOut;
    /**
     * Sets the value of the field "noOfDiscOut" to be used for the constructed object.
     * @param noOfDiscOut
     *   The value of the "noOfDiscOut" field.
     * @return
     *   This builder.
     */
    public Builder withNoOfDiscOut(long noOfDiscOut) {
      this.noOfDiscOut = noOfDiscOut;
      return this;
    }

    protected long monthlyLimit;
    /**
     * Sets the value of the field "monthlyLimit" to be used for the constructed object.
     * @param monthlyLimit
     *   The value of the "monthlyLimit" field.
     * @return
     *   This builder.
     */
    public Builder withMonthlyLimit(long monthlyLimit) {
      this.monthlyLimit = monthlyLimit;
      return this;
    }

    protected String billingSchedule;
    /**
     * Sets the value of the field "billingSchedule" to be used for the constructed object.
     * @param billingSchedule
     *   The value of the "billingSchedule" field.
     * @return
     *   This builder.
     */
    public Builder withBillingSchedule(String billingSchedule) {
      this.billingSchedule = billingSchedule;
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
    protected void populate(DVDRentalBenefitMetadata instance) {
      super.populate(instance);

      instance.setNoOfDiscOut(this.noOfDiscOut);
      instance.setMonthlyLimit(this.monthlyLimit);
      instance.setBillingSchedule(this.billingSchedule);
    }

    /**
     * Builds an instance of DVDRentalBenefitMetadata.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public DVDRentalBenefitMetadata build() {
      DVDRentalBenefitMetadata instance = new DVDRentalBenefitMetadata();

      populate(instance);

      return instance;
    }
  };

  private long noOfDiscOut;
  private long monthlyLimit;
  private String billingSchedule;

  public long getNoOfDiscOut() {
    return this.noOfDiscOut;
  }

  public void setNoOfDiscOut(long noOfDiscOut) {
    this.noOfDiscOut = noOfDiscOut;
  }

  public long getMonthlyLimit() {
    return this.monthlyLimit;
  }

  public void setMonthlyLimit(long monthlyLimit) {
    this.monthlyLimit = monthlyLimit;
  }

  public String getBillingSchedule() {
    return this.billingSchedule;
  }

  public void setBillingSchedule(String billingSchedule) {
    this.billingSchedule = billingSchedule;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.primeclubservice.DVDRentalBenefitMetadata");

  /**
   * HashCode implementation for DVDRentalBenefitMetadata
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        super.hashCode(),
        classNameHashCode,
        getNoOfDiscOut(),
        getMonthlyLimit(),
        getBillingSchedule());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for DVDRentalBenefitMetadata
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof DVDRentalBenefitMetadata)) {
      return false;
    }

    DVDRentalBenefitMetadata that = (DVDRentalBenefitMetadata) other;

    return
        super.equals(other)
        && internalEqualityCheck(getNoOfDiscOut(), that.getNoOfDiscOut())
        && internalEqualityCheck(getMonthlyLimit(), that.getMonthlyLimit())
        && internalEqualityCheck(getBillingSchedule(), that.getBillingSchedule());
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
    if(!(o instanceof DVDRentalBenefitMetadata))
      return 1;
    DVDRentalBenefitMetadata t = (DVDRentalBenefitMetadata)o;
    if(getNoOfDiscOut() < t.getNoOfDiscOut())
      return -1;
    if(getNoOfDiscOut() > t.getNoOfDiscOut())
      return 1;
    if(getMonthlyLimit() < t.getMonthlyLimit())
      return -1;
    if(getMonthlyLimit() > t.getMonthlyLimit())
      return 1;
    {
      Object o1 = getBillingSchedule();
      Object o2 = t.getBillingSchedule();
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
    return super.compareTo(o);

  }

}