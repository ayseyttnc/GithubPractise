package utilities;

import enums.USER;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Utils {
    static MyResponse response = new MyResponse();

    public static MyResponse createUser(USER user) {
        response.myResponse = given()
                .spec(response.getMyRequestSpecification())
                .body(user)
                .when()
                .post("/signup");
        return response;
    }
}
