package com.amazon.ata.primeclubservice;

import java.util.Arrays;

public class PrimeBenefit implements Comparable<PrimeBenefit> {

  /**
   * Statically creates a builder instance for PrimeBenefit.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of PrimeBenefit.
   */
  public static class Builder {

    protected String benefitType;
    /**
     * Sets the value of the field "benefitType" to be used for the constructed object.
     * @param benefitType
     *   The value of the "benefitType" field.
     * @return
     *   This builder.
     */
    public Builder withBenefitType(String benefitType) {
      this.benefitType = benefitType;
      return this;
    }

    protected BenefitMetadata benefitMetadata;
    /**
     * Sets the value of the field "benefitMetadata" to be used for the constructed object.
     * @param benefitMetadata
     *   The value of the "benefitMetadata" field.
     * @return
     *   This builder.
     */
    public Builder withBenefitMetadata(BenefitMetadata benefitMetadata) {
      this.benefitMetadata = benefitMetadata;
      return this;
    }

    /**
     * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
     * @param instance
     *   The instance to be populated.
     */
    protected void populate(PrimeBenefit instance) {
      instance.setBenefitType(this.benefitType);
      instance.setBenefitMetadata(this.benefitMetadata);
    }

    /**
     * Builds an instance of PrimeBenefit.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public PrimeBenefit build() {
      PrimeBenefit instance = new PrimeBenefit();

      populate(instance);

      return instance;
    }
  };

  private String benefitType;
  private BenefitMetadata benefitMetadata;

//@EnumValues(value={"MOM_LITE","MOM_DISCOUNT","FREE_TRIDENT_VOD","FREE_EXPEDITED_SHIPPING","DIM_SUM","AMZN4KIDS"})
//@Required()
  public String getBenefitType() {
    return this.benefitType;
  }

  public void setBenefitType(String benefitType) {
    this.benefitType = benefitType;
  }

  public BenefitMetadata getBenefitMetadata() {
    return this.benefitMetadata;
  }

  public void setBenefitMetadata(BenefitMetadata benefitMetadata) {
    this.benefitMetadata = benefitMetadata;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.primeclubservice.PrimeBenefit");

  /**
   * HashCode implementation for PrimeBenefit
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        classNameHashCode,
        getBenefitType(),
        getBenefitMetadata());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for PrimeBenefit
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof PrimeBenefit)) {
      return false;
    }

    PrimeBenefit that = (PrimeBenefit) other;

    return
        internalEqualityCheck(getBenefitType(), that.getBenefitType())
        && internalEqualityCheck(getBenefitMetadata(), that.getBenefitMetadata());
  }

  private static boolean internalEqualityCheck(Object a, Object b) {
    return a == b || (a != null && a.equals(b));
  }

  @SuppressWarnings({"rawtypes","unchecked"})
  /** @deprecated This is broken and should not be used for any critical business processing. Please see https://issues.amazon.com/SF-955 */
  @Deprecated
  public int compareTo(@Deprecated PrimeBenefit o) {

    if(o == null)
      return -1;
    if(o == this)
      return 0;
    PrimeBenefit t = o;
    {
      Object o1 = getBenefitType();
      Object o2 = t.getBenefitType();
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
      Object o1 = getBenefitMetadata();
      Object o2 = t.getBenefitMetadata();
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