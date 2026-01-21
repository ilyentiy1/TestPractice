package api.specs.response;

import api.client.IssueClient;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@AllArgsConstructor
public class IssueSpecs {
    private final Response response;
    private final IssueClient issueClient;

    public IssueSpecs issueCreateCheck(int status) {
        response
                .then()
                .log().all()
                .statusCode(status);

        if(response.getStatusCode() == 200) {
            response
                    .then()
                    .body(matchesJsonSchemaInClasspath("api/jsonSchema/issueResponseSchema.json"));
        }
        return this;
    }

    public IssueSpecs issueReadCheck() {
        response
                .then()
                .log().all()
                .statusCode(200);
        return this;
    }

    public void issueDeleteCheck() {
        response
                .then()
                .log().all()
                .statusCode(200);
    }

    public IssueClient then() {
        return this.issueClient;
    }
}
