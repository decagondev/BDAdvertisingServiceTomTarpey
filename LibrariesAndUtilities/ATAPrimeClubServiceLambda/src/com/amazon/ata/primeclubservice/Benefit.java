package com.amazon.ata.primeclubservice;

public class Benefit {

  private Benefit() { throw new UnsupportedOperationException(); }

  public static final String MOM_LITE = "MOM_LITE";
  public static final String MOM_DISCOUNT = "MOM_DISCOUNT";
  public static final String FREE_TRIDENT_VOD = "FREE_TRIDENT_VOD";
  public static final String FREE_EXPEDITED_SHIPPING = "FREE_EXPEDITED_SHIPPING";
  public static final String DIM_SUM = "DIM_SUM";
  public static final String AMZN4KIDS = "AMZN4KIDS";

  private static final String[] values = {
    MOM_LITE, MOM_DISCOUNT, FREE_TRIDENT_VOD, FREE_EXPEDITED_SHIPPING, DIM_SUM, AMZN4KIDS
  };

  public static String[] values() {
    return values;
  }
}
