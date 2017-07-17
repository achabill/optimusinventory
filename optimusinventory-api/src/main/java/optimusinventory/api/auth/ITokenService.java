package optimusinventory.api.auth;

/**
 * Created by Acha Bill on 7/17/2017.
 */

import optimusinventory.api.models.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface ITokenService {
    public String digest(String data) throws UnsupportedEncodingException, NoSuchAlgorithmException;

    public String setToken(User value);

    public String getToken(User value);

    public boolean removeToken(String token);

    public User tokenValue(String token);

    public void clearTokens();
}