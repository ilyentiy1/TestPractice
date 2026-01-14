package api.tests.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static api.specifications.Specifications.requestSpec;
import static api.utils.ConfigProvider.ENDPOINT_USER;
import static io.restassured.RestAssured.given;

public class GetUserTest extends UserBaseTest {

    @Test
    @DisplayName("Получить список пользователей")
    @Execution(ExecutionMode.CONCURRENT)
    public void getUserTest() {
        given()
                .spec(requestSpec())
                .queryParam("fields", "id,login,name")
                .when()
                .get(ENDPOINT_USER)
                .then()
                .log().all()
                .statusCode(200);
    }
}
