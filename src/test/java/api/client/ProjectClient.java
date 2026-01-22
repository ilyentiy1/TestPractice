package api.client;

import api.dto.LeaderDTO;
import api.dto.ProjectDTO;
import api.specs.response.ProjectSpecs;
import io.restassured.response.Response;
import lombok.Getter;

import static api.specs.request.RequestSpecs.projectRequestSpec;
import static io.restassured.RestAssured.given;

public class ProjectClient {
    private Response response;
    @Getter
    private String currentId;

    public ProjectClient createProject(String name, String shortName, String description) {
        response = given()
                .spec(projectRequestSpec())
                .body(new ProjectDTO(name, shortName, description, new LeaderDTO("2-1")))
                .queryParam("fields", "id,name,shortName")
                .when()
                .post();
        if(response.statusCode() == 200) {
            currentId = response.jsonPath().getString("id");
        }
        return this;
    }

    public ProjectClient readProjects() {
        response = given()
                .spec(projectRequestSpec())
                .queryParam("fields", "id, name")
                .when()
                .get();
        return this;
    }

    public ProjectClient readProjectById() {
        response = given()
                .spec(projectRequestSpec())
                .queryParam("fields", "id, name")
                .pathParam("project_id", currentId)
                .when()
                .get("/{project_id}");
        return this;
    }

    public ProjectClient deleteProject() {
        response = given()
                .spec(projectRequestSpec())
                .pathParam("project_id", currentId)
                .when()
                .delete("/{project_id}");
        return this;
    }

    public ProjectSpecs passes() {
        return new ProjectSpecs(response, this);
    }
}
