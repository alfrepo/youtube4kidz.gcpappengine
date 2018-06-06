package digital.alf.youtube4kidz.data.daos;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.datastore.*;
import digital.alf.youtube4kidz.data.objects.User;
import digital.alf.youtube4kidz.properties.ConfigProperties;
import digital.alf.youtube4kidz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
abstract class AbstractDao {

    public static final String KEY_PROPERTY = "__key__";

    @Autowired
    ConfigProperties configProperties;

    @Autowired
    ServletContext context;

    @Autowired
    UserService userService;

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


    protected final StructuredQuery.PropertyFilter getCurrentUserFilter(){
        Key key = Key.newBuilder(configProperties.getProjectId(), User.GCPENTITY_KIND, userService.getCurrentUserId()).build();
        return StructuredQuery.PropertyFilter.hasAncestor(key);
    }

    protected final PathElement getCurrentUserPathElement(){
        PathElement contextUser = PathElement.of(User.GCPENTITY_KIND, userService.getCurrentUserId());
        return contextUser;
    }
}
