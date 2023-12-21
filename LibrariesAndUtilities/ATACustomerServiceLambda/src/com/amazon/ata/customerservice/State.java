package com.amazon.ata.customerservice;

public class State {

  private State() { throw new UnsupportedOperationException(); }

  public static final String AL = "AL";
  public static final String AK = "AK";
  public static final String AZ = "AZ";
  public static final String AR = "AR";
  public static final String CA = "CA";
  public static final String CO = "CO";
  public static final String CT = "CT";
  public static final String DE = "DE";
  public static final String FL = "FL";
  public static final String GA = "GA";
  public static final String HI = "HI";
  public static final String ID = "ID";
  public static final String IL = "IL";
  public static final String IN = "IN";
  public static final String IA = "IA";
  public static final String KS = "KS";
  public static final String KY = "KY";
  public static final String LA = "LA";
  public static final String ME = "ME";
  public static final String MD = "MD";
  public static final String MA = "MA";
  public static final String MI = "MI";
  public static final String MN = "MN";
  public static final String MS = "MS";
  public static final String MO = "MO";
  public static final String MT = "MT";
  public static final String NE = "NE";
  public static final String NV = "NV";
  public static final String NH = "NH";
  public static final String NJ = "NJ";
  public static final String NM = "NM";
  public static final String NY = "NY";
  public static final String NC = "NC";
  public static final String ND = "ND";
  public static final String OH = "OH";
  public static final String OK = "OK";
  public static final String OR = "OR";
  public static final String PA = "PA";
  public static final String RI = "RI";
  public static final String SC = "SC";
  public static final String SD = "SD";
  public static final String TN = "TN";
  public static final String TX = "TX";
  public static final String UT = "UT";
  public static final String VT = "VT";
  public static final String VA = "VA";
  public static final String WA = "WA";
  public static final String WV = "WV";
  public static final String WI = "WI";
  public static final String WY = "WY";

  private static final String[] values = {
    AL, AK, AZ, AR, CA, CO, CT, DE, FL, GA, HI, ID, IL, IN, IA, KS, KY, LA, ME, MD, MA, MI, MN, MS, MO, MT, NE, NV, NH, NJ, NM, NY, NC, ND, OH, OK, OR, PA, RI, SC, SD, TN, TX, UT, VT, VA, WA, WV, WI, WY
  };

  public static String[] values() {
    return values;
  }
}
