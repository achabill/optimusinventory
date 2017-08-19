package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.dao.IMachineItemDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.models.MachineItem;
import optimusinventory.api.models.Privilege;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "Machine items")
@RequestMapping(value = "/api/machineitems")
public class MachineItemController {
    private IMachineItemDao machineItemDao;
    private IHelpers helpers;

    public MachineItemController(IMachineItemDao machineItemDao, IHelpers helpers) {
        this.machineItemDao = machineItemDao;
        this.helpers = helpers;
    }

    @RequestMapping(value = "/")
    @ApiOperation(value = "Get all machine items")
    public ResponseEntity<List<MachineItem>> getAllMachineItems(@RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_MACHINE_ITEMS);
        return new ResponseEntity<>(machineItemDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get machine item by id")
    @RequestMapping(value = "/{id}")
    public ResponseEntity<MachineItem> getMachineItemById(@PathVariable("id") String id,
                                                          @RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_MACHINE_ITEMS);
        return new ResponseEntity<>(getMachineItemById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Update Machine item by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<MachineItem> updateMachineItemById(@PathVariable("id") String id,
                                                             @Valid @RequestBody MachineItem machineItem,
                                                             @RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.UPDATE_MACHINE_ITEMS);
        if (machineItem.getId() != null || !machineItem.getId().equals(id)) {
            throw new Exception("Machine item with id does not match target id");
        }
        MachineItem newMachineItem = machineItemDao.save(machineItem);
        return new ResponseEntity<>(newMachineItem, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete Machine Item by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteMachineItemById(@PathVariable("id") String id,
                                                        @RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.DELETE_MACHINE_ITEMS);
        machineItemDao.delete(getMachineItemById(id));
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Create new machine item")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<MachineItem> add(@RequestParam(value = "token") String token,
                                           @Valid @RequestBody MachineItem machineItem) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.CREATE_MACHINE_ITEMS);
        MachineItem newMachineItem = machineItemDao.save(machineItem);
        return new ResponseEntity<>(newMachineItem, HttpStatus.CREATED);
    }

    //helpers
    private MachineItem getMachineItemById(String id) throws Exception {
        MachineItem machine = machineItemDao.findById(id);
        if (machine == null)
            throw new Exception("Machine with id not found");
        return machine;
    }
}
