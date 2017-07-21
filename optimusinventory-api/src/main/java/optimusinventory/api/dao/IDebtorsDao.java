package optimusinventory.api.dao;

import optimusinventory.api.models.Debtor;
import optimusinventory.api.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IDebtorsDao extends MongoRepository<Debtor, String> {
    Debtor findById(String id);
}
