package com.amazon.ata.customerservice;

public class Category {

  private Category() { throw new UnsupportedOperationException(); }

  public static final String PRIME_VIDEO = "PRIME_VIDEO";
  public static final String AMAZON_MUSIC = "AMAZON_MUSIC";
  public static final String KINDLE = "KINDLE";
  public static final String ECHO = "ECHO";
  public static final String FRESH = "FRESH";
  public static final String TECHNICAL_BOOKS = "TECHNICAL_BOOKS";
  public static final String CHILDRENS_BOOKS = "CHILDRENS_BOOKS";
  public static final String MAGAZINES = "MAGAZINES";
  public static final String VIDEO_GAMES = "VIDEO_GAMES";
  public static final String ELECTRONICS = "ELECTRONICS";
  public static final String COMPUTERS = "COMPUTERS";
  public static final String HOME = "HOME";
  public static final String PET = "PET";

  private static final String[] values = {
    PRIME_VIDEO, AMAZON_MUSIC, KINDLE, ECHO, FRESH, TECHNICAL_BOOKS, CHILDRENS_BOOKS, MAGAZINES, VIDEO_GAMES, ELECTRONICS, COMPUTERS, HOME, PET
  };

  public static String[] values() {
    return values;
  }
}
