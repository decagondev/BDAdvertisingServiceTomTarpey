package com.amazon.ata.advertising.service.activity.dagger;

import com.amazon.ata.advertising.service.model.requests.AddTargetingGroupRequest;
import com.amazon.ata.advertising.service.model.responses.AddTargetingGroupResponse;
import com.amazon.ata.advertising.service.dependency.DaggerLambdaComponent;
import com.amazon.ata.advertising.service.dependency.LambdaComponent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AddTargetingGroupActivityDagger implements RequestHandler<AddTargetingGroupRequest, AddTargetingGroupResponse> {
    private static final LambdaComponent dagger = DaggerLambdaComponent.create();

    @Override
    public AddTargetingGroupResponse handleRequest(AddTargetingGroupRequest addTargetingGroupRequest, Context context) {
        return dagger.provideAddTargetingGroupActivity().addTargetingGroup(addTargetingGroupRequest);
    }
}
