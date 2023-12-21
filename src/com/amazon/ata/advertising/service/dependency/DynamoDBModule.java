package com.amazon.ata.advertising.service.dependency;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DynamoDBModule {

    /**
     * Provides a singleton instance of DynamoDBMapper.
     *
     * @return DynamoDBMapper
     */
    @Singleton
    @Provides
    public DynamoDBMapper provideDynamoDBMapper() {
        AmazonDynamoDB amazonDynamoDBClient = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
                .withRegion(Regions.US_WEST_2)
                .build();

        return new DynamoDBMapper(amazonDynamoDBClient);
    }
}
