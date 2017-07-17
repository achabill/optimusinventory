package optimusinventory.api.exception;

/**
 * Created by Acha Bill on 7/17/2017.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameOrPasswordNotFoundException extends RuntimeException {
    public UsernameOrPasswordNotFoundException(String message) {
        super(message);
    }
}
