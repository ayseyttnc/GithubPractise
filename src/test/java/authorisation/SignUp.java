package authorisation;

import com.github.javafaker.Faker;
import pojo.USER;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.MyFaker;
import utilities.MyResponse;
import utilities.ROLE;
import utilities.Utils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SignUp {
    @Test
    public void testSignUpPositive() {
        Faker faker = new Faker();
        String userName = faker.name().username();
        String emailAddress = faker.internet().emailAddress();
        String password = faker.internet().password(8, 10, true, true, true);
        Response response;
        RestAssured.basePath = "/api";
        RestAssured.baseURI = "http://localhost:3000";
        response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"username\":\"" + userName + "\",\n" +
                        "    \"email\":\"" + emailAddress + "\",\n" +
                        "    \"password\":\"" + password + "\",\n" +
                        "    \"firstName\":\"omer\",\n" +
                        "    \"lastName\":\"ali\",\n" +
                        "    \"role\":\"teacher\"\n" +
                        "}")
                .when()
                .post("/signup");

        JsonPath jsonPath = response.jsonPath();

        boolean aBoolean = jsonPath.getBoolean("data.user.approved");
        boolean status = jsonPath.getBoolean("status");
        int id = jsonPath.getInt("data.user.id");
        String responseUserName = jsonPath.getString("data.user.username");
        String responsePassword = jsonPath.getString("data.user.password");
        int statusCode = response.statusCode();

        Assert.assertFalse(aBoolean);
        Assert.assertTrue(status);
        Assert.assertEquals(userName, responseUserName);
        Assert.assertNotEquals(password, responsePassword);
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void testSignUpNegative() {
        MyFaker myFaker = new MyFaker();
        MyResponse response = new MyResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("username", myFaker.getUsername());
        map.put("email", myFaker.getEmail());
        map.put("password", myFaker.getPassword());
        map.put("firstName", myFaker.getFirstName());
        map.put("lastName", myFaker.getLastname());
        map.put("role", "teacher");

        response.myResponse = given()
                .spec(response.getMyRequestSpecification())

                .body(map)
                .when()
                .post("/signup");

        JsonPath jsonPath = response.myResponse.jsonPath();

        boolean aBoolean = jsonPath.getBoolean("data.user.approved");
        boolean status = jsonPath.getBoolean("status");
        int id = jsonPath.getInt("data.user.id");
        String responseUserName = jsonPath.getString("data.user.username");
        String responsePassword = jsonPath.getString("data.user.password");
        int statusCode = response.myResponse.statusCode();

        Assert.assertFalse(aBoolean);
        Assert.assertTrue(status);
        Assert.assertEquals(myFaker.getUsername(), responseUserName);
        Assert.assertNotEquals(myFaker.getPassword(), responsePassword);
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void testSignUpNegative2() {
        MyResponse response = new MyResponse();
        USER user = new USER(ROLE.TEACHER);

        response.myResponse = given()
                .spec(response.getMyRequestSpecification())
                .body(user)
                .when()
                .post("/signup");

        JsonPath jsonPath = response.myResponse.jsonPath();

        boolean aBoolean = jsonPath.getBoolean("data.user.approved");
        boolean status = jsonPath.getBoolean("status");
        int id = jsonPath.getInt("data.user.id");
        String responseUserName = jsonPath.getString("data.user.username");
        String responsePassword = jsonPath.getString("data.user.password");
        int statusCode = response.myResponse.statusCode();

        Assert.assertFalse(aBoolean);
        Assert.assertTrue(status);
        Assert.assertEquals(user.username(), responseUserName);
        Assert.assertNotEquals(user.password(), responsePassword);
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void testSignUpNegative3() {
        USER user = new USER(ROLE.TEACHER);

        MyResponse response = Utils.createUser(user);

        JsonPath jsonPath = response.myResponse.jsonPath();

        boolean aBoolean = jsonPath.getBoolean("data.user.approved");
        boolean status = jsonPath.getBoolean("status");
        int id = jsonPath.getInt("data.user.id");
        String responseUserName = jsonPath.getString("data.user.username");
        String responsePassword = jsonPath.getString("data.user.password");
        int statusCode = response.myResponse.statusCode();

        Assert.assertFalse(aBoolean);
        Assert.assertTrue(status);
        Assert.assertEquals(user.username(), responseUserName);
        Assert.assertNotEquals(user.password(), responsePassword);
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void testName() {
        USER user=new USER(ROLE.STUDENT);

        MyResponse user1 = Utils.createUser(user);
        user1.myResponse.prettyPrint();

        MyResponse user2 = Utils.createUser(user);
        user2.myResponse.prettyPrint();

    }
}
