package digital.alf.youtube4kidz.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource("/WEB-INF/config.properties")
public class ConfigProperties {

    private String projectId;
    private String pathToJsonKeys;

    public String getPathToJsonKeys() {
        return pathToJsonKeys;
    }

    public void setPathToJsonKeys(String pathToJsonKeys) {
        this.pathToJsonKeys = pathToJsonKeys;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
