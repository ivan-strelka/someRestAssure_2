package tests;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    public Faker faker = new Faker();

    @BeforeClass
    public void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/app";
    }

}
