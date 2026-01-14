package api.tests.User;

import api.dto.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static api.specifications.Specifications.requestSpec;
import static api.utils.ConfigProvider.ENDPOINT_USER;
import static api.utils.Printer.printUserTestInfo;
import static io.restassured.RestAssured.given;

public class UpdateUserTest extends UserBaseTest {

    @ParameterizedTest
    @DisplayName("Параметризованное тестирование обновление данных пользователя")
    @CsvFileSource(resources = "/api/valueSource/UserParameters.csv", numLinesToSkip = 1)
    @Execution(ExecutionMode.CONCURRENT)
    public void updateUserParameterizedTest(String login, String name, int expectedStatusCode, boolean isPositive) {

        printUserTestInfo(login, name, expectedStatusCode, isPositive);

        given()
                .spec(requestSpec())
                .pathParam("user_id", tmpUser.getId())
                .body(new UserDTO(login))
                .queryParam("fields", "id,login")
                .when()
                .post(ENDPOINT_USER + "/{user_id}")
                .then()
                .log().all()
                .statusCode(expectedStatusCode);
    }
}
