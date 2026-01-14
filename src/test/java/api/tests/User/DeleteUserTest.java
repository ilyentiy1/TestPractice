package api.tests.User;

import api.BaseRestAssuredTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static api.specifications.Specifications.requestSpec;
import static api.utils.ConfigProvider.ENDPOINT_USER;
import static io.restassured.RestAssured.given;

public class DeleteUserTest extends UserBaseTest {

    @Test
    @DisplayName("Удалить пользователя")
    @Execution(ExecutionMode.CONCURRENT)
    public void deleteUserTest() {
        given()
                .spec(requestSpec())
                .pathParam("user_id", tmpUser.getId())
                .when()
                .delete(ENDPOINT_USER + "/{user_id}")
                .then()
                .log().all()
                .statusCode(200);
    }
}
