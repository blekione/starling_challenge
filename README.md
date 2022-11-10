# starling-challenge

MARCIN KRUGLIK

## To run tests

```shell script
./mvnw clean test
```

## Running the application (in dev mode)

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
application will be available at `http://localhost:8080/`



## Packaging and running the application

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## REST API

The application provides a single API endpoints to create and retrive a ronding saving goal. When the goal is created
with `PUT /savings-goals/{accounUid}/category/{categoryUid}?fromDate={fromDate}` the application creates saving goal, adds
spending transactions for the full week for the given `accountUid` since `fromDate`, then calculates the amount to the
next full unit and adds it to the savings goal, and then repeat it for each full week until now.

For example you can test the API against the sandbox Starling API account with the bellow URL. But need to ask for
Authorisation key first.

```
http://localhost:8080/savings-goals/61ca3b12-33f0-47fd-a3be-7db27e10cffa/category/c254cc12-eb42-4838-bf2f-ba3637edd448?fromDate=2022-11-01
```

## Further improvement

* adding jdocs - in most cases code and methods names are self explanatory but if I have more time, I would definitely 
improve that
* adding persistent layer - this would enable the tracking of the weeks for which rounding values have been already
added to the savings goal. Then adding another API end point to repeat the operation would be possible
* improve reliability:
    * error handling form client requests
    * add operation record in the DB with UID at the start of the request and mark it completed before sending response to the 
    client
