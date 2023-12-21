package com.amazon.ata.advertising.service.activity.dagger;

import com.amazon.ata.advertising.service.dependency.DaggerLambdaComponent;
import com.amazon.ata.advertising.service.dependency.LambdaComponent;
import com.amazon.ata.advertising.service.model.requests.UpdateContentRequest;
import com.amazon.ata.advertising.service.model.responses.UpdateContentResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateContentActivityDagger implements RequestHandler<UpdateContentRequest, UpdateContentResponse> {
    private static final LambdaComponent dagger = DaggerLambdaComponent.create();

    @Override
    public UpdateContentResponse handleRequest(UpdateContentRequest updateContentRequest, Context context) {
        return dagger.provideUpdateContentActivity().updateContent(updateContentRequest);
    }
}
