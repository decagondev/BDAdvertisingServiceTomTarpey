package com.amazon.ataprimeclubservicelambda.service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;

public class ATAPrimeClubServiceClientBuilder {

     public static Builder standard() {
         return new Builder();
     }

     public static final class Builder {
         private AwsClientBuilder.EndpointConfiguration endpointConfiguration;
         private AWSCredentialsProvider awsCredentialsProvider;

        public Builder withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration endpointConfiguration) {
            this.endpointConfiguration = endpointConfiguration;
            return this;
        }

        public Builder withAwsCredentialsProvider(AWSCredentialsProvider awsCredentialsProvider) {
            this.awsCredentialsProvider = awsCredentialsProvider;
            return this;
        }

        public ATAPrimeClubService build() {
            return new ATAPrimeClubService();
        }
     }
}

