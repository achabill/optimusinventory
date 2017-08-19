package optimusinventory.api.log;


import optimusinventory.api.models.MachineLog;

public interface IMachineLogService {
    MachineLog log(MachineLog machineItemLog);
}
