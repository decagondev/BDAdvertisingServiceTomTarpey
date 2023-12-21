//package com.amazon.ataprimeclubservicelambda.activity;
//
//import static com.amazon.bones.lambdarouter.LambdaRouterHttpMethod.GET;
//
//import com.amazon.ataprimeclubservicelambda.dagger.DaggerServiceComponent;
//import com.amazon.ataprimeclubservicelambda.dagger.ServiceComponent;
//import com.amazon.bones.lambdarouter.LambdaRouter;
//import com.amazon.bones.lambdarouter.LambdaRouterSignature;
//import com.amazon.bones.lambdarouter.handlers.ActivityInstantiatorHandler;
//import com.amazon.bones.lambdarouter.handlers.ActivityInvocationHandler;
//import com.amazon.bones.lambdarouter.handlers.RequestDeserializerHandler;
//import com.amazon.bones.lambdarouter.handlers.ResultSerializerHandler;
//import com.amazon.bones.lambdarouter.handlers.RouteMatcherHandler;
//import com.amazon.bones.util.DataConverter;
//import com.google.common.annotations.VisibleForTesting;
//import java.util.Objects;
//
///**
// * Router class is instantiate only once within same JVM and so it is reused by subsequent calls. This class is
// * a good place to keep references to resources that are expensive to create.
// */
//public class ATAPrimeClubServiceLambdaRouter extends LambdaRouter {
//
//    private ServiceComponent serviceComponent;
//
//    /**
//     * Default constructor.
//     */
//    public ATAPrimeClubServiceLambdaRouter() {
//    }
//
//    /**
//     * The main thing this methods is doing to is to initialize the request processing chain.
//     * <p>
//     * <b>Important note:</p> for each request a new instance of LambdaActivity is created. This is similar to Coral
//     * handles activities.
//     * </p>
//     * To store expensive to create resources between calls use fields from this class and inject them
//     * at creation time.
//     * <p>
//     * It is possible to use a memoization supplier if due to applicaiton specific reason is necessary to have
//     * the same LambdaActivity instance to handle all the request.
//     * </p>
//     */
//    @Override
//    public void initialize() {
//        DataConverter dataConverter = getDataConverter();
//        ServiceComponent serviceComponent = getServiceComponent();
//        getChain().setHandlers(
//                new ResultSerializerHandler(dataConverter),
//                new RouteMatcherHandler()
//                        .withRoute(new LambdaRouterSignature(GET, "/benefits/{marketplaceId}/{customerId}"),
//                            serviceComponent::getPrimeBenefitsActivity),
//                new ActivityInstantiatorHandler(),
//                new RequestDeserializerHandler(dataConverter),
//                serviceComponent.metricsHandler(),
//                new ActivityInvocationHandler());
//    }
//
//    @VisibleForTesting
//    void setServiceComponent(ServiceComponent serviceComponent) {
//        this.serviceComponent = serviceComponent;
//    }
//
//    private ServiceComponent getServiceComponent() {
//        if (Objects.isNull(serviceComponent)) {
//            return DaggerServiceComponent.create();
//        }
//
//        return serviceComponent;
//    }
//}
