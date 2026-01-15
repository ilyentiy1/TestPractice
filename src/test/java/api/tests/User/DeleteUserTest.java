package api.tests.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.specifications.Specifications.requestSpec;
import static api.specifications.Specifications.responseSpec;
import static api.utils.ConfigProvider.ENDPOINT_USER;
import static io.restassured.RestAssured.given;

@DisplayName("Удалить пользователя")
public class DeleteUserTest extends UserBaseTest {

    @Test
    public void deleteUserTest() {

        given()
                .spec(requestSpec())
                .pathParam("user_id", tmpUser.get().getId())
                .when()
                .delete(ENDPOINT_USER + "/{user_id}")
                .then()
                .spec(responseSpec(200));
    }
}
