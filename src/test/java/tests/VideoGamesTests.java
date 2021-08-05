package tests;

import constants.Endpoints;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static data.TestData.getRandomUserData;
import static headers.HeadersTest.*;
import static io.restassured.RestAssured.given;

public class VideoGamesTests extends BaseTest {

    @Test
    public void getAllVideoGamesTest() {

        Header h1 = new Header("Accept", "application/json");
        Header h2 = new Header("Accept-Language", "en-US");
        Header h3 = new Header("User-Agent", "Mozilla/5.0");
        Header h4 = new Header("Content-Type", "application/json");
        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        list.add(h2);
        list.add(h3);
        list.add(h4);
        Headers header = new Headers(list);

        given().log().all()
                .headers(header)
                .when().get(Endpoints.VIDEO_GAMES)
                .then().statusCode(200).log().all();


    }

    @Test
    public void getAllVideoGamesTest2() {

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Accept", "application/json");
        requestHeaders.put("Accept-Language", "en-US");
        requestHeaders.put("User-Agent", "Mozilla/5.0");
        requestHeaders.put("Content-Type", "application/json");

        given().log().all()
                .headers(requestHeaders)
                .when().get(Endpoints.VIDEO_GAMES)
                .then().statusCode(200).log().all();


    }


    @Test
    public void createNewVideoGameTest() {
        given().log().all()
                .headers(getDefoultHeaders())
                .body(getRandomUserData())
                .when()
                .post(Endpoints.VIDEO_GAMES)
                .then().statusCode(200).log().all();


    }
}
