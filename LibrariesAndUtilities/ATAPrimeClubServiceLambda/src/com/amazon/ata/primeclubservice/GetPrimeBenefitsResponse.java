package com.amazon.ata.primeclubservice;

import java.util.List;
import java.util.Arrays;

public class GetPrimeBenefitsResponse implements Comparable<GetPrimeBenefitsResponse> {

  /**
   * Statically creates a builder instance for GetPrimeBenefitsResponse.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Fluent builder for instances of GetPrimeBenefitsResponse.
   */
  public static class Builder {

    protected List<PrimeBenefit> primeBenefits;
    /**
     * Sets the value of the field "primeBenefits" to be used for the constructed object.
     * @param primeBenefits
     *   The value of the "primeBenefits" field.
     * @return
     *   This builder.
     */
    public Builder withPrimeBenefits(List<PrimeBenefit> primeBenefits) {
      this.primeBenefits = primeBenefits;
      return this;
    }

    /**
     * Sets the fields of the given instances to the corresponding values recorded when calling the "with*" methods.
     * @param instance
     *   The instance to be populated.
     */
    protected void populate(GetPrimeBenefitsResponse instance) {
      instance.setPrimeBenefits(this.primeBenefits);
    }

    /**
     * Builds an instance of GetPrimeBenefitsResponse.
     * <p>
     * The built object has its fields set to the values given when calling the "with*" methods of this builder.
     * </p>
     */
    public GetPrimeBenefitsResponse build() {
      GetPrimeBenefitsResponse instance = new GetPrimeBenefitsResponse();

      populate(instance);

      return instance;
    }
  };

  private List<PrimeBenefit> primeBenefits;

  public List<PrimeBenefit> getPrimeBenefits() {
    return this.primeBenefits;
  }

  public void setPrimeBenefits(List<PrimeBenefit> primeBenefits) {
    this.primeBenefits = primeBenefits;
  }

  private static final int classNameHashCode =
      internalHashCodeCompute("com.amazon.ata.primeclubservice.GetPrimeBenefitsResponse");

  /**
   * HashCode implementation for GetPrimeBenefitsResponse
   * based on java.util.Arrays.hashCode
   */
  @Override
  public int hashCode() {
    return internalHashCodeCompute(
        classNameHashCode,
        getPrimeBenefits());
  }

  private static int internalHashCodeCompute(Object... objects) {
    return Arrays.hashCode(objects);
  }
  /**
   * Equals implementation for GetPrimeBenefitsResponse
   * based on instanceof and Object.equals().
   */
  @Override
  public boolean equals(final Object other) {
    if (!(other instanceof GetPrimeBenefitsResponse)) {
      return false;
    }

    GetPrimeBenefitsResponse that = (GetPrimeBenefitsResponse) other;

    return
        internalEqualityCheck(getPrimeBenefits(), that.getPrimeBenefits());
  }

  private static boolean internalEqualityCheck(Object a, Object b) {
    return a == b || (a != null && a.equals(b));
  }

  @SuppressWarnings({"rawtypes","unchecked"})
  /** @deprecated This is broken and should not be used for any critical business processing. Please see https://issues.amazon.com/SF-955 */
  @Deprecated
  public int compareTo(@Deprecated GetPrimeBenefitsResponse o) {

    if(o == null)
      return -1;
    if(o == this)
      return 0;
    GetPrimeBenefitsResponse t = o;
    {
      Object o1 = getPrimeBenefits();
      Object o2 = t.getPrimeBenefits();
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