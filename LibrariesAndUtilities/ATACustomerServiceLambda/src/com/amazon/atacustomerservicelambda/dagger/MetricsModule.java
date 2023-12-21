//
//package com.amazon.atacustomerservicelambda.dagger;
//
//import com.amazon.aws.cloudwatch.reporter.CloudWatchReporterFactory;
//import com.amazon.aws.cloudwatch.reporter.DimensionNameFilter;
//import com.amazon.coral.metrics.helper.MetricsHelper;
//import com.amazon.metrics.declarative.DefaultMetricsManager;
//import com.amazon.metrics.declarative.MetricsFactoriesHelper;
//import com.amazon.metrics.declarative.MetricsManager;
//import com.amazon.metrics.declarative.aspectj.MetricMethodAspect;
//import com.amazon.metrics.declarative.servicemetrics.aspectj.ServiceMetricsMethodAspect;
//import com.google.common.collect.ImmutableSet;
//import dagger.Module;
//import dagger.Provides;
//
//import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsync;
//import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClientBuilder;
//
//import org.aspectj.lang.Aspects;
//
//import javax.inject.Named;
//import javax.inject.Singleton;
//import java.util.Set;
//
//@Module
//public class MetricsModule {
//private static final Set<String> DIMENSION_NAMES = ImmutableSet.of("ServiceName", "Program", "Service", "Operation", "RequestId");
//    private static final String PROGRAM_NAME = "ATACustomerServiceLambda";
//    private static final String NAMESPACE = "CoralMetricsLambda";
//    private static final String REGION_NAME_KEY = "RegionName";
//
//    @Provides
//    @Singleton
//    @Named(REGION_NAME_KEY)
//    static String provideRegionName() {
//        return System.getenv("AWS_REGION");
//    }
//
//    @Provides
//    @Singleton
//    static AmazonCloudWatchAsync provideCloudWatch(@Named(REGION_NAME_KEY) final String regionName) {
//        return AmazonCloudWatchAsyncClientBuilder.standard()
//                .withRegion(regionName)
//                .build();
//    }
//
//    @Provides
//    @Singleton
//    static MetricsManager provideMetricsManager(final AmazonCloudWatchAsync cloudWatch) {
//        final DimensionNameFilter dimensionNameFilter = new DimensionNameFilter();
//        dimensionNameFilter.setWhitelist(true);
//        dimensionNameFilter.setDimensionNames(DIMENSION_NAMES);
//
//        final CloudWatchReporterFactory factory = new CloudWatchReporterFactory();
//        factory.setCloudWatchAsyncClient(cloudWatch);
//        factory.setNamespace(NAMESPACE);
//        factory.setPublishFrequencySeconds(60);
//        factory.setReporterFilter(dimensionNameFilter);
//
//        final MetricsHelper metricsFactory = new MetricsHelper();
//        metricsFactory.setReporters(factory);
//        metricsFactory.setProgram(PROGRAM_NAME);
//
//        final MetricsManager metricsManager = new DefaultMetricsManager(metricsFactory);
//
//        final MetricMethodAspect metricMethodAspect = Aspects.aspectOf(MetricMethodAspect.class);
//        metricMethodAspect.setFactories(MetricsFactoriesHelper.defaultMetricFactories());
//        metricMethodAspect.setMetricsManager(metricsManager);
//        ServiceMetricsMethodAspect serviceMetricsMethodAspect = Aspects.aspectOf(ServiceMetricsMethodAspect.class);
//        serviceMetricsMethodAspect.setMetricsManager(metricsManager);
//
//        return metricsManager;
//    }
//}
