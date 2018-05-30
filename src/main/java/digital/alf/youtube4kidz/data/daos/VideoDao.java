package digital.alf.youtube4kidz.data.daos;

import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;
import digital.alf.youtube4kidz.data.objects.Video;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoDao extends AbstractDao {

    public List<Video> listVideos() {
        List<Video> l;
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind(Video.GCPENTITY_KIND)
                .build();

        QueryResults<Entity> entities = getDataStore().run(query);
        return entitiesToVideos(entities);
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
        v.setId(e.getKey().getId());
        v.setLink(e.getString(Video.GCPENTITY_LINK));
        return v;
    }
}
