package optimusinventory.api.parsers;

import optimusinventory.api.models.StockItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IExcelFileService {
    List<StockItem> parse(MultipartFile file) throws IOException;
}
