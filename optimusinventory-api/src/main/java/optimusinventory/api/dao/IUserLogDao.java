package optimusinventory.api.dao;

import optimusinventory.api.models.Debtor;
import optimusinventory.api.models.UserLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserLogDao extends MongoRepository<UserLog, String> {
    UserLog findById(String id);
}
