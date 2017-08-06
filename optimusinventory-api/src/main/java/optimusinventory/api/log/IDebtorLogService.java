package optimusinventory.api.log;

import optimusinventory.api.models.DebtorLog;

public interface IDebtorLogService {
    DebtorLog log(DebtorLog debtorLog);
}
