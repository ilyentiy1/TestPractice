package api.tests.User;

import api.dto.UserDTO;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static api.specifications.Specifications.requestSpec;
import static api.utils.ConfigProvider.ENDPOINT_USER;
import static api.utils.Printer.printUserTestInfo;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateUserTest extends UserBaseTest {

    @ParameterizedTest
    @DisplayName("Параметризованное тестирование создание пользователя")
    @CsvFileSource(resources = "/api/valueSource/UserParameters.csv", numLinesToSkip = 1)
    @Execution(ExecutionMode.CONCURRENT)
    public void createUserParameterizedTest(String login, String name, int expectedStatusCode, boolean isPositive) {

        printUserTestInfo(login, name, expectedStatusCode, isPositive);

        Response response = given()
                .spec(requestSpec())
                .body(new UserDTO(login, name))
                .queryParam("fields", "id,login")
                .when()
                .post(ENDPOINT_USER);

        response.then()
                .statusCode(expectedStatusCode)
                .log().all();

        if (response.getStatusCode() == 200) {
            response.then()
                    .body(matchesJsonSchemaInClasspath("api/jsonSchema/userResponseSchema.json"));
        }

    }
}
