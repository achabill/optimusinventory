package optimusinventory.api.dao;

import optimusinventory.api.models.Debtor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IDebtorsDao extends MongoRepository<Debtor, String> {
    Debtor findById(String id);
}
