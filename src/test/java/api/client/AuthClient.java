package api.client;

import api.specs.response.AuthSpecs;
import io.restassured.response.Response;

import static api.specs.request.RequestSpecs.authRequestSpec;
import static io.restassured.RestAssured.given;

public class AuthClient {
    private Response response;

    public AuthClient executeAuth(String token) {
        response =  given()
                .spec(authRequestSpec(token))
                .when()
                .get();
        return this;
    }

    public AuthSpecs passes() {
        return new AuthSpecs(response);
    }
}
