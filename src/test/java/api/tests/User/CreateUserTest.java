package api.tests.User;

import api.BaseRestAssuredTest;
import api.dto.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static api.specifications.Specifications.createUserResponseSpec;
import static api.specifications.Specifications.requestSpec;
import static api.utils.ConfigProvider.ENDPOINT_USER;
import static io.restassured.RestAssured.given;

@DisplayName("Параметризованное тестирование создание пользователя")
public class CreateUserTest extends BaseRestAssuredTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/UserParameters.csv", numLinesToSkip = 1)
    public void createUserParameterizedTest(String login, String name, int expectedStatusCode, boolean isPositive) {

        given()
                .spec(requestSpec())
                .body(new UserDTO(login, name))
                .queryParam("fields", "id,login")
                .when()
                .post(ENDPOINT_USER)
                .then()
                .spec(createUserResponseSpec(expectedStatusCode));
    }
}
