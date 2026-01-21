package api.specs.response;

import api.client.ProjectClient;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;

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

    public ProjectSpecs projectReadCheck() {
        response
                .then()
                .log().all()
                .statusCode(200);
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
