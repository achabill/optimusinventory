package optimusinventory.api.dao;

import optimusinventory.api.models.StockItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IStockItemsDao extends MongoRepository<StockItem, String> {
    StockItem findById(String id);
    StockItem findByName(String name);
}
