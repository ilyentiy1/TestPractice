package api.tests.User;

import api.BaseRestAssuredTest;
import api.dto.UserDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static api.specifications.Specifications.requestSpec;
import static api.utils.ConfigProvider.ENDPOINT_USER;
import static io.restassured.RestAssured.given;

public class UserBaseTest extends BaseRestAssuredTest {

    protected UserDTO tmpUser;

    @BeforeEach
    public void prepareUser() {
        tmpUser = given()
                .spec(requestSpec())
                .body(new UserDTO("tmpUser" + System.currentTimeMillis()))
                .queryParam("fields", "id,login")
                .when()
                .post(ENDPOINT_USER)
                .then()
                .statusCode(200)
                .extract().body().as(UserDTO.class);
    }

    @AfterEach
    public void cleanUp() {
        given()
                .spec(requestSpec())
                .pathParam("user_id", tmpUser.getId())
                .when()
                .delete(ENDPOINT_USER + "/{user_id}");
    }
}
