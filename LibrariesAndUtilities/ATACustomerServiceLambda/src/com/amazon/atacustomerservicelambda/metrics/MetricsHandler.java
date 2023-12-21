//
//package com.amazon.atacustomerservicelambda.metrics;
//
//import com.amazon.coral.metrics.Metrics;
//import com.amazon.metrics.declarative.MetricsManager;
//
//import com.amazon.bones.lambdarouter.handlers.Job;
//import com.amazon.bones.lambdarouter.handlers.RequestHandler;
//
//import javax.inject.Inject;
//import java.util.Objects;
//
//public class MetricsHandler implements RequestHandler {
//    private static final String REQUEST_ID = "RequestId";
//
//    private final MetricsManager metricsManager;
//
//    @Inject
//    public MetricsHandler(final MetricsManager metricsManager) {
//        Objects.requireNonNull(metricsManager);
//
//        this.metricsManager = metricsManager;
//    }
//
//    @Override
//    public void before(Job job) throws Throwable {
//        Objects.requireNonNull(job);
//
//        // Add Lambda request ID in metrics, so metrics can be related to specific request.
//        // The request ID will be associated with all downstream metrics added to the MetricsManager
//        final Metrics metrics = metricsManager.get();
//        metrics.addProperty(REQUEST_ID, job.getContext().getAwsRequestId());
//    }
//
//    @Override
//    public void after(Job job) throws Throwable {
//        Objects.requireNonNull(job);
//
//        // MetricsManager maintains a usage count for each metrics instance, so each get() should be paired with pop()
//        // to signify the end of usage. The final pop() will eventually publish metrics
//        metricsManager.pop();
//    }
//}