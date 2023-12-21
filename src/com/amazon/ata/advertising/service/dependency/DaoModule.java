package com.amazon.ata.advertising.service.dependency;

import com.amazon.ata.advertising.service.dao.ContentDao;
import com.amazon.ata.advertising.service.dao.CustomerProfileDao;
import com.amazon.ata.advertising.service.dao.CustomerSpendDao;
import com.amazon.ata.advertising.service.dao.PrimeDao;
import com.amazon.ata.advertising.service.dao.ReadableDao;
import com.amazon.ata.advertising.service.dao.TargetingGroupDao;
import com.amazon.ata.advertising.service.model.AdvertisementContent;
import com.amazon.ata.advertising.service.model.RequestContext;
import com.amazon.ata.advertising.service.targeting.TargetingGroup;
import com.amazon.ata.customerservice.CustomerProfile;
import com.amazon.ata.customerservice.Spend;

import com.amazon.atacustomerservicelambda.service.ATACustomerService;
import com.amazon.ataprimeclubservicelambda.service.ATAPrimeClubService;
import dagger.Module;
import dagger.Provides;

import java.util.List;
import java.util.Map;

@Module
public class DaoModule {

    /**
     * Dao for content.
     * @param contentDao source of content data
     * @return Dao
     */
    @Provides
    public ReadableDao<String, List<AdvertisementContent>> provideContentDao(ContentDao contentDao) {
        return contentDao;
    }

    /**
     * Dao for customer profiles.
     * @param customerClient source of customer profile data
     * @return Dao
     */
    @Provides
    public ReadableDao<String, CustomerProfile> provideCustomerProfileDao(ATACustomerService customerClient) {
        return new CustomerProfileDao(customerClient);
    }

    /**
     * Dao for customer spend per category.
     * @param customerClient source of customer spend data
     * @return Dao
     */
    @Provides
    public ReadableDao<RequestContext, Map<String, Spend>> provideCustomerSpendDao(ATACustomerService customerClient) {
        return new CustomerSpendDao(customerClient);
    }

    /**
     * Dao for prime benefits.
     * @param primeClubServiceClient source of prime benefit data
     * @return Dao
     */
    @Provides
    public ReadableDao<RequestContext, List<String>> providePrimeDao(ATAPrimeClubService primeClubServiceClient) {
        return new PrimeDao(primeClubServiceClient);
    }


    /**
     * Dao to get all of the targeting groups for a piece of content.
     * @param targetingGroupDao source of targeting Dao data
     * @return Dao
     */
    @Provides
    public ReadableDao<String, List<TargetingGroup>> provideTargetingGroupDao(TargetingGroupDao targetingGroupDao) {
        return targetingGroupDao;
    }
}
