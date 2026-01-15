package api.tests.User;

import api.BaseRestAssuredTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.specifications.Specifications.requestSpec;
import static api.specifications.Specifications.responseSpec;
import static api.utils.ConfigProvider.ENDPOINT_USER;
import static io.restassured.RestAssured.given;

@DisplayName("Получить список пользователей")
public class GetUserTest extends BaseRestAssuredTest {

    @Test
    public void getUserTest() {

        given()
                .spec(requestSpec())
                .queryParam("fields", "id,login,name")
                .when()
                .get(ENDPOINT_USER)
                .then()
                .spec(responseSpec(200));
    }
}
