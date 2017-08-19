package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.auth.ITokenService;
import optimusinventory.api.dao.IMachineDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.log.IMachineLogService;
import optimusinventory.api.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/machines/")
@Api(value = "Machines")
public class MachineController {
    IHelpers helpers;
    IMachineDao machineDao;
    IMachineLogService machineLogService;
    ITokenService tokenService;

    public MachineController(IHelpers helpers, IMachineDao machineDao, IMachineLogService machineLogService, ITokenService tokenService) {
        this.helpers = helpers;
        this.machineDao = machineDao;
        this.machineLogService = machineLogService;
        this.tokenService = tokenService;
    }

    @ApiOperation(value = "Get all machines types")
    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public ResponseEntity<List<MachineType>> getAllMachineTypes(@RequestParam(value = "token") String token) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_MACHINE);
        return new ResponseEntity<>(helpers.getAllMachineTypes(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all Machines")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Machine>> getAllMachines(@RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_MACHINE);
        return new ResponseEntity<>(machineDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new machine")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Machine> add(@RequestParam(value = "token") String token,
                                       @Valid @RequestBody Machine machine) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.CREATE_MACHINE);
        Machine newMachine = machineDao.save(machine);
        MachineLog machineLog = new MachineLog(newMachine, tokenService.tokenValue(token), new Date(), LogAction.CREATE);
        machineLogService.log(machineLog);
        return new ResponseEntity<>(newMachine, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Machine by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Machine> getDebtorById(@RequestParam(value = "token") String token,
                                                 @PathVariable("id") String id) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_MACHINE);
        Machine machine = getMachineById(id);
        return new ResponseEntity<>(machine, HttpStatus.OK);
    }

    @ApiOperation(value = "Update machine")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Machine> updateMachineById(@RequestParam(value = "token") String token,
                                                     @PathVariable("id") String id,
                                                     @Valid @RequestBody Machine machine) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.UPDATE_MACHINE);
        getMachineById(id);
        if (machine.getId() == null || !machine.getId().equals(id)) {
            throw new Exception("Machine id does not match target id");
        }
        Machine newMachine = machineDao.save(machine);
        MachineLog machineLog = new MachineLog(newMachine, tokenService.tokenValue(token), new Date(), LogAction.UPDATE);
        machineLogService.log(machineLog);
        return new ResponseEntity<>(newMachine, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete machine by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteMachineById(@RequestParam(value = "token") String token,
                                                    @PathVariable("id") String id) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.DELETE_MACHINE);
        Machine machine = getMachineById(id);
        machineDao.delete(machine);
        MachineLog machineLog = new MachineLog(machine, tokenService.tokenValue(token), new Date(), LogAction.DELETE);
        machineLogService.log(machineLog);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }

    //internal helpers
    private Machine getMachineById(String id) throws Exception {
        Machine machine = machineDao.findById(id);
        if (machine == null) {
            throw new Exception("Machine with id does not exist");
        }
        return machine;
    }
}
