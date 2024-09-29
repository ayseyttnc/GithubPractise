package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MyResponse {
    public    Response myResponse;
    private   RequestSpecification requestSpecification;

    public MyResponse() {
        RestAssured.basePath = "/api";
        RestAssured.baseURI = "http://localhost:3000";

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType(ContentType.JSON);

        requestSpecification = requestSpecBuilder.build();
    }

    public RequestSpecification getMyRequestSpecification() {
        return requestSpecification;
    }
}
