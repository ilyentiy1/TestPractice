package api.specs.response;

import api.client.AuthClient;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthSpecs {
    private final Response response;

    public void authCheck(int status) {
        response
                .then()
                .log().all()
                .statusCode(status);
    }

}
