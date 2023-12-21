package com.amazon.ata.primeclubservice;

public class BenefitLevel {

  private BenefitLevel() { throw new UnsupportedOperationException(); }

  public static final String MINI = "MINI";
  public static final String STANDARD = "STANDARD";
  public static final String PLUS = "PLUS";
  public static final String FRESH = "FRESH";

  private static final String[] values = {
    MINI, STANDARD, PLUS, FRESH
  };

  public static String[] values() {
    return values;
  }
}
