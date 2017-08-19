package optimusinventory.api.log;

import optimusinventory.api.models.StockItemLog;

public interface IStockItemLogService {
    StockItemLog log(StockItemLog stockItemLog);
}
