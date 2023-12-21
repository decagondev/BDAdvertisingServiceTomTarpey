## Mastery Task 3 - Ads don't grow on trees (or do they?)

In Mastery Task 1, we updated `AdvertisementSelectionLogic` to randomly show an advertisement from only the ads that a 
customer is eligible for. We did this to gather some initial data on how often customers in certain targeting groups 
would click the ads, and we have updated the targeting group data (using the `UpdateTargetingGroup` operation) with the 
latest click through rates. Now, the product team would like to update the advertisement selection logic to select the 
eligible ad with the highest click through rate, instead of just a random eligible ad. If we're going to show 
a customer an ad, let's make it the one that they're most likely to click on!

Update `AdvertisementSelectionLogic` so that `selectAdvertisement` returns the ad that the customer is eligible for 
with the highest click through rate. Use a `TreeMap` to help sort the eligible ads by their click through rate. You'll 
need the `TreeMap` to use a `Comparator` that sorts the `TargetingGroups` by click through rate from highest to lowest. 
We encourage you to look at `TreeMap`'s and `Comparator`'s Javadocs to see what provided methods can help you implement 
this!

Remember that a piece of ad content can have multiple `TargetingGroups` (each with its own click through rate) and a 
customer can be part of multiple targeting groups for a single piece of ad content. That means, when evaluating 
targeting groups for an ad, we should be considering the eligible targeting group with the highest click through rate. 
For example, let's say we have an ad with two targeting groups: one that includes customers that bought logic books 
with a 0.15 click through rate and another that includes customers that have bought technical books with a 0.30 click 
through rate. For a customer that has bought both technical books AND logic books, we should use the targeting group 
with the 0.30 click through rate when sorting through the ads, since it has the higher click through rate.

**Exit Checklist:**

* You've updated `AdvertisementSelectionLogic` to use a `TreeMap` to help select an eligible ad with the highest click 
through rate.
* You've added/updated unit tests for your changes
* Running the gradle command `./gradlew -q clean :test --tests "com.tct.mastery.task3.*"` passes.

