package com.amazon.ata.advertising.service.activity.dagger;

import com.amazon.ata.advertising.service.model.requests.DeleteContentRequest;
import com.amazon.ata.advertising.service.model.responses.DeleteContentResponse;
import com.amazon.ata.advertising.service.dependency.DaggerLambdaComponent;
import com.amazon.ata.advertising.service.dependency.LambdaComponent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteContentActivityDagger implements RequestHandler<DeleteContentRequest, DeleteContentResponse> {
    private static final LambdaComponent dagger = DaggerLambdaComponent.create();

    @Override
    public DeleteContentResponse handleRequest(DeleteContentRequest deleteContentRequest, Context context) {
        return dagger.provideDeleteContentActivity().deleteContent(deleteContentRequest);
    }
}
