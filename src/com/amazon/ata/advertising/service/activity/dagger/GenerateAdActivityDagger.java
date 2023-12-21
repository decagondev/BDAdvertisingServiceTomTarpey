package com.amazon.ata.advertising.service.activity.dagger;

import com.amazon.ata.advertising.service.model.requests.GenerateAdvertisementRequest;
import com.amazon.ata.advertising.service.model.responses.GenerateAdvertisementResponse;
import com.amazon.ata.advertising.service.dependency.DaggerLambdaComponent;
import com.amazon.ata.advertising.service.dependency.LambdaComponent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GenerateAdActivityDagger implements RequestHandler<GenerateAdvertisementRequest, GenerateAdvertisementResponse> {
    private static final LambdaComponent dagger = DaggerLambdaComponent.create();

    @Override
    public GenerateAdvertisementResponse handleRequest(GenerateAdvertisementRequest generateAdvertisementRequest, Context context) {
        return dagger.provideGenerateAdActivity().generateAd(generateAdvertisementRequest);
    }
}
