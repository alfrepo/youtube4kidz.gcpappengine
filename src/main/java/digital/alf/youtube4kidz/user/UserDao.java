package digital.alf.youtube4kidz.user;

import com.google.cloud.datastore.*;
import digital.alf.youtube4kidz.data.AbstractDao;
import org.springframework.stereotype.Component;
import com.google.cloud.datastore.Entity;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao extends AbstractDao {

    public Long createUser(User user) {
        KeyFactory keyFactory = getDataStore().newKeyFactory().setKind(User.GCPENTITY_KIND);
        IncompleteKey key = keyFactory.newKey();


        FullEntity<IncompleteKey> incompleteUserEntity = Entity.newBuilder(key)  // Create the Entity
                .set(User.GCPENTITY_LOGIN, user.getLogin())           // Add Property ("author", book.getAuthor())
                .set(User.GCPENTITY_PASSWORD, user.getPassword())
                .build();
        Entity userEntity = getDataStore().add(incompleteUserEntity);
        return userEntity.getKey().getId();
    }


    public List<User> listUsers() {
        List<User> l;
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind(User.GCPENTITY_KIND)
                .setOrderBy(StructuredQuery.OrderBy.asc(User.GCPENTITY_LOGIN))
                .build();

        QueryResults<Entity> entities = getDataStore().run(query);
        return entitiesToUsers(entities);
    }

    private List<User> entitiesToUsers(QueryResults<Entity> entities) {
        List<User> l = new ArrayList<>();
        entities.forEachRemaining((Entity e) -> {
            l.add(entityToUser(e));
        });
        return l;
    }

    private User entityToUser(Entity e) {
        User u = new User();
        u.setPassword(e.getString(User.GCPENTITY_PASSWORD));
        u.setLogin(e.getString(User.GCPENTITY_LOGIN));
        u.setId(e.getKey().getId());
        return u;
    }
}
