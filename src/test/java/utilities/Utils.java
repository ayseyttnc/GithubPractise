package utilities;

import pojo.USER;

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
