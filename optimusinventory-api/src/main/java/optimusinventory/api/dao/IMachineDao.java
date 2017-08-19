package optimusinventory.api.dao;

import optimusinventory.api.models.Machine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMachineDao extends MongoRepository<Machine, String> {
    Machine findById(String id);
}
