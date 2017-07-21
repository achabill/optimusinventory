package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.dao.IStockItemsDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.models.Privilege;
import optimusinventory.api.models.StockItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "Stock items")
@RequestMapping(value = "/api/items")
public class StockItemsController {
    private IStockItemsDao stockItemsDao;
    private IHelpers helpers;

    public StockItemsController(IStockItemsDao stockItemsDao, IHelpers helpers) {
        this.stockItemsDao = stockItemsDao;
        this.helpers = helpers;
    }

    @ApiOperation(value = "Get all stock items")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<StockItem>> getAllStockItems(@RequestParam(value = "token") String token) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_ITEMS);
        return new ResponseEntity<>(stockItemsDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add new Stock item")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<StockItem> add(@RequestParam(value = "token") String token,
                                         @Valid @RequestBody StockItem stockItem) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.CREATE_ITEMS);
        StockItem newStockItem = stockItemsDao.save(stockItem);
        return new ResponseEntity<>(newStockItem, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get stock item by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StockItem> getStockItemById(@RequestParam(value = "token") String token,
                                                      @PathVariable("id") String id) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_ITEMS);
        StockItem stockItem = stockItemsDao.findById(id);
        return new ResponseEntity<>(stockItem, HttpStatus.OK);
    }

    @ApiOperation(value = "Update stock item")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StockItem> updateStockItemById(@PathVariable("id") String id,
                                                         @RequestParam(value = "token") String token,
                                                         @Valid @RequestBody StockItem stockItem) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.UPDATE_ITEMS);
        getStockItemById(id);
        if(stockItem.getId() != null || !stockItem.getId().equals(id)){
            throw new Exception("StockItem id does not match target id");
        }
        StockItem newStockItem = stockItemsDao.save(stockItem);
        return new ResponseEntity<>(newStockItem, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete stock item by id")
    @RequestMapping(value = "/{id}", method =  RequestMethod.DELETE)
    public ResponseEntity<String> deleteStockItemById(@PathVariable("id") String id,
                                                      @RequestParam(value = "token") String token) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.UPDATE_ITEMS);
        StockItem stockItem = getStockItemById(id);
        stockItemsDao.delete(stockItem);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }

    //internal helpers
    private StockItem getStockItemById(String id) throws Exception {
        StockItem stockitem = stockItemsDao.findById(id);
        if(stockitem == null){
            throw new Exception("StockItem with id does not exist");
        }
        return stockitem;
    }
}
