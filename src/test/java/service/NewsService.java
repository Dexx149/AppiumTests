package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import net.datafaker.Faker;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.News;
import models.UserData;

public class NewsService extends BaseService{
    private static final String BASE_PATH="/news";

    public Response getNews(){
        return getRequest(BASE_PATH);
    }

    public List<News> getNewsVisibleOnControlPanel() {
        var response = getRequest(BASE_PATH + "?pages=1&publishDate=false");

        return response.then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("elements", News.class);
    }

    public Response createNews(News news){
        return postRequest(BASE_PATH, news);
    }

    public Response deleteNews(long newsID){
        return deleteRequest(BASE_PATH + "/" + newsID);
    }

    public Response updateNews(News payload){
        return putRequest(BASE_PATH, payload);
    }

}
