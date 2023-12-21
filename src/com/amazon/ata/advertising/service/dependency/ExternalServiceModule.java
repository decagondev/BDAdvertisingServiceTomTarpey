package com.amazon.ata.advertising.service.dependency;

import com.amazon.atacustomerservicelambda.service.ATACustomerService;
import com.amazon.atacustomerservicelambda.service.ATACustomerServiceClientBuilder;
import com.amazon.ataprimeclubservicelambda.service.ATAPrimeClubService;
import com.amazon.ataprimeclubservicelambda.service.ATAPrimeClubServiceClientBuilder;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Wire up clients for external services that need to be called.
 */
@Module
public class ExternalServiceModule {
    private static final String SERVICE_ENDPOINT = "serviceEndpoint";
    private static final String SIGNING_REGION = "signingRegion";

    /**
     * Set up and build an ATAPrimeClubService client for the correct endpoint and using the AWS credentials to have
     * authorization.
     * @param credentialsProvider Credentials to access AWS account where service lives.
     * @return Client for ATAPrimeClubService
     */
    @Provides
    @Singleton
    public ATAPrimeClubService providePrimeClubService(AWSCredentialsProvider credentialsProvider) {
        String primeKeys = "ata.primeclub.service.client.endpointConfiguration";
        String serviceEndpoint = System.getProperty(primeKeys + SERVICE_ENDPOINT);
        String signingRegion = System.getProperty(primeKeys + SIGNING_REGION);
        return ATAPrimeClubServiceClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(serviceEndpoint, signingRegion))
            .withAwsCredentialsProvider(credentialsProvider)
            .build();
    }

    /**
     * Set up and build an ATACustomer client for the correct endpoint, using AWS credentials to have
     * authorization.
     * @param credentialsProvider Credentials to access AWS account where service lives.
     * @return Client for ATACustomer
     */
    @Provides
    @Singleton
    public ATACustomerService provideCustomerService(AWSCredentialsProvider credentialsProvider) {
        String primeKeys = "ata.customer.service.client.endpointConfiguration";
        String serviceEndpoint = System.getProperty(primeKeys + SERVICE_ENDPOINT);
        String signingRegion = System.getProperty(primeKeys + SIGNING_REGION);
        return ATACustomerServiceClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(serviceEndpoint, signingRegion))
            .withAwsCredentialsProvider(credentialsProvider)
            .build();
    }

    /**
     * Provides an AWSCredentialsProvider.
     * @return AWS credentials provider.
     */
    @Provides
    @Singleton
    public AWSCredentialsProvider provideAWSCredentialsProvider() {
        return new DefaultAWSCredentialsProviderChain();
    }
}
