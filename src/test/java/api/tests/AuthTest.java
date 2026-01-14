package api.tests;

import api.BaseRestAssuredTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;

public class AuthTest extends BaseRestAssuredTest {

    @ParameterizedTest
    @DisplayName("Проверка авторизации по постоянному токену")
    @CsvFileSource(resources = "/api/valueSource/AuthParameters.csv", numLinesToSkip = 1)
    @Execution(ExecutionMode.CONCURRENT)
    public void authTest(String token, int expectedStatusCode) {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:8080/api/users")
                .then()
                .log().ifValidationFails()
                .statusCode(expectedStatusCode);
    }
}
