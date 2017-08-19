package optimusinventory.api.dao;

import optimusinventory.api.models.StockItemLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IStockItemLogDao extends MongoRepository<StockItemLog, String> {
    StockItemLog findById(String id);
}
