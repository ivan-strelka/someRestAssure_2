package data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TestData {

    private final static Faker faker = new Faker();

    public static Map getRandomUserData() {
        HashMap dataMap;
        dataMap = new HashMap();
        dataMap.put("id", faker.random().nextInt(100));
        dataMap.put("name", faker.name().firstName());
        dataMap.put("releaseDate", getTime());
        dataMap.put("reviewScore", faker.random().nextInt(180));
        dataMap.put("category", faker.company().name());
        dataMap.put("rating", faker.company().profession());

        return dataMap;
    }

    public static String getTime() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(Calendar.getInstance().getTime());
        return timeStamp;
    }
}
