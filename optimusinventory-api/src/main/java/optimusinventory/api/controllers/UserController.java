package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.auth.ITokenService;
import optimusinventory.api.dao.IUsersDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.log.IUserLogService;
import optimusinventory.api.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
@Api(value = "Users", description = "User management")
public class UserController {

    private IHelpers helpers;
    private ITokenService tokenService;
    private IUsersDao usersDao;
    private IUserLogService userLogService;

    public UserController(IHelpers helpers, ITokenService tokenService, IUsersDao usersDao, IUserLogService userLogService) {
        this.helpers = helpers;
        this.tokenService = tokenService;
        this.usersDao = usersDao;
        this.userLogService = userLogService;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "Login", notes = "Logs in a user and returns an access token.")
    public ResponseEntity<UserAccessToken> login(@Valid @RequestBody UserLogin user) throws Exception {


        User oldUser = usersDao.findByUsername(user.getUsername());
        String token = tokenService.getToken(oldUser);
        if(token != null){
            throw new Exception("You are already logged in with token: " + token);
        }

        if (oldUser == null)
            throw new Exception("Username not found");
        if (!oldUser.getPassword().equals(tokenService.digest(user.getPassword())))
            throw new Exception("Username or password incorrect");

        UserAccessToken userAccessToken = new UserAccessToken(oldUser, tokenService.setToken(oldUser));
        UserLog userLog = new UserLog(oldUser, null, UserLogAction.LOGIN, new Date());
        userLogService.log(userLog);
        return new ResponseEntity<>(userAccessToken, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation(value = "Logout", notes = "Logs out a user and removes the access token")
    public ResponseEntity<User> logout(@RequestParam(value = "token") String token) throws Exception {

        User user = helpers.validateToken(token);
        tokenService.removeToken(token);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(value = "Sign up", notes = "Sign up a user with the required credentials")
    public ResponseEntity<UserAccessToken> addNewUser(@Valid @RequestBody User user,
                                                  @RequestParam(value = "token") String token) throws Exception {

        helpers.validateRole(helpers.validateToken(token), Privilege.CREATE_ACCOUNTS);
        String username = user.getUsername();
        if(helpers.isUsernameAvailable(username)){
            throw new Exception("Username is not available");
        }

        String password = tokenService.digest(user.getPassword());
        user.setPassword(password);
        if (user.getPrivileges() == null)
            //default privilege is sales
            user.setPrivileges(new ArrayList<Privilege>() {{
                add(Privilege.CREATE_SALES);
                add(Privilege.UPDATE_SALES);
                add(Privilege.UPDATE_ITEMS);
                add(Privilege.READ_ITEMS);
                add(Privilege.CREATE_DEBTORS);
                add(Privilege.READ_DEBTORS);
                add(Privilege.UPDATE_DEBTORS);
            }});
        User newUser = usersDao.save(user);
        String _token = tokenService.setToken(newUser);
        UserLog userLog = new UserLog(tokenService.tokenValue(token), newUser, UserLogAction.ADD, new Date());
        userLogService.log(userLog);
        return new ResponseEntity<>(new UserAccessToken(newUser, _token), HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Gets all users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_ACCOUNTS);
        return new ResponseEntity<>(usersDao.findAll(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/privileges", method = RequestMethod.GET)
    @ApiOperation(value = "Gets all privileges")
    public ResponseEntity<List<Privilege>> getAllPrivileges(@RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_ACCOUNTS);

        return new ResponseEntity<>(helpers.getAllPrivileges(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get a user", notes = "Gets the user with the specified id")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id,
                                            @RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_ACCOUNTS);

        return new ResponseEntity<>(getUserById(id), HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete a user", notes = "Deletes the user with the specified id")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String id,
                                                 @RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.DELETE_ACCOUNTS);
        usersDao.delete(getUserById(id));
        UserLog userLog = new UserLog(tokenService.tokenValue(token), null, UserLogAction.DELETE, new Date());
        userLogService.log(userLog);
        return new ResponseEntity<>("deleted", HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update a user", notes = "Updates the user with the specified id")
    public ResponseEntity<User> updateUserById(@PathVariable("id") String id,
                                               @RequestParam(value = "token") String token,
                                               @RequestParam(value = "modifypassword") boolean modifypassword,
                                               @Valid @RequestBody User user) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.UPDATE_ACCOUNTS);
        getUserById(id);
        if(user.getId() == null || !user.getId().equals(id)){
            throw new Exception("User id does not match target id");
        }
        if(modifypassword){
            user.setPassword(tokenService.digest(user.getPassword()));
        }
        User newUser = usersDao.save(user);
        UserLog userLog = new UserLog(tokenService.tokenValue(token), newUser, UserLogAction.UPDATE, new Date());
        userLogService.log(userLog);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //internal helpers
    private User getUserById(String id) throws Exception {
        User user = usersDao.findById(id);
        if(user == null){
            throw new Exception("User with id does not exist");
        }
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
            return user;
        }

        public String getToken() {
            return token;
        }
    }

}
