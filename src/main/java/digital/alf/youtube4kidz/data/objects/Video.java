package digital.alf.youtube4kidz.data.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Video {
    public static final String GCPENTITY_KIND = "video";
    public static final String GCPENTITY_LINK = "link";

    private long id;
    private String link;
}
