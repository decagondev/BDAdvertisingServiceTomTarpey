# What?

This package is just an example Java Beer Lambda package, fronted by API Gateway, that's generated from a Coral model. It's for use with BATS/BARS/LPT/Pipelines/BONES. It's API Gateway definition is built from an associated coral model. It's most useful when you just want to deploy a coral service on native AWS, and back it with Lambda. You still get all the goodness of Coral (build time clients, shareable clients, client config) but all the benefits of Native AWS (OnDemand compute). This also is the first time we're officially de-coupling a Java backend from a coral model. You can technically use whatever language you want in Lambda, provided you obey your Coral Model contract.

The CloudFormation is in `configuration/cloudFormation`, and you can see a few special things.

Parameters:

* `AttachLambdaFunctionToVPC` -> Whether or not to attach the lambda function to the VPC. This works out of the box with BONES VPCs. You could really use any VPC, but the definition below attaches to the specific exports BONES uses.
* `Stage` -> A stage to use as you see fit. Please keep this parameter even if you don't use it. (Our LPT needs at least one value)

You edit CloudFormation parameters in LPT (in the `stack_parameters` method) and then run `brazil-build synthesize-pipeline`, and kick off a build in your Lambda pipeline to see the new values take effect.

This package uses [CfnBuild](https://code.amazon.com/packages/CfnBuild) to build the final CloudFormation templates it uses. See the README in [CfnBuild](https://code.amazon.com/packages/CfnBuild) for details. It uses a composite build system, in this case `cfn-build-and-happy-trails`. (You can see this in your `Config` file.) We also have `cfn-build-and-brazilpython` as well, if you're feeling saucy.

Tl;dr you get the benefits of CfnBuild, but can still use HappyTrails/other build systems.

This package comes with some other goodies out of the box, we have [SAMToolkit](https://w.amazon.com/index.php/SAMToolkit) built in, as well as [Rapid Dev Environment](https://w.amazon.com/index.php/RDE).

## About the `AttachLambdaFunctionToVPC` parameter:
By default, this creates a new `LambdaSecurityGroup` that egress to anywhere. This is fine, but can likely be restricted by use case. Further, we provision lambda function IP address on the first two PrivateSubnets. Please be careful with this, as lambda can  exhaust the number of IP addresses, and it's highly likely that you're using those for your instances as well (this is a fancy way of saying : too much lambda activity can stop your instances from launching in those subnets.)
Further, the security group that's created here can *egress* anywhere, but that doesn't mean other security groups will necessarily allow *ingress* from the SG/Lambda function. You may need actually allow ingress into other security groups. You can automate this by [exporting](http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/using-cfn-stack-exports.html) the Security Group ID, and then importing it into the target security group using [Fn::ImportValue](http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-importvalue.html) in the target security group stack definition.
Finally, note there is a start up penalty for Lambdas that must attach ENIs (attach to VPCs).

# The Example Lambda

This lambda function emulates the standard "Coral Beer Service". The Coral Model looks nearly identical but with the HTTP Binding associated with it. To use Coral2Swagger, we need those HTTP Bindings, as those control the REST API we build in API Gateway. You can read more about those on the [wiki](https://w.amazon.com/index.php/Coral/Manual/HttpBinding).

We also include a basic "router" (You can see the router code [here](https://code.amazon.com/packages/BONESLambdaJavaRouter). We opted to do a SingleLambdaFunction with a router, as it's most similar to coral. This is not a requirement, and is totally optional.
## Single Lambda vs Multiple (or how to change it)
If you like SingleLambda mode, great.
If you don't like it, great. Here's how to change it (and how it works):

The model package holds your coral models, and during build time invokes coral2swagger. The coral2swagger generator is what generates API Gateway models. The thing to understand is how we generate the "LambdaFunction" name in the API Gateway definition.

Coral2Swagger's `build.json` (see your Model package) controls the behavior. Under the `swagger` key, you'll notice a `basePath`, an `executionRoleReferenceGenerator` and a  `lambdaReferenceGenerator`.

The `SingletonRefererenceGenerator` does exactly what it sounds like for each generator, namely, always refers to exactly one of the role or lambda respectively. These are hard coded logical names, so the role for APIG will always be `APIGatewayExecutionRole` and the lambda function logical name will be `LambdaFunction`. This is what ties the APIG definition and the CloudFormation together once injected.

You can use the other generators,  which are outlined in the [Coral2SwaggerGenerator Readme](https://code.amazon.com/packages/Coral2SwaggerGenerator/trees/mainline).

This example lambda is backed by DyanmoDB when deployed to Lambda, otherwise it's a memory db.

# SAMToolkit

SAMToolkit is when you want to deploy a local lambda function to your own AWS Account.
In short, it packages (in a similar way to BATS) your lambda, and deploys (including your CloudFormation) to whatever account you configure it for. SAMToolkit supports many other features as well, which are covered [in the wiki](https://w.amazon.com/index.php/SAMToolkit).

Our recommended setup for SAM Toolkit is to edit the generated `SAMToolkit.devenv` file, and plug in the relevant details. You can provide credentials through one of Odin ([not recommended, users aren't ideal](https://w.amazon.com/bin/view/Brelandm/NoUsers/)), Isengard Role Assumption (yay!) or AWS Identity Broker Role Assumption (yay!)

For actually doing the sam commands, we alias the local "./sam" to `brazil-build-tool-exec sam $1` so you can run things like `./sam package && ./sam deploy && ./sam test`. These will make a "BATS like" package, upload and deploy, and then run your tests. Again, the wiki has the most details on this.

# RDE

[RDE](https://w.amazon.com/index.php/RDE) is a tool that emulates a full development environment on your local machine (including [laptops with full credential access](https://w.amazon.com/index.php/RDE/How_do_I#Use_AWS_credentials_on_my_Mac)). It's based on Docker.
We set up a base `definition.yaml` file that sets up the RDE workflow. You can add more to this file to run more custom workflows against your code.

RDE should be installed ([how to install](https://w.amazon.com/index.php/RDE/Environment_Setup)), and you can then run your tests locally and iterate quickly on changes.

To run an example "get and put beer" run the following:

(Assuming you have RDE installed.)

```
rde wflow run
rde wflow run test
```

Two "events" are mapped by default. Our example lambda function is based on the Lambda Proxy Input/Output. The `mounts/events/` files are the inputs we expect for each call. This is what a standard Lambda Input request would look like.

You can also iterate on code changes by invoking the local endpoint:

```
rde wflow run
curl http://localhost:1180/beers
```

You can debug your lambda functions locally by following [this guide](https://w.amazon.com/index.php/RDE/Tutorial/SAM_Applications#Debugging_your_local_Lambda_functions).

To setup credentials use the following [guide](https://w.amazon.com/index.php/RDE/How_do_I#Use_AWS_credentials_on_my_Mac).

You can also run [Hydra](https://w.amazon.com/index.php/HydraTestPlatform) tests locally with RDE. Hydra Test Platform is a test automation sollution for Native AWS. The current version of Hydra uses Lambda as the execution engine. Given RDE's support for Lambda you can run the tests locally against your service.
See the RDE Hydra integration [docs](https://w.amazon.com/index.php/RDE/Tutorial/Hydra) for more information.
	 
After starting your stack all you have to do is run the workflow step that invokes the tests: 
```
rde wflow run
rde wflow run -s run-hydra-tests
```
	

# General workflow

For testing with lambda, here's our current recommendation:

1. Unit tests. Run good old fashioned unit tests against your code.
2. RDE. Use the local endpoint to test your function, then craft RDE inputs and outputs and run those locally to validate your function.
3. SAMToolkit. Deploy your Lambda + Associated cloud formation to your own personal testing account, make sure it's all happy.
4. CR and Push. Run integration tests in your pipeline for your function.

# API Gateway / Coral Integration

Along with this package, you should have gotten an associated CoralLambdaModel package. The Coral Model package is of course just a good old fashioned coral model. The key differences between it and a generic coral model:

1. The `Coral2Swagger = 1.0;` build-tool dependency.
2. The `build-system` being `happytrails-and-coral2swagger`
3. The presence of a `build.json` file.
4. The Coral Model itself having the HTTP bindings.

The [Coral2Swagger package](https://code.amazon.com/packages/Coral2SwaggerGenerator/trees/mainline) adds some logic that knows how to translate from Coral to swagger (the language API Gateway speaks.) We then set the build system to a hybrid build, happy trails first to do our normal coral build, and then `coral2swagger` to generate the swagger definition. We generate swagger at build time as a build artifact, so we can consume it later. The `coral2swagger` build tool uses the `build.json` file to get any data it can't infer using the coral model itself. Finally, the coral model must have HTTP bindings for the swagger generation to work successfully.

CfnBuild injects your final swagger definition into CloudFormation. This is what we ultimately end up deploying in the pipeline. You can see in the `configuration/cloudFormation/lambda_cloudformation.template.yml` file, we'll inject the swagger into the variable `REPLACE_WITH_SWAGGER_DEFINITION`.

Do take note : To force API Gateway to create a new deployment each time, we use the `Type: 'AWS::Serverless::Api` type, not the APIGateway type directly.

You should be able to use the shapes like normal in your lambda service.

# Additional Tips:

On MacOS, you probably need to run the following command first (see [this sage post answer](https://sage.amazon.com/questions/118544?q=Failed-executing-or-communicating-with-Brazil-Package-Cache-Answered#277151)):

```bash
brazil-package-cache enable_edge_cache
```

You'll need `jq` if you don't have it: (CfnBuild needs JQ to do its magic.)
```bash
brew install jq
```

