package com.amazon.ata.primeclubservice;

import java.util.Arrays;

public class BenefitMetadata implements Comparable<BenefitMetadata> {

  /**
   * Statically creates a builder instance for BenefitMetadata.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of BenefitMetadata.
   */
  public static class Builder {

    protected String benefitLevel;
    /**
     * Sets the value of the field "benefitLevel" to be used for the constructed object.
     * @param benefitLevel
     *   The value of the "benefitLevel" field.
     * @return
     *   This builder.
     */
    public Builder withBenefitLevel(String benefitLevel) {
      this.benefitLevel = benefitLevel;
      return this;
    }

    /**
     * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
     * @param instance
     *   The instance to be populated.
     */
    protected void populate(BenefitMetadata instance) {
      instance.setBenefitLevel(this.benefitLevel);
    }

    /**
     * Builds an instance of BenefitMetadata.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public BenefitMetadata build() {
      BenefitMetadata instance = new BenefitMetadata();

      populate(instance);

      return instance;
    }
  };

  private String benefitLevel;

//@EnumValues(value={"MINI","STANDARD","PLUS","FRESH"})
  public String getBenefitLevel() {
    return this.benefitLevel;
  }

  public void setBenefitLevel(String benefitLevel) {
    this.benefitLevel = benefitLevel;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.primeclubservice.BenefitMetadata");

  /**
   * HashCode implementation for BenefitMetadata
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        classNameHashCode,
        getBenefitLevel());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for BenefitMetadata
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof BenefitMetadata)) {
      return false;
    }

    BenefitMetadata that = (BenefitMetadata) other;

    return
        internalEqualityCheck(getBenefitLevel(), that.getBenefitLevel());
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
    BenefitMetadata t = o;
    {
      Object o1 = getBenefitLevel();
      Object o2 = t.getBenefitLevel();
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