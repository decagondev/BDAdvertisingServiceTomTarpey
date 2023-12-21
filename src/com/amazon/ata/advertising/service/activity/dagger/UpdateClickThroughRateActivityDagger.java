package com.amazon.ata.advertising.service.activity.dagger;

import com.amazon.ata.advertising.service.model.requests.UpdateClickThroughRateRequest;
import com.amazon.ata.advertising.service.model.responses.UpdateClickThroughRateResponse;
import com.amazon.ata.advertising.service.dependency.DaggerLambdaComponent;
import com.amazon.ata.advertising.service.dependency.LambdaComponent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateClickThroughRateActivityDagger implements RequestHandler<UpdateClickThroughRateRequest, UpdateClickThroughRateResponse> {
    private static final LambdaComponent dagger = DaggerLambdaComponent.create();

    @Override
    public UpdateClickThroughRateResponse handleRequest(UpdateClickThroughRateRequest updateClickThroughRateRequest, Context context) {
        return dagger.provideUpdateClickThroughRateActivity().updateClickThroughRate(updateClickThroughRateRequest);
    }
}
