package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.dao.IStockItemLogDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.models.Privilege;
import optimusinventory.api.models.StockItem;
import optimusinventory.api.models.StockItemLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@Api(value = "StockItem logs")
@RequestMapping(value = "/api/logs/items")
public class StockItemLogController {

    private IHelpers helpers;
    private IStockItemLogDao stockItemLogDao;

    public StockItemLogController(IHelpers helpers, IStockItemLogDao stockItemLogDao) {
        this.helpers = helpers;
        this.stockItemLogDao = stockItemLogDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation("Get all stockItem logs")
    public ResponseEntity<List<StockItemLog>> getAll(@RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.LOGS);
        List<StockItemLog> logs = stockItemLogDao.findAll();
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation("Get stockItem logs by id")
    public ResponseEntity<StockItemLog> getById(@RequestParam(value = "token") String token,
                                                @PathVariable("id") String id) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.LOGS);
        StockItemLog log = stockItemLogDao.findById(id);
        return new ResponseEntity<>(log, HttpStatus.OK);
    }


}
