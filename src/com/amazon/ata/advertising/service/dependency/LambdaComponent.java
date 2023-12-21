package com.amazon.ata.advertising.service.dependency;

//import com.amazon.coral.dagger.annotations.CoralComponent;
//import com.amazon.coral.service.lambda.LambdaEndpoint;

import com.amazon.ata.advertising.service.activity.*;
import dagger.Component;
import dagger.Module;

import javax.inject.Singleton;

@Singleton
//@CoralComponent(
//        modules = {CoralModule.class},
//        // No need to generate a Launcher
//        generateLauncher = false
//)
@Component(modules = {
        ExternalServiceModule.class,
        DaoModule.class,
        DynamoDBModule.class
})
public interface LambdaComponent {
    /**
     * The LambdaEndpoint to connect the lambda to Coral.
     * @return a LambdaEndpoint
     */
    //LambdaEndpoint getLambdaEndpoint();

    /**
     * Inject's targeting predicates with the DAOs they require.
     * @return a TargetingPredicateInjector
     */
    TargetingPredicateInjector getTargetingPredicateInjector();

    GenerateAdActivity provideGenerateAdActivity();

    AddTargetingGroupActivity provideAddTargetingGroupActivity();

    CreateContentActivity provideCreateContentActivity();

    DeleteContentActivity provideDeleteContentActivity();

    UpdateClickThroughRateActivity provideUpdateClickThroughRateActivity();

    UpdateContentActivity provideUpdateContentActivity();
}
