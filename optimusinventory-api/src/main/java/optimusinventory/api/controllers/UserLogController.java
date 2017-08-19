package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.dao.IUserLogDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.models.Privilege;
import optimusinventory.api.models.UserLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "User logs")
@RequestMapping(value = "/api/logs/users")
public class UserLogController {

    private IHelpers helpers;
    private IUserLogDao userLogDao;

    public UserLogController(IHelpers helpers, IUserLogDao userLogDao) {
        this.helpers = helpers;
        this.userLogDao = userLogDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Get all user logs")
    public ResponseEntity<List<UserLog>> getAll(@RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.LOGS);
        List<UserLog> logs = userLogDao.findAll();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get user log by id")
    public ResponseEntity<UserLog> getById(@RequestParam(value = "token") String token,
                                           @PathVariable("id") String id) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.LOGS);
        UserLog log = userLogDao.findById(id);
        return new ResponseEntity<>(log, HttpStatus.OK);
    }

}
