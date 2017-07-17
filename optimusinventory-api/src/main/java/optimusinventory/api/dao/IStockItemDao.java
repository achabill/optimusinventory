package optimusinventory.api.dao;

import optimusinventory.api.models.StockItem;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Acha Bill on 7/17/2017.
 */
public interface IStockItemDao extends MongoRepository<StockItem, String> {
    public StockItem findById(String id);

    public StockItem findByName(String name);

    public List<StockItem> findByCategory(String category);
}
