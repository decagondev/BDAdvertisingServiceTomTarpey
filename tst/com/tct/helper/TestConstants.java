package com.tct.helper;

/**
 * Contains constant values used in TCTs.
 */
public class TestConstants {
    public static final String US_MARKETPLACE_ID = "ATVPDKIKX0DER";
    public static final String CA_MARKETPLACE_ID = "A2EUQ1WTGCTBG2";

    // customer profile is calculated with this function based on unicode char values
    // https://code.amazon.com/packages/ATACustomerServiceLambda/blobs/b02f1bc224cd968fc35bb28e96890eea0be23b7b/--/src/com/amazon/atacustomerservicelambda/activity/GetCustomerProfileActivity.java#L95
    // (100 + 101) + 100  = 301 % 10 = 1
    public static final String PARENT_PROFILE_CUSTOMER_ID = "de";
    // (100 + 98) + 100 = 298 % 10 = 8
    public static final String EMPTY_PROFILE_CUSTOMER_ID = "db";
}
