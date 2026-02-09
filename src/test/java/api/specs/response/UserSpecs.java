package api.specs.response;

import api.client.UserClient;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@AllArgsConstructor
public class UserSpecs {
    private final Response response;
    private final UserClient userClient;

    public UserSpecs userCreationCheck(int status) {
        response
                .then()
                .log().all()
                .statusCode(status);

        if(response.getStatusCode() == 200) {
            response
                    .then()
                    .body(matchesJsonSchemaInClasspath("api/jsonSchema/userResponseSchema.json"));
        }
        return this;
    }

    public UserSpecs userReadCheck() {
        response
                .then()
                .log().all()
                .statusCode(200);

        return this;
    }

    public UserSpecs userUpdateCheck(int statusCode) {
        response
                .then()
                .log().all()
                .statusCode(statusCode);
        return this;
    }

    public void userDeleteCheck() {
        response
                .then()
                .log().all()
                .statusCode(200);
    }


    public UserClient then() {
        return this.userClient;
    }
}
