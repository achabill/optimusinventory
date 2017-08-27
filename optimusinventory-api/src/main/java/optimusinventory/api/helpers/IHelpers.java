package optimusinventory.api.helpers;

import optimusinventory.api.models.MachineType;
import optimusinventory.api.models.Privilege;
import optimusinventory.api.models.User;

import java.util.List;

public interface IHelpers {
    boolean isUsernameAvailable(String username);

    User validateToken(String token) throws Exception;

    void validateRole(User user, Privilege privilege) throws Exception;

    List<Privilege> getAllPrivileges();

    List<MachineType> getAllMachineTypes();
}
