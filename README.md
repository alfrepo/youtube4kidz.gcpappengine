# youtube4kidz.gcpappengine
A google app engine applicaiton.

The app-skelleton using Spring boots, maven, google app engine was created following this tutorial:
https://codelabs.developers.google.com/codelabs/cloud-app-engine-springboot/#9

## Running the app engine locally
```
./mvnw -DskipTests appengine:devserver
```
When executed from GCP shell - use the button "web preview" on port 8080 to see the applicaiton.

## Deploy to the app engine 
Committing the app to the appengine.

```
./mvnw -DskipTests appengine:update
```
