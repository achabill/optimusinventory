package optimusinventory.api.log;

import optimusinventory.api.dao.IMachineLogDao;
import optimusinventory.api.models.MachineLog;
import org.springframework.stereotype.Service;

@Service("MachineItemLogService")
public class MachineLogService implements IMachineLogService {
    private IMachineLogDao machineItemLogDao;

    public MachineLogService(IMachineLogDao machineItemLogDao) {
        this.machineItemLogDao = machineItemLogDao;
    }


    @Override
    public MachineLog log(MachineLog machineItemLog) {
        return machineItemLogDao.save(machineItemLog);
    }
}
