package digital.alf.youtube4kidz.video;

import com.google.cloud.datastore.*;
import digital.alf.youtube4kidz.data.AbstractDao;
import digital.alf.youtube4kidz.tools.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoDao extends AbstractDao {

    @Autowired
    ConfigProperties configProperties;

    public Long createVideo(Video video) {
        KeyFactory keyFactory = getDataStore().newKeyFactory().setKind(Video.GCPENTITY_KIND).addAncestor(getCurrentUserPathElement());
        IncompleteKey key = keyFactory.newKey();

        FullEntity<IncompleteKey> incompleteUserEntity = Entity.newBuilder(key)  // Create the Entity
                .set(Video.GCPENTITY_NAME, video.getName())           // Add Property ("author", book.getAuthor())
                .set(Video.GCPENTITY_AUTHOR, video.getAuthor())
                .set(Video.GCPENTITY_KIND, Video.GCPENTITY_KIND)
                .set(Video.GCPENTITY_YOUTUBE_LINK, video.getYoutubeLink())
                .build();
        Entity videoEntity = getDataStore().add(incompleteUserEntity);
        return videoEntity.getKey().getId();
    }

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
        StructuredQuery.Filter keyFilter = StructuredQuery.PropertyFilter.eq(GCP_DATASTORE_KEY_PROPERTY, videoKey);

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
        v.setEntityId(e.getKey().getId());
        v.setYoutubeLink(e.getString(Video.GCPENTITY_YOUTUBE_LINK));
        v.setAuthor(e.getString(Video.GCPENTITY_AUTHOR));
        v.setName(e.getString(Video.GCPENTITY_NAME));
        return v;
    }
}
