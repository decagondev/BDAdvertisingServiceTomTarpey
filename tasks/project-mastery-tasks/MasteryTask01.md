## Mastery Task 1 - Filter out the noise

###Milestone 1: Update TargetingEvaluator and AddTargetingGroupActivity

While looking over the current service code, you see some instances where we're using a for loop to iterate and process 
elements in a collection. To help reduce the boilerplate iterative code and make the logic easier to follow, you decide 
to update the logic to use streams instead! 

For this task, you'll be updating logic in two classes to use a stream instead of a for loop to iterate and process 
elements.

`TargetingEvaluator`'s `evaluate` method determines if all the `TargetingPredicates` in a given `TargetingGroup` are `true` for 
the given `RequestContext`.

* Update `TargetingEvaluator`'s `evaluate` method to use a stream instead of a for loop to evaluate the 
`TargetingPredicateResult`.


`AddTargetingGroupActivity` is the activity class for the operation that adds a new targeting group to an existing piece 
of advertising content.

* Update `AddTargetingGroupActivity`'s `addTargetingGroup` method to use a stream when converting the 
`TargetingPredicate`s from the Coral model to its internal model.

We encourage you to take a look at the Stream [Javadoc](https://docs.oracle.com/javase/8/docs/api/?java/util/stream/Stream.html) to see what available methods can help implement the logic.

###Milestone 2: Update AdvertisementSelectionLogic

If you look at our service's `AdvertisementSelectionLogic`, you'll see that we're currently retrieving all
advertisements for a marketplace and randomly showing one of those advertisements to customers. To help increase our
ad click through rate, our marketing team has created targeting rules that they think will help show ads to customers
who are most likely to click on it. They've created ads specifically for each of these targeting groups. So now we want
to only show ads that customers are eligible for, based on the customer being a part of an ad's targeting group.

Update `AdvertisementSelectionLogic`'s `selectAdvertisement` method so that it randomly selects only from ads that the 
customer is eligible for based on the ad content's `TargetingGroup`. Your solution should use streams to evaluate the 
targeting groups (again, take a look at the streams javadoc if you need some guidance!). Use `TargetingEvaluator` to 
help filter out the ads that a customer is not eligible for. Then randomly return one of the ads that the customer is 
eligible for (if any). 

If there are no eligible ads for the customer, then return an `EmptyGeneratedAdvertisement`. Since these ads are being 
rendered on the retail website, we don't want to return null or throw exceptions if there are no eligible ads for the 
customer. Instead we return an empty string so that the front-end can handle rendering gracefully. 

**Exit Checklist:**

* You've updated `AdvertisementSelectionLogic`'s `selectAdvertisement` method so that it randomly selects an ad that the 
customer is eligible for 
* You've updated `TargetingEvaluator`'s `evaluate` method to use a stream instead of a for loop to evaluate the 
`TargetingPredicateResult`.
* You've set both `AdvertisementSelectionLogic` and `TargetingEvaluator`‘s `IMPLEMENTED_STREAM` boolean flag to `true`.
* You've updated `AddTargetingGroupActivity`'s `addTargetingGroup` method to use a stream when building the `Map` of
spend categories to return.
* Running the gradle command `./gradlew -q clean :test --tests com.tct.mastery.task1.MasteryTaskOneLogicTests` passes.
* Running the gradle command `./gradlew -q clean :test --tests com.tct.introspection.MT1IntrospectionTests` passes.

