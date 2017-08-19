package optimusinventory.api.dao;

import optimusinventory.api.models.MachineLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMachineLogDao extends MongoRepository<MachineLog, String> {
    MachineLog findById(String id);
}

