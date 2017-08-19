package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.auth.ITokenService;
import optimusinventory.api.dao.IStockItemsDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.log.IStockItemLogService;
import optimusinventory.api.models.LogAction;
import optimusinventory.api.models.Privilege;
import optimusinventory.api.models.StockItem;
import optimusinventory.api.models.StockItemLog;
import optimusinventory.api.parsers.IExcelFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "Stock items")
@RequestMapping(value = "/api/items")
public class StockItemController {
    private IStockItemsDao stockItemsDao;
    private IHelpers helpers;
    private IExcelFileService excelFileService;
    private ITokenService tokenService;
    private IStockItemLogService stockItemLogService;

    public StockItemController(IStockItemsDao stockItemsDao, IHelpers helpers, IExcelFileService excelFileService, ITokenService tokenService, IStockItemLogService stockItemLogService) {
        this.stockItemsDao = stockItemsDao;
        this.helpers = helpers;
        this.excelFileService = excelFileService;
        this.tokenService = tokenService;
        this.stockItemLogService = stockItemLogService;
    }

    @ApiOperation(value = "Get all stock items")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<StockItem>> getAllStockItems(@RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_ITEMS);
        return new ResponseEntity<>(stockItemsDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Add new Stock item")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<StockItem> add(@RequestParam(value = "token") String token,
                                         @Valid @RequestBody StockItem stockItem) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.CREATE_ITEMS);
        List<StockItem> allStockItems = stockItemsDao.findAll();
        int i = allStockItems.indexOf(stockItem);
        StockItem item = null;
        if (i >= 0) {
            StockItem _item = allStockItems.get(i);
            _item.setQuantity(_item.getQuantity() + stockItem.getQuantity());
            item = _item;
        } else {
            item = stockItem;
        }
        StockItem newStockItem = stockItemsDao.save(item);
        StockItemLog stockItemLog = new StockItemLog(tokenService.tokenValue(token), newStockItem, new Date(), LogAction.CREATE);
        stockItemLogService.log(stockItemLog);

        return new ResponseEntity<>(newStockItem, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Add stock items from an excel file")
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ResponseEntity<List<StockItem>> addFromExcelFile(@RequestParam(value = "token") String token,
                                                            @RequestParam(value = "file") MultipartFile file) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.CREATE_ITEMS);
        List<StockItem> fileStockItems = excelFileService.parse(file);
        List<StockItem> allStockItems = stockItemsDao.findAll();
        List<StockItem> update = new ArrayList<>();
        for (StockItem item : fileStockItems) {
            int i = allStockItems.indexOf(item);
            if (i >= 0) {
                StockItem _item = allStockItems.get(i);
                _item.setQuantity(item.getQuantity() + _item.getQuantity());
                update.add(_item);
            } else {
                update.add(item);
            }
        }
        List<StockItem> newStockItems = stockItemsDao.save(update);
        newStockItems.forEach(item -> {
            StockItemLog stockItemLog = new StockItemLog(tokenService.tokenValue(token), item, new Date(), LogAction.CREATE);
            stockItemLogService.log(stockItemLog);
        });
        return new ResponseEntity<>(newStockItems, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get stock item by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<StockItem> getStockItemById(@RequestParam(value = "token") String token,
                                                      @PathVariable("id") String id) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_ITEMS);
        StockItem stockItem = stockItemsDao.findById(id);
        return new ResponseEntity<>(stockItem, HttpStatus.OK);
    }

    @ApiOperation(value = "Update stock item")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<StockItem> updateStockItemById(@PathVariable("id") String id,
                                                         @RequestParam(value = "token") String token,
                                                         @Valid @RequestBody StockItem stockItem) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.UPDATE_ITEMS);
        getStockItemById(id);
        if (stockItem.getId() == null || !stockItem.getId().equals(id)) {
            throw new Exception("StockItem id does not match target id");
        }
        StockItem newStockItem = stockItemsDao.save(stockItem);
        StockItemLog stockItemLog = new StockItemLog(tokenService.tokenValue(token), newStockItem, new Date(), LogAction.UPDATE);
        stockItemLogService.log(stockItemLog);
        return new ResponseEntity<>(newStockItem, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete stock item by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteStockItemById(@PathVariable("id") String id,
                                                      @RequestParam(value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.UPDATE_ITEMS);
        StockItem stockItem = getStockItemById(id);
        stockItemsDao.delete(stockItem);
        StockItemLog stockItemLog = new StockItemLog(tokenService.tokenValue(token), stockItem, new Date(), LogAction.DELETE);
        stockItemLogService.log(stockItemLog);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }

    //internal helpers
    private StockItem getStockItemById(String id) throws Exception {
        StockItem stockitem = stockItemsDao.findById(id);
        if (stockitem == null) {
            throw new Exception("StockItem with id does not exist");
        }
        return stockitem;
    }

}
