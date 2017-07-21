package optimusinventory.api.dao;

import optimusinventory.api.models.Sale;
import optimusinventory.api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ISalesDao extends MongoRepository<Sale, String> {
    Sale findById(String id);
}
