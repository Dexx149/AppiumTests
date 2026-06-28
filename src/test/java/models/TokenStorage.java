package models;


public class TokenStorage {

    private static TokenStorage instance;

    private static String accessToken;

    private TokenStorage() {
        this.accessToken = null;
    }

    public static TokenStorage getInstance() {
        if (instance == null) {
            instance = new TokenStorage();
        }
        return instance;
    }

    public void setToken(String token) {
        this.accessToken = token;
    }

    public static String getToken() {
        return accessToken;
    }

    public static boolean hasToken() {
        return accessToken != null && !accessToken.isEmpty();
    }

}