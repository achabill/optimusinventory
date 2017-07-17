package optimusinventory.api.dao;

import optimusinventory.api.models.Sale;
import optimusinventory.api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Acha Bill on 7/17/2017.
 */
public interface ISaleDao extends MongoRepository<User, String> {
    public Sale findById(String id);
}
