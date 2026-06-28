package service;

import io.restassured.response.Response;
import models.TokenStorage;
import models.UserData;

public class AuthService extends BaseService{
    private static final String BASE_PATH="/authentication/login";

    public void login(UserData user) {
        String token = postRequest(BASE_PATH, user)
                .then()
                .statusCode(200)
                .extract()
                .path("accessToken");

        TokenStorage.getInstance().setToken(token);
    }


}
