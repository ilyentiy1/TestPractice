package api.tests;

import api.BaseRestAssuredTest;
import api.utils.ConfigProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static api.specifications.Specifications.responseSpec;
import static io.restassured.RestAssured.given;

@DisplayName("Проверка авторизации по постоянному токену")
public class AuthTest extends BaseRestAssuredTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/AuthParameters.csv", numLinesToSkip = 1)
    public void authTest(String token, int expectedStatusCode) {

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(ConfigProvider.ENDPOINT_AUTH)
                .then()
                .spec(responseSpec(expectedStatusCode));
    }
}
