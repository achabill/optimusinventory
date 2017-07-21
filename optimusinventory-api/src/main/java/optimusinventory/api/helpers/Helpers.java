package optimusinventory.api.helpers;

import optimusinventory.api.auth.ITokenService;
import optimusinventory.api.dao.IUsersDao;
import optimusinventory.api.models.Privilege;
import optimusinventory.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("Helpers")
public class Helpers implements IHelpers {

    IUsersDao usersDao;
    ITokenService tokenService;

    public Helpers(IUsersDao usersDao, ITokenService tokenService){
        this.usersDao = usersDao;
        this.tokenService = tokenService;
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return usersDao.findByUsername(username) != null;

    }

    @Override
    public User validateToken(String token) throws Exception {
        User u = tokenService.tokenValue(token);
        if (u == null)
            throw new Exception("Token cannot be verified");
        return u;
    }

    @Override
    public void validateRole(User user, Privilege privilege) throws Exception {
        if (!user.getPrivileges().contains(privilege))
            throw new Exception("Not enough privileges to perform action");
    }

    @Override
    public List<Privilege> getAllPrivileges() {
        List<Privilege> privileges = new ArrayList<Privilege>() {{
            add(Privilege.CREATE_DEBTORS);
            add(Privilege.READ_DEBTORS);
            add(Privilege.UPDATE_DEBTORS);
            add(Privilege.DELETE_DEBTORS);
            add(Privilege.CREATE_SALES);
            add(Privilege.UPDATE_SALES);
            add(Privilege.READ_SALES);
            add(Privilege.DELETE_SALES);
            add(Privilege.CREATE_ACCOUNTS);
            add(Privilege.READ_ACCOUNTS);
            add(Privilege.UPDATE_ACCOUNTS);
            add(Privilege.DELETE_ACCOUNTS);
            add(Privilege.CREATE_ITEMS);
            add(Privilege.READ_ITEMS);
            add(Privilege.UPDATE_ITEMS);
            add(Privilege.DELETE_ITEMS);
            add(Privilege.CREATE_SUMMARY);
            add(Privilege.READ_SUMMARY);
            add(Privilege.UPDATE_SUMMARY);
            add(Privilege.DELETE_SUMMARY);
        }};
        return privileges;
    }
}
