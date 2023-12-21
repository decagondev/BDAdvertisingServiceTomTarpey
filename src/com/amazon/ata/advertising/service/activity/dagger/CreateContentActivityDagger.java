package com.amazon.ata.advertising.service.activity.dagger;

import com.amazon.ata.advertising.service.model.requests.CreateContentRequest;
import com.amazon.ata.advertising.service.model.responses.CreateContentResponse;
import com.amazon.ata.advertising.service.dependency.DaggerLambdaComponent;
import com.amazon.ata.advertising.service.dependency.LambdaComponent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateContentActivityDagger implements RequestHandler<CreateContentRequest, CreateContentResponse> {
    private static final LambdaComponent dagger = DaggerLambdaComponent.create();

    @Override
    public CreateContentResponse handleRequest(CreateContentRequest createContentRequest, Context context) {
        return dagger.provideCreateContentActivity().createContent(createContentRequest);
    }
}
