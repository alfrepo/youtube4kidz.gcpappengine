# youtube4kidz.gcpappengine
A google app engine applicaiton.

The app-skelleton using Spring boots, maven, google app engine was created following this tutorial:
https://codelabs.developers.google.com/codelabs/cloud-app-engine-springboot/#9

Then it was converted to gradle.

## Importing the project into Idea
- Clone the project (directly from IDEA using  “VCS > Checkout from Version Control > Git” )
- After the project is opened, accept the popup for adding App Engine framework support. 
  In the "Project Structure > Modules > youtube4kidz_main" the item "Google App Engine Standard" must be listed now. ![alt text](https://lh3.googleusercontent.com/-g45_fzcF_vI/WxoxihMV8aI/AAAAAAAAALU/eSpwEfo4LbAcHNxffniVFISQrVTvQGXTwCHMYCw/s0/2018-06-08_09-34-33.png)
- Enable annotation processing in Idea, to support Lombok" via "Settings > Build > Compiler > Annotation Processors" and checking the checkbos "Enable annotation processing"
- put the service account key in JSON format, generated via "GCP > Credentials > Create Credentials > Service Account key" to webapp/WEB-INF/keys. Name it "YoutubeKidz-8bf46bba43f7"
- first build the project via "Build > Rebuild Project". Restart Idea, if you get the "ClassNotFound" exceptions
  Building is not part of the appengine-start process. Otherwise you will receive a ClassNotFound / MethodNotFound
- Then start project via Run “Google AppEngine Dev”

## Running the app engine locally with gradle
```
./gradlew appengineStart
```
When executed from GCP shell - use the button "web preview" on port 8080 to see the applicaiton.

## Deploy to the app engine 
Sending the app to the appengine.

```
./gradlew appengineDeploy
```


## Datastore
The data is stored using GCP-datastore. Here is the documentation for datastore: https://cloud.google.com/datastore/docs/how-to
