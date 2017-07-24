package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.dao.IDebtorLogDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.models.Debtor;
import optimusinventory.api.models.DebtorLog;
import optimusinventory.api.models.Privilege;
import optimusinventory.api.models.UserLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "Debtor logs")
@RequestMapping(value = "/api/logs/debtors")
public class DebtorLogController {
    IHelpers helpers;
    IDebtorLogDao debtorLogDao;

    public DebtorLogController(IHelpers helpers, IDebtorLogDao debtorLogDao) {
        this.helpers = helpers;
        this.debtorLogDao = debtorLogDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Get all debtor logs")
    public ResponseEntity<List<DebtorLog>> getAll(@RequestParam(value = "token") String token) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.LOGS);
        List<DebtorLog> logs = debtorLogDao.findAll();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get debtor log by id")
    public ResponseEntity<DebtorLog> getById(@RequestParam(value = "token") String token,
                                                 @PathVariable("id") String id) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.LOGS);
        DebtorLog log = debtorLogDao.findById(id);
        return new ResponseEntity<>(log, HttpStatus.OK);
    }

}
