package digital.alf.youtube4kidz.data.daos;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import digital.alf.youtube4kidz.properties.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
abstract class AbstractDao {

    @Autowired
    ConfigProperties configProperties;

    @Autowired
    ServletContext context;

    private Datastore datastore;

    protected final Datastore getDataStore() {
        if(this.datastore != null) return this.datastore;

        try {
            InputStream is = context.getResourceAsStream(configProperties.getPathToJsonKeys());

            DatastoreOptions options = DatastoreOptions.newBuilder()
                    .setProjectId(configProperties.getProjectId())
                    .setCredentials(GoogleCredentials.fromStream(is)).build();

            this.datastore = options.getService();
            return this.datastore;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create a Datastore. Probably the projectname or the path to the oauth JSON is wrong. Message: "+e.getMessage(), e);
        }
    }
}