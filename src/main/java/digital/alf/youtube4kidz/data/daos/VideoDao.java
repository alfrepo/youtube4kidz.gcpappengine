package digital.alf.youtube4kidz.data.daos;

import com.google.cloud.datastore.*;
import digital.alf.youtube4kidz.data.objects.User;
import digital.alf.youtube4kidz.data.objects.Video;
import digital.alf.youtube4kidz.properties.ConfigProperties;
import digital.alf.youtube4kidz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoDao extends AbstractDao {

    @Autowired
    ConfigProperties configProperties;

    @Autowired
    UserService userService;

    public List<Video> getVideos() {
        List<Video> l;

        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind(Video.GCPENTITY_KIND)
                .setFilter(getCurrentUserFilter())
                .build();

        QueryResults<Entity> entities = getDataStore().run(query);
        return entitiesToVideos(entities);
    }

    public Video getById(long id) {
        Key videoKey = Key.newBuilder(configProperties.getProjectId(), Video.GCPENTITY_KIND, id).addAncestor(getCurrentUserPathElement()).build();
        StructuredQuery.Filter keyFilter = StructuredQuery.PropertyFilter.eq(KEY_PROPERTY, videoKey);

        Query<Entity> query = Query.newEntityQueryBuilder()
                .setFilter(keyFilter)
                .build();

        QueryResults<Entity> entities = getDataStore().run(query);
        List<Video> videos = entitiesToVideos(entities);

        Assert.isTrue(videos.size() == 1, String.format("There should be exactly one video with the id: %s ",id));
        return videos.iterator().next();
    }

    private List<Video> entitiesToVideos(QueryResults<Entity> entities) {
        List<Video> l = new ArrayList<>();
        entities.forEachRemaining((Entity e) -> {
            l.add(entityToVideo(e));
        });
        return l;
    }

    private Video entityToVideo(Entity e) {
        Video v = new Video();
        v.setVideoId(e.getKey().getId());
        v.setYoutubeLink(e.getString(Video.GCPENTITY_YOUTUBE_LINK));
        v.setAuthor(e.getString(Video.GCPENTITY_AUTHOR));
        v.setName(e.getString(Video.GCPENTITY_NAME));
        return v;
    }
}
