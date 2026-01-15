package api.tests.Issue;

import api.BaseRestAssuredTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static api.specifications.Specifications.getIssueResponseSpec;
import static api.specifications.Specifications.requestSpec;
import static api.utils.ConfigProvider.ENDPOINT_ISSUE;
import static io.restassured.RestAssured.given;

public class GetIssueTest extends BaseRestAssuredTest {
    @Test
    @DisplayName("Получить список задач")
    public void getIssueTest() {
        given()
                .spec(requestSpec())
                .queryParam("fields", "summary,idReadable,description,project(id,name)")
                .when()
                .get(ENDPOINT_ISSUE)
                .then()
                .spec(getIssueResponseSpec());
    }
}
