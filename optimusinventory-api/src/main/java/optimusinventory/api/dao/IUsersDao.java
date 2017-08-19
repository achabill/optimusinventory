package optimusinventory.api.dao;

import optimusinventory.api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUsersDao extends MongoRepository<User, String> {
    User findById(String id);

    User findByUsername(String username);
}