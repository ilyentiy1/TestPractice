package api.tests.User;

import api.BaseRestAssuredTest;
import api.dto.UserDTO;
import api.utils.ConfigProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.UUID;

import static api.specifications.Specifications.requestSpec;
import static api.utils.ConfigProvider.ENDPOINT_USER;
import static io.restassured.RestAssured.given;


public abstract class UserBaseTest extends BaseRestAssuredTest {

    protected ThreadLocal<UserDTO> tmpUser = new ThreadLocal<>();


    @BeforeEach
    public void prepareUser() {
        tmpUser.set(given()
                .spec(requestSpec())
                .body(new UserDTO("user_" + UUID.randomUUID().toString().substring(0, 8)))
                .queryParam("fields", "id,login")
                .when()
                .post(ConfigProvider.ENDPOINT_USER)
                .then()
                .statusCode(200)
                .extract().body().as(UserDTO.class)
        );
    }

    @AfterEach
    public void cleanUp() {
        if (tmpUser != null) {
            given()
                    .spec(requestSpec())
                    .pathParam("user_id", tmpUser.get().getId())
                    .when()
                    .delete(ENDPOINT_USER + "/{user_id}");
            tmpUser.remove();
        }
    }
}
