package digital.alf.youtube4kidz.video;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Implements ResourceSupport, so that it can be used by the REST controller
 */
@Getter
@Setter
public class Video extends ResourceSupport {
    public static final String GCPENTITY_NAME = "name";
    public static final String GCPENTITY_KIND = "video";
    public static final String GCPENTITY_YOUTUBE_ID = "youtubeId";
    public static final String GCPENTITY_AUTHOR = "author";
    public static final String GCPENTITY_TAGS = "tags";

    private long entityId;
    private String name;
    private String youtubeId;
    private String author;
    private List<String> tags;
}
