package optimusinventory.api.dao;

import optimusinventory.api.models.Debtor;
import optimusinventory.api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Acha Bill on 7/17/2017.
 */
public interface IDebtorDao extends MongoRepository<User, String> {
    public Debtor findById(String id);

    public List<Debtor> findByFirstName(String firstName);

    public List<Debtor> findByLastName(String lastName);
}
