package api.client;

import api.dto.IssueDTO;
import api.dto.ProjectDTO;
import api.specs.response.IssueSpecs;
import io.restassured.response.Response;
import lombok.Getter;

import static api.specs.request.BaseRequestSpecification.requestSpec;
import static api.utils.ConfigProvider.ENDPOINT_ISSUE;
import static io.restassured.RestAssured.given;

public class IssueClient {

    private Response response;
    @Getter
    private String currentId;

    public IssueClient createIssue(String summary, String description) {
        response = given()
                .spec(requestSpec())
                .body(new IssueDTO(summary, description,
                        new ProjectDTO("0-1")))
                .queryParam("fields", "id,summary,description,idReadable,project(id,name)")
                .when()
                .post(ENDPOINT_ISSUE);
        if(response.statusCode() == 200) {
            currentId = response.jsonPath().getString("id");
        }
        return this;
    }

    public IssueClient readIssues() {
        response = given()
                .spec(requestSpec())
                .queryParam("fields", "summary,idReadable,description,project(id,name)")
                .when()
                .get(ENDPOINT_ISSUE);
        return this;
    }

    public IssueClient readIssueById() {
        response = given()
                .spec(requestSpec())
                .queryParam("fields", "summary,idReadable,description,project(id,name)")
                .pathParam("issue_id", currentId)
                .when()
                .get(ENDPOINT_ISSUE + "/{issue_id}");
        return this;
    }

    public IssueClient deleteIssue() {
        response = given()
                .spec(requestSpec())
                .pathParam("issue_id", currentId)
                .when()
                .delete(ENDPOINT_ISSUE + "/{issue_id}");
        return this;
    }

    public IssueSpecs passes() {
        return new IssueSpecs(response, this);
    }
}
