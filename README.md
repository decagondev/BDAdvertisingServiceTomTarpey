#  Unit 5 Project: ATA Advertising Service

## Preliminaries: The More Things Change

### Ambiguity, Complexity, and Scope

Ambiguity will be increasing from previous projects. Tasks instructions will contain fewer details, as we expect you to 
reference documentation and deep dive into the code yourself to understand how things work. Even if there isn't a 
specific task for it, you're of course welcome to create any diagrams or other notes as a reference for yourself or 
others!

There will be some increasing complexity as we work with ExecutorServices for the first time.

You'll have your fellow participants in the same situation as you, so remember to collaborate: rely on each other for 
assistance, and share your own knowledge.

## Unit 5 Project Progress and Tracking

### Doneness checklist

You're done with the project when: 

* You can access your service through `curl`
* Your CRs have passed review and all steps in your pipeline (including CR verification and TCTs) succeed
* You've submitted the Project Reflection response in Canvas
* You've completed the Participant End-of-Unit Survey (a short survey to help us improve the project and the project 
process for next time! Optional, but highly encouraged)

## The Problem: ATA Advertising

ATA's AdvertisingService serves advertisements for ATA. These advertisements show up on the retail website and use 
targeting to present different ATA advertisements to each individual. The targeting tries to take advantage of what 
Amazon knows about you to show you the particular ad that is most likely to appeal to you.

An overview of the service is covered in the [design document](DESIGN_DOCUMENT.md). We encourage you to read that now
before continuing below.

### Accessing operations via curl

Coral requires some HTTP headers that aren't supplied by default. The Coral Explorer automatically adds those headers
to its requests, but the framework we use in this project doesn't allow us to use the Explorer. When you use `curl`,
you'll need to supply a `Content-Encoding` and `Content-Type` that Coral recognizes, along with an
`Amz-Target` header indicating which API you want to call.

You can reference the templates and examples below when you're testing your code.

### GenerateAdvertisement
**template**

```
curl \
-X GET \
-sS 'http://localhost:1186/advertisement/<marketplaceId>?customerId=<customerId>' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.GenerateAdvertisement'
```

**example**

```
curl \
-X GET \
-sS 'http://localhost:1186/advertisement/1?customerId=10' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.GenerateAdvertisement'
```


### CreateContent

**template**

```
curl \
-X POST \
-sS 'http://localhost:1186/content' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1; charset=UTF-8' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.CreateContent' \
-d '{"__type":"com.amazon.ata.advertising.service#CreateContentRequest","content":"<content>", "marketplaceId":"<marketplaceId>"}'
```

**example**

```
curl \
-X POST \
-sS 'http://localhost:1186/content' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1; charset=UTF-8' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.CreateContent' \
-d '{"__type":"com.amazon.ata.advertising.service#CreateContentRequest","content":"Hello World", "marketplaceId":"1"}'
```


### AddTargetingGroup

**template**

```
curl \
-X POST \
-sS 'http://localhost:1186/targetingGroups' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1; charset=UTF-8' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.AddTargetingGroup' \
-d '{"__type":"com.amazon.ata.advertising.service#AddTargetingGroupRequest","contentId":"<contentId>", "targetingPredicates":"<targetingPredicates>"}'
```

**example**

```
curl \
-X POST \
-sS 'http://localhost:1186/targetingGroups' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1; charset=UTF-8' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.AddTargetingGroup' \
-d '{"__type":"com.amazon.ata.advertising.service#AddTargetingGroupRequest","contentId":"0b63284a-9c16-11e8-98d0-529269fb1459","targetingPredicates":[{"targetingPredicateType":"AGE","negate":false,"attributes":{"AgeRange":"AGE_26_TO_30"}}]}'
```


### UpdateClickThroughRate
**template**

```
curl \
-X PUT \
-sS 'http://localhost:1186/targetingGroups/<targetingGroupId>' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1; charset=UTF-8' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.UpdateClickThroughRate' \
-d '{"__type":"com.amazon.ata.advertising.service#UpdateClickThroughRateRequest","targetingGroupdId":"<targetingGroupId>", "clickThroughRate":"<clickThroughRate>}'
```

**example**

```
curl \
-X PUT \
-sS 'http://localhost:1186/targetingGroups/ba9568e2-b239-48bc-a167-8a2797f7935e' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1; charset=UTF-8' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.UpdateClickThroughRate' \
-d '{"__type":"com.amazon.ata.advertising.service#UpdateClickThroughRateRequest","targetingGroupId":"ba9568e2-b239-48bc-a167-8a2797f7935e", "clickThroughRate":"0.54"}'
```

### UpdateContent

**template**

```
curl \
-X PUT \
-sS 'http://localhost:1186/content/<contentId>' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1; charset=UTF-8' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.UpdateContent' \
-d '{"__type": "com.amazon.ata.advertising.service#UpdateContentRequest", "contentId": "<contentId>", "advertisingContent":<advertising content>}'
```

**example**

```
curl \
-X PUT \
-sS 'http://localhost:1186/content/df4568e2-b349-14cd-y35f-be34f7s3457ge' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1; charset=UTF-8' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.UpdateContent' \
-d '{"__type":"com.amazon.ata.advertising.service#UpdateContentRequest","contentId":"df4568e2-b349-14cd-y35f-be34f7s3457ge","advertisingContent":{"id":"df4568e2-b349-14cd-y35f-be34f7s3457ge","marketplaceId":"1","content":"join ata!"}}'
```


### DeleteContent
**template**

```
curl \
-X DELETE \
-sS 'http://localhost:1186/content/<contentId>' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.DeleteContent' \
-d '{"contentId":"<contentId>"}'
```


**example**

```
curl \
-X DELETE \
-sS 'http://localhost:1186/content/ba9568e2-b239-48bc-a167-8a2797f7935e' \
-H 'Content-Encoding: amz-1.0' \
-H 'Content-Type: application/x-amz-json-1.1' \
-H 'X-Amz-Target: com.amazon.ata.advertising.service.ATACurriculumAdvertisingService.DeleteContent' \
-d '{"contentId":"ba9568e2-b239-48bc-a167-8a2797f7935e"}'
```


## Project Preparedness Tasks

### [Project Preparedness Task 1: Use the Source, Again, and Again](tasks/project-preparedness-tasks/PreparednessTask01.md)
### [Project Preparedness Task 2: Hello, Project Buddy](tasks/project-preparedness-tasks/PreparednessTask02.md)


## Project Mastery Tasks

### [Mastery Task 1: Filter out the noise](tasks/project-mastery-tasks/MasteryTask01.md)
### [Mastery Task 2: Concurrent Tasks](tasks/project-mastery-tasks/MasteryTask02.md)
### [Mastery Task 3: Ads don't grow on trees (or do they?)](tasks/project-mastery-tasks/MasteryTask03.md)


## Project Reflection

Think over the entire project, from the introduction to the final mastery task completion. Answer three or more 
questions in the [Canvas Project Reflection Quiz](https://mlu.instructure.com/courses/605/quizzes/2052).

