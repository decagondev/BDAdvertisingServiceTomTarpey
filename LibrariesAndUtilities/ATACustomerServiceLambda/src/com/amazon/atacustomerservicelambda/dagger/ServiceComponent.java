
package com.amazon.atacustomerservicelambda.dagger;

import com.amazon.atacustomerservicelambda.activity.GetCustomerProfileActivity;
import com.amazon.atacustomerservicelambda.activity.GetCustomerSpendCategoriesActivity;
//import com.amazon.atacustomerservicelambda.metrics.MetricsHandler;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
//@Component(modules = {MetricsModule.class})
public interface ServiceComponent {
    //MetricsHandler metricsHandler();

    GetCustomerProfileActivity getCustomerProfileActivity();

    GetCustomerSpendCategoriesActivity getCustomerSpendCategoriesActivity();
}
