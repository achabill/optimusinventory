package optimusinventory.api.dao;

import optimusinventory.api.models.DebtorLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IDebtorLogDao extends MongoRepository<DebtorLog, String> {
    DebtorLog findById(String id);
}
