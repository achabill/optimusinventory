package optimusinventory.api.dao;

import optimusinventory.api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IUsersDao extends MongoRepository<User, String> {
    User findById(String id);
    User findByUsername(String username);
}