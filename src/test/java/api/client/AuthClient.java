package api.client;

import api.specs.response.AuthSpecs;
import api.utils.ConfigProvider;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient {
    private Response response;

    public AuthClient executeAuth(String token) {
        response =  given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(ConfigProvider.ENDPOINT_AUTH);
        return this;
    }

    public AuthSpecs passes() {
        return new AuthSpecs(response);
    }
}
