package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.auth.ITokenService;
import optimusinventory.api.dao.IUserDao;
import optimusinventory.api.exception.*;
import optimusinventory.api.models.Previleges;
import optimusinventory.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acha Bill on 7/17/2017.
 */

@RestController
@RequestMapping(value = "/api/accounts")
@Api(value = "Account", description = "Account management")
public class UserController {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ITokenService tokenService;

    public UserController() {
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "Login", notes = "Logs in a user and returns an access token.")
    public ResponseEntity<UserAccessToken> login(@Valid @RequestBody User user) throws Exception {
        User oldUser = userDao.findByUsername(user.getUsername());
        if (oldUser == null)
            throw new UsernameOrPasswordNotFoundException("Username not found");
        if (!oldUser.getPassword().equals(tokenService.digest(user.getPassword())))
            throw new UsernameOrPasswordNotFoundException("Username or password incorrect");

        UserAccessToken userAccessToken = new UserAccessToken(oldUser, tokenService.setToken(oldUser));
        return new ResponseEntity<>(userAccessToken, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation(value = "Logout", notes = "Logs out a user and removes the access token")
    public ResponseEntity<User> logout(@RequestParam(value = "token", required = true) String token) throws Exception {

        User user = validateToken(token);
        tokenService.removeToken(token);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ApiOperation(value = "Signup", notes = "Signup a user with the required credentials")
    public ResponseEntity<UserAccessToken> signup(@Valid @RequestBody User user,
                                                  @RequestParam(value = "token", required = true) String token) throws Exception {

        verifyAdminToken(token);
        String username = user.getUsername();
        isUsernameAvailable(username);

        String password = tokenService.digest(user.getPassword());
        user.setPassword(password);
        if (user.getPrevileges() == null)
            user.setPrevileges(new ArrayList<Previleges>() {{
                add(Previleges.MANAGE_SALES);
            }});
        User newUser = userDao.save(user);
        String _token = tokenService.setToken(newUser);

        return new ResponseEntity<>(new UserAccessToken(newUser, _token), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Gets all users", notes = "Gets all users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(value = "token", required = true) String token) throws Exception {
        verifyAdminToken(token);
        return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Gets all previleges", notes = "Gets all previleges")
    public ResponseEntity<List<Previleges>> getAllPrevileges(@RequestParam(value = "token", required = true) String token) throws Exception {
        verifyAdminToken(token);
        List<Previleges> previleges = new ArrayList<Previleges>() {{
            add(Previleges.MANAGE_SALES);
            add(Previleges.MANAGE_SUMMARY);
            add(Previleges.MANAGE_ACCOUNTS);
            add(Previleges.MANAGE_DEBTORS);
            add(Previleges.MANAGE_ITEMS);
        }};
        return new ResponseEntity<>(previleges, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get a user", notes = "Gets the user with the specified id")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id,
                                            @RequestParam(value = "token", required = true) String token) throws Exception {
        verifyAdminToken(token);
        User user = validateUserId(id);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a user", notes = "Deletes the user with the specified id")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String id,
                                                 @RequestParam(value = "token", required = true) String token) throws Exception {
        verifyAdminToken(token);
        User user = validateUserId(id);

        if (!user.getPrevileges().contains(Previleges.MANAGE_ACCOUNTS))
            throw new CannotDeleteAdministratorException("Administrator cannot be deleted");

        userDao.delete(user);

        return new ResponseEntity<>("deleted", HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/update", method = RequestMethod.PUT)
    @ApiOperation(value = "Update a user", notes = "Updates the user with the specified id")
    public ResponseEntity<User> modifyUserSelf(@PathVariable("id") String id,
                                               @RequestParam(value = "token", required = true) String token,
                                               @Valid @RequestBody User user) throws Exception {
        verifyAdminToken(token);
        User _user = validateUserId(id);
        isUsernameAvailable(user.getUsername());

        _user = user;
        _user.setPassword(tokenService.digest(_user.getPassword()));
        userDao.save(_user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    private void isUsernameAvailable(String username) throws Exception {
        if (userDao.findByUsername(username) != null)
            throw new UserNameNotAvailableException(username + " is not available.");
    }

    private User validateToken(String token) {
        User u = tokenService.tokenValue(token);
        if (u == null)
            throw new UnauthorizedException("Token cannot be verified");
        return u;
    }

    private void verifyAdminToken(String token) throws Exception {
        if (!validateToken(token).getPrevileges().contains(Previleges.MANAGE_ACCOUNTS))
            throw new UnauthorizedException("Not enough privileges to perform action");
    }

    private User validateUserId(String id) {
        User user = userDao.findById(id);
        if (user == null)
            throw new UserNotFoundException("User with id does not exist");
        return user;
    }

    //Helpers
    private class UserAccessToken {
        private User user;
        private String token;

        public UserAccessToken(User user, String token) {
            this.user = user;
            this.token = token;
        }

        public User getUser() {
            return this.user;
        }

        public String getToken() {
            return this.token;
        }
    }

}
