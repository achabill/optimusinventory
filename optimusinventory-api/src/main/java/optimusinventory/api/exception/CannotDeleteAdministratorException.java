package optimusinventory.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Acha Bill on 7/17/2017.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotDeleteAdministratorException extends RuntimeException {
    public CannotDeleteAdministratorException(String message) {
        super(message);
    }
}