package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.dao.IDebtorsDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.models.Debtor;
import optimusinventory.api.models.Privilege;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "Manage debtors")
@RequestMapping("/api/debtors")
public class DebtorsController {

    private IDebtorsDao debtorsDao;
    private IHelpers helpers;

    public DebtorsController(IDebtorsDao debtorsDao, IHelpers helpers) {
        this.debtorsDao = debtorsDao;
        this.helpers = helpers;
    }

    @ApiOperation(value = "Get all debtors")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Debtor>> getAllDebtors(@RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_DEBTORS);
        return new ResponseEntity<>(debtorsDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new debtor")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Debtor> add(@RequestParam(value = "token") String token,
                                      @Valid @RequestBody Debtor debtor) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.CREATE_DEBTORS);
        Debtor newDebtor = debtorsDao.save(debtor);
        return new ResponseEntity<>(newDebtor, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get debtor by id")
    @RequestMapping(value  = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Debtor> getDebtorById(@RequestParam(value = "token") String token,
                                                @PathVariable("id") String id) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_DEBTORS);
        Debtor debtor = getDebtorById(id);
        return new ResponseEntity<>(debtor, HttpStatus.OK);
    }

    @ApiOperation(value = "Update debtor")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Debtor> updateDebtorById(@RequestParam(value = "token") String token,
                                                   @PathVariable("id") String id,
                                                   @Valid @RequestBody Debtor debtor) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.UPDATE_DEBTORS);
        getDebtorById(id);
        if(debtor.getId() != null || !debtor.getId().equals(id)){
            throw new Exception("Debtor id does not match target id");
        }
        Debtor newDebtor = debtorsDao.save(debtor);
        return new ResponseEntity<>(newDebtor, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete debtor by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteDebtorById(@RequestParam(value = "token") String token,
                                                   @PathVariable("id") String id) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.DELETE_DEBTORS);
        Debtor debtor = getDebtorById(id);
        debtorsDao.delete(debtor);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }

    //internal helpers
    private Debtor getDebtorById(String id) throws Exception {
        Debtor debtor = debtorsDao.findById(id);
        if(debtor == null){
            throw new Exception("Debtor with id does not exist");
        }
        return debtor;
    }
}
