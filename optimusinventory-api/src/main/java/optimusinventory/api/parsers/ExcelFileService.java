package optimusinventory.api.parsers;

import optimusinventory.api.models.StockItem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("ExcelFileService")
public class ExcelFileService implements IExcelFileService {
    @Override
    public List<StockItem> parse(MultipartFile excelFile) throws IOException {
        try {
            String contentType = excelFile.getContentType();
            if(contentType == null)
                throw new RuntimeException("Invalid content type");

            FileInputStream file = new FileInputStream(convertToFile(excelFile));

            //Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            List<StockItem> stockItems = new ArrayList<>();
            if(rowIterator.hasNext())
                rowIterator.next();

            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();


                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                StockItem stockItem = new StockItem();
                int count = 0;
                while(cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();
                    count++;
                    switch(count){
                        case 1:
                            continue;
                        case 2:
                            stockItem.setName(cell.getStringCellValue());
                            break;
                        case 3:
                            stockItem.setCategory(cell.getStringCellValue());
                            break;
                        case 4:
                            stockItem.setQuantity((int)cell.getNumericCellValue());
                            break;
                        case 5:
                            stockItem.setCostPrice((int)cell.getNumericCellValue());
                            break;
                        case 6:
                            stockItem.setSellingPrice((int)cell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }
                    if(count > 6)
                        break;
                }
                stockItems.add(stockItem);
            }
            return stockItems;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error uploading\n" + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error uploading\n" + e.getMessage());
        }
    }
    private File convertToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
