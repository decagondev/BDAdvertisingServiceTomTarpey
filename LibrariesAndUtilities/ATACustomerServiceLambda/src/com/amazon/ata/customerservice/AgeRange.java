package com.amazon.ata.customerservice;

public class AgeRange {

  private AgeRange() { throw new UnsupportedOperationException(); }

  public static final String UNDER_18 = "UNDER_18";
  public static final String AGE_18_TO_21 = "AGE_18_TO_21";
  public static final String AGE_22_TO_25 = "AGE_22_TO_25";
  public static final String AGE_26_TO_30 = "AGE_26_TO_30";
  public static final String AGE_31_TO_35 = "AGE_31_TO_35";
  public static final String AGE_36_TO_45 = "AGE_36_TO_45";
  public static final String AGE_46_TO_60 = "AGE_46_TO_60";
  public static final String OVER_60 = "OVER_60";

  private static final String[] values = {
    UNDER_18, AGE_18_TO_21, AGE_22_TO_25, AGE_26_TO_30, AGE_31_TO_35, AGE_36_TO_45, AGE_46_TO_60, OVER_60
  };

  public static String[] values() {
    return values;
  }
}
