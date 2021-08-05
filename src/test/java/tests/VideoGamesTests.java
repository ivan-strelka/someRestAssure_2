package tests;

import constants.Endpoints;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static data.TestData.getRandomUserData;
import static headers.HeadersTest.getDefoultHeaders;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class VideoGamesTests extends BaseTest {

    @Test(priority = 3)
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

    @Test(priority = 2)
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


    @Test(priority = 1)
    public void createNewVideoGameTest() {

        Map map = getRandomUserData();

        Response response = given().log().all()
                .headers(getDefoultHeaders())
                .body(map)
                .when()
                .post(Endpoints.VIDEO_GAMES)
                .then().statusCode(200).log().all()
                .extract().response();

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);

        String jsonString2 = response.jsonPath().get("status");
        Assert.assertEquals(jsonString2.contains("Record Added Successfully"), true);

        String idOfNewGame = String.format(Endpoints.VIDEO_GAMES_ID, map.get("id"));

        Response response2 = given().log().all()
                .headers(getDefoultHeaders())
                .when().get(idOfNewGame)
                .then().statusCode(200).log().all()
                .extract().response();

        String jsonStringResponseNew = response2.asString();
        String id = map.get("id").toString();
        String name = map.get("name").toString();
        String reviewScore = map.get("reviewScore").toString();
        String category = map.get("category").toString();
        String rating = map.get("rating").toString();

        Assert.assertEquals(jsonStringResponseNew.contains(id), true);
        Assert.assertEquals(jsonStringResponseNew.contains(name), true);
        Assert.assertEquals(jsonStringResponseNew.contains(reviewScore), true);
        Assert.assertEquals(jsonStringResponseNew.contains(category), true);
        Assert.assertEquals(jsonStringResponseNew.contains(rating), true);


    }


    @Test(priority = 4)
    public void getVideoGamesByIdTest() {

        Map map = getRandomUserData();

        String idOfNewGame = String.format(Endpoints.VIDEO_GAMES_ID, map.get("id"));
        System.out.println(" ID FROM MAP -> " + map.get("id").toString());
        System.out.println(" id Of New Game -> " + idOfNewGame);

        Response response = given().log().all()
                .headers(getDefoultHeaders())
                .body(map)
                .when()
                .post(Endpoints.VIDEO_GAMES)
                .then().statusCode(200).log().all()
                .extract().response();

        Integer id = Integer.parseInt(map.get("id").toString());

        System.out.println(" ************ START getVideoGamesByIdTest *****************");
        System.out.println(" ************ ID IS ->  ************ " + id);

        Response response2 = given().log().all()
                .headers(getDefoultHeaders())
                .when().get(idOfNewGame)
                .then().statusCode(200).log().all()
                .body("id", equalTo(id))
                .extract().response();

    }

    @Test(priority = 5)
    public void updateGames() {
        Map map = new HashMap();
        map.put("id", 1);
        map.put("name", "Resident Evil 3" + faker.name().name());
        map.put("releaseDate", "2005-10-01");
        map.put("reviewScore", 85);
        map.put("category", "Shooter");
        map.put("rating", "Universal");

        String idOfNewGame = String.format(Endpoints.VIDEO_GAMES_ID, 1);

        String name = map.get("name").toString();

        System.out.println("NAME IS -> ************************** " + name);

        given().headers(getDefoultHeaders())
                .log().all()
                .body(map)
                .when()
                .put(idOfNewGame)
                .then()
                .statusCode(200)
                .log().all()
                .body("id", equalTo(1))
                .body("name", equalTo(name));


    }

    @Test(priority = 7)
    public void deleteGamesTest() {

        String idOfNewGame = String.format(Endpoints.VIDEO_GAMES_ID, 1);

        Response response = given().headers(getDefoultHeaders())
                .log().all()
                .when()
                .delete(idOfNewGame)
                .then()
                .statusCode(200)
                .log().all().extract().response();

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);


    }


    @Test(priority = 6)
    public void createNewVideoGameTestDeleted() {

        Map map = getRandomUserData();

        Response response = given().log().all()
                .headers(getDefoultHeaders())
                .body(map)
                .when()
                .post(Endpoints.VIDEO_GAMES)
                .then().statusCode(200).log().all()
                .extract().response();

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("Record Added Successfully"), true);

        String jsonString2 = response.jsonPath().get("status");
        Assert.assertEquals(jsonString2.contains("Record Added Successfully"), true);

        String idOfNewGame = String.format(Endpoints.VIDEO_GAMES_ID, map.get("id"));

        Response response2 = given().log().all()
                .headers(getDefoultHeaders())
                .when().get(idOfNewGame)
                .then().statusCode(200).log().all()
                .extract().response();

        String jsonStringResponseNew = response2.asString();
        String id = map.get("id").toString();
        String name = map.get("name").toString();
        String reviewScore = map.get("reviewScore").toString();
        String category = map.get("category").toString();
        String rating = map.get("rating").toString();

        Assert.assertEquals(jsonStringResponseNew.contains(id), true);
        Assert.assertEquals(jsonStringResponseNew.contains(name), true);
        Assert.assertEquals(jsonStringResponseNew.contains(reviewScore), true);
        Assert.assertEquals(jsonStringResponseNew.contains(category), true);
        Assert.assertEquals(jsonStringResponseNew.contains(rating), true);


        Response response3 = given().headers(getDefoultHeaders())
                .log().all()
                .when()
                .delete(idOfNewGame)
                .then()
                .statusCode(200)
                .log().all().extract().response();


        String jsonString4 = response3.asString();
        Assert.assertEquals(jsonString4.contains("Record Deleted Successfully"), true);


    }

}
