package digital.alf.youtube4kidz.tools;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ConfigurationProperties
@PropertySource("/WEB-INF/config.properties")
public class ConfigProperties {

    private String projectId;
    private String pathToJsonKeys;
}
