package optimusinventory.api.log;

import optimusinventory.api.models.StockItem;
import optimusinventory.api.models.StockItemLog;

public interface IStockItemLogService {
    StockItemLog log(StockItemLog stockItemLog);
}
