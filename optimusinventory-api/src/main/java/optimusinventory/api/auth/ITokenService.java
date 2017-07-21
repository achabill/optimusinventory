package optimusinventory.api.auth;

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