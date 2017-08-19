package optimusinventory.api.dao;

import optimusinventory.api.models.MachineItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMachineItemDao extends MongoRepository<MachineItem, String> {
    MachineItem findById(String id);
}
