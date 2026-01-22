package api.client;

import api.dto.IssueDTO;
import api.dto.ProjectDTO;
import api.specs.response.IssueSpecs;
import io.restassured.response.Response;
import lombok.Getter;

import static api.specs.request.RequestSpecs.issueRequestSpec;
import static api.utils.ConfigProvider.ENDPOINT_ISSUE;
import static io.restassured.RestAssured.given;

public class IssueClient {

    private Response response;
    @Getter
    private String currentId;

    public IssueClient createIssue(String summary, String description) {
        response = given()
                .spec(issueRequestSpec())
                .body(new IssueDTO(summary, description,
                        new ProjectDTO("0-1")))
                .queryParam("fields", "id,summary,description,idReadable,project(id,name)")
                .when()
                .post();
        if(response.statusCode() == 200) {
            currentId = response.jsonPath().getString("id");
        }
        return this;
    }

    public IssueClient readIssues() {
        response = given()
                .spec(issueRequestSpec())
                .queryParam("fields", "summary,idReadable,description,project(id,name)")
                .when()
                .get();
        return this;
    }

    public IssueClient readIssueById() {
        response = given()
                .spec(issueRequestSpec())
                .queryParam("fields", "summary,idReadable,description,project(id,name)")
                .pathParam("issue_id", currentId)
                .when()
                .get( "{issue_id}");
        return this;
    }

    public IssueClient deleteIssue() {
        response = given()
                .spec(issueRequestSpec())
                .pathParam("issue_id", currentId)
                .when()
                .delete( "{issue_id}");
        return this;
    }

    public IssueSpecs passes() {
        return new IssueSpecs(response, this);
    }
}
