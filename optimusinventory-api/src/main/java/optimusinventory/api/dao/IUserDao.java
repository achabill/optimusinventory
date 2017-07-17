package optimusinventory.api.dao;

/**
 * Created by Acha Bill on 7/17/2017.
 */

import optimusinventory.api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * IUserDAO
 *
 * @author Acha Bill [achabill12[at]gmail[dot]com]
 */
public interface IUserDao extends MongoRepository<User, String> {
    public User findById(String id);

    public User findByUsername(String username);

    public List<User> findByFirstName(String firstName);

    public List<User> findByLastName(String lastName);
}