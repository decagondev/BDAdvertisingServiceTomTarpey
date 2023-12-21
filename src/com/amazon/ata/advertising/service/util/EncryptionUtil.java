package com.amazon.ata.advertising.service.util;

//import amazon.security.AmzUid;

public class EncryptionUtil {
    private EncryptionUtil() {}

    /**
     * Encrypts a marketplaceId if it is not already encrypted.
     * @param marketplaceId The marketplace ID to encrypt
     * @return the encrypted marketplace ID
     */
    public static String encryptMarketplaceId(String marketplaceId) {
        String encryptedMarketplace;
        try {
            long decryptedMarketplace = Long.parseLong(marketplaceId);
            encryptedMarketplace = marketplaceId;//AmzUid.encryptCustomerID(decryptedMarketplace);
        } catch (NumberFormatException e) {
            encryptedMarketplace = marketplaceId;
        }
        return encryptedMarketplace;
    }
}
