package api.specs.response;

import api.client.ProjectClient;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;

@AllArgsConstructor
public class ProjectSpecs {
    private final Response response;
    private final ProjectClient projectClient;

    public ProjectSpecs projectCreateCheck(int status) {
        response
                .then()
                .log().all()
                .statusCode(status);
        return this;
    }

    public ProjectSpecs projectReadCheck(String expectedName) {
        response
                .then()
                .log().all()
                .statusCode(200);

        Assertions.assertEquals(expectedName,
                response.then().extract().body().jsonPath().getString("name"));
        return this;
    }

    public void projectDeleteCheck() {
        response
                .then()
                .log().all()
                .statusCode(200);
    }

    public ProjectClient then() {
        return this.projectClient;
    }
}
