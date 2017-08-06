package optimusinventory.api.log;

import optimusinventory.api.dao.IUserLogDao;
import optimusinventory.api.models.UserLog;
import org.springframework.stereotype.Service;

@Service("UserLogService")
public class UserLogService implements IUserLogService {

    private IUserLogDao userLogDao;

    public UserLogService(IUserLogDao userLogDao) {
        this.userLogDao = userLogDao;
    }

    @Override
    public UserLog log(UserLog userLog) {
        return userLogDao.save(userLog);
    }
}
