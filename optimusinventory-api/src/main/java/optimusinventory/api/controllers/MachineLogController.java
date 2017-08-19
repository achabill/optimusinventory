package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.dao.IMachineLogDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.models.MachineLog;
import optimusinventory.api.models.Privilege;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/logs/machine")
@Api(value = "machine logs")
public class MachineLogController {
    IHelpers helpers;
    IMachineLogDao machineLogDao;

    public MachineLogController(IHelpers helpers, IMachineLogDao machineLogDao) {
        this.helpers = helpers;
        this.machineLogDao = machineLogDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Get all machine logs")
    public ResponseEntity<List<MachineLog>> getAll(@RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.LOGS);
        List<MachineLog> logs = machineLogDao.findAll();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get machine log by id")
    public ResponseEntity<MachineLog> getById(@RequestParam(value = "token") String token,
                                              @PathVariable("id") String id) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.LOGS);
        MachineLog log = machineLogDao.findById(id);
        return new ResponseEntity<>(log, HttpStatus.OK);
    }

}
