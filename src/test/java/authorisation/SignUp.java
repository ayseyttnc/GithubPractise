package authorisation;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class SignUp {
    @Test
    public void testSignUpPositive() {
        Faker faker = new Faker();
        String userName = faker.name().username();
        String emailAddress = faker.internet().emailAddress();
        String password = faker.internet().password(8, 10, true, true, true);
        Response response;
        RestAssured.basePath="/api";
        RestAssured.baseURI="http://localhost:3000";
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
}
