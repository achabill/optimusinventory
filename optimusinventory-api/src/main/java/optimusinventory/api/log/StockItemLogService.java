package optimusinventory.api.log;

import optimusinventory.api.dao.IStockItemLogDao;
import optimusinventory.api.models.StockItemLog;
import org.springframework.stereotype.Service;

@Service("StockItemLogService")
public class StockItemLogService implements IStockItemLogService {

    private IStockItemLogDao stockItemLogDao;

    public StockItemLogService(IStockItemLogDao stockItemLogDao) {
        this.stockItemLogDao = stockItemLogDao;
    }

    @Override
    public StockItemLog log(StockItemLog stockItemLog) {
        return stockItemLogDao.save(stockItemLog);
    }
}
