package optimusinventory.api.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import optimusinventory.api.dao.ISalesDao;
import optimusinventory.api.helpers.IHelpers;
import optimusinventory.api.models.Privilege;
import optimusinventory.api.models.Sale;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Api(value = "Sales", description = "Sales Management")
@RequestMapping(value = "/api/sales")
public class SaleController {

    private ISalesDao salesDao;
    private IHelpers helpers;

    public SaleController(ISalesDao salesDao, IHelpers helpers) {
        this.salesDao = salesDao;
        this.helpers = helpers;
    }

    @ApiOperation(value = "Gets all sales")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Sale>> getAllSales(@RequestParam(value = "token") String token) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_SALES);
        return new ResponseEntity<>(salesDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get sale by id")
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable("id") String id,
                                            @RequestParam (value = "token") String token) throws Exception {
        helpers.validateRole(helpers.validateToken(token), Privilege.READ_SALES);
        return new ResponseEntity<>(getSaleById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Update sale by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Sale> updateSaleById(@PathVariable("id") String id,
                                               @Valid @RequestBody Sale sale,
                                               @RequestParam (value = "token") String token) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.UPDATE_SALES);
        if(sale.getId() != null || !sale.getId().equals(id)){
            throw new Exception("Sale id does not match target id");
        }
        Sale newSale = salesDao.save(sale);
        return new ResponseEntity<>(newSale, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete user by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUserById(@PathVariable("id") String id,
                                                 @RequestParam(value = "token") String token) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.DELETE_SALES);
        salesDao.delete(getSaleById(id));
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Create new sale")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Sale> add(@RequestParam(value = "token") String token,
                                    @Valid @RequestBody Sale sale) throws Exception{
        helpers.validateRole(helpers.validateToken(token), Privilege.CREATE_SALES);
        Sale newSale = salesDao.save(sale);
        return new ResponseEntity<>(newSale, HttpStatus.CREATED);
    }

    //Helpers
    private Sale getSaleById(String id) throws Exception {
        Sale sale = salesDao.findById(id);
        if(sale == null)
            throw new Exception("Sale with id not found");
        return sale;
    }

}
