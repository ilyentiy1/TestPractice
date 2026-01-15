package api.tests;

import api.BaseRestAssuredTest;
import api.dto.LeaderDTO;
import api.dto.ProjectDTO;
import api.utils.ConfigProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static api.specifications.Specifications.requestSpec;
import static api.specifications.Specifications.responseSpec;
import static api.utils.Printer.printProjectTestInfo;
import static io.restassured.RestAssured.given;

@DisplayName("Создание проекта")
public class CreateProjectTest extends BaseRestAssuredTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/ProjectParameters.csv", numLinesToSkip = 1)
    public void createProjectTest(String name, String shortName,
                                  String description, int expectedStatusCode, boolean isPositive) {



        given()
                .spec(requestSpec())
                .body(new ProjectDTO(name, shortName, description, new LeaderDTO("2-1")))
                .queryParam("fields", "id,name,shortName")
                .when()
                .post(ConfigProvider.ENDPOINT_PROJECT)
                .then()
                .spec(responseSpec(expectedStatusCode));


    }
}
