package api.tests.Issue;

import api.BaseRestAssuredTest;
import api.dto.IssueDTO;
import api.dto.ProjectDTO;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static api.specifications.Specifications.requestSpec;
import static api.utils.ConfigProvider.ENDPOINT_ISSUE;
import static api.utils.Printer.printIssueTestInfo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateIssueTest extends BaseRestAssuredTest {

    @ParameterizedTest
    @DisplayName("Создание задачи")
    @CsvFileSource(resources = "/api/valueSource/IssueParameters.csv", numLinesToSkip = 1)
    public void createIssueTest(String summary, String description,
                                int expectedStatusCode, boolean isPositive) {

        printIssueTestInfo(summary, description, expectedStatusCode, isPositive);

        Response response = given()
                .spec(requestSpec())
                .body(new IssueDTO(summary, description,
                        new ProjectDTO("0-1")))
                .queryParam("fields", "summary,description,idReadable,project(id,name)")
                .when()
                .post(ENDPOINT_ISSUE);

        response.then()
                .statusCode(expectedStatusCode)
                .log().all();

        if(response.getStatusCode() == 200) {
            response.then()
                    .body(matchesJsonSchemaInClasspath("api/jsonSchema/issueResponseSchema.json"));
        }
    }
}
