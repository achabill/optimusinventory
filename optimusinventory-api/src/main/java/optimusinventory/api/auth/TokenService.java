package optimusinventory.api.auth;

import optimusinventory.api.models.User;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.util.UUID.randomUUID;

@Service("TokenService")
class TokenService implements ITokenService {

    private final static Map<String, User> tokens = new HashMap<>();
    private final Sha1Hex sha1 = new Sha1Hex();

    @Override
    public String digest(String data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return sha1.makeSHA1Hash(data);
    }

    @Override
    public String setToken(User value) {
        String token = randomUUID().toString().replaceAll("-", "");
        tokens.put(token, (User) value);
        return token;
    }

    @Override
    public String getToken(User value) {
        Iterator it = tokens.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            if (tokens.get(key).equals(value))
                return key;
        }
        return null;
    }

    @Override
    public boolean removeToken(String token) {
        return tokens.remove(token) != null;
    }

    @Override
    public User tokenValue(String token) {
        if (!tokens.containsKey(token))
            return null;
        return tokens.get(token);
    }

    @Override
    public void clearTokens() {
        tokens.clear();
    }
}
