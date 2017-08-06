package optimusinventory.api.log;

import optimusinventory.api.dao.IDebtorLogDao;
import optimusinventory.api.models.DebtorLog;
import org.springframework.stereotype.Service;

@Service("DebtorLogService")
public class DebtorLogService implements IDebtorLogService {
    private IDebtorLogDao debtorLogDao;

    public DebtorLogService(IDebtorLogDao debtorLogDao) {
        this.debtorLogDao = debtorLogDao;
    }

    @Override
    public DebtorLog log(DebtorLog debtorLog) {
        return debtorLogDao.save(debtorLog);
    }
}
