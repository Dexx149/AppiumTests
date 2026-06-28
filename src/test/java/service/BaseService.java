package service;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.TokenStorage;

public class BaseService {

    private static final String BASE_URL = "https://students.netoservices.ru/qamid-diplom-backend";
    private RequestSpecification requestSpecification;

    public BaseService(){

            requestSpecification = given().contentType(ContentType.JSON).baseUri(BASE_URL);

        if (TokenStorage.hasToken()) {
            requestSpecification.header("Authorization",TokenStorage.getToken());
        }
    }

    protected Response getRequest(String endpoint) {
        return requestSpecification.when().get(endpoint);
    }

    protected Response postRequest(String endpoint, Object payload) {
        return requestSpecification.body(payload).when().post(endpoint);
    }

    protected Response putRequest(String endpoint, Object payload) {
        return requestSpecification.body(payload).when().put(endpoint);
    }

    protected Response deleteRequest(String endpoint) {
        return requestSpecification.when().delete(endpoint);
    }
}
