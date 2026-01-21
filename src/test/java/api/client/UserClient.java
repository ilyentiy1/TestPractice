package api.client;

import api.dto.UserDTO;
import api.specs.response.UserSpecs;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.UUID;

import static api.specs.request.BaseRequestSpecification.requestSpec;
import static api.utils.ConfigProvider.ENDPOINT_USER;
import static io.restassured.RestAssured.given;

public class UserClient {

    private Response response;
    @Getter
    private String currentId;

    public UserClient createUser(String login, String name) {
        response = given()
                        .spec(requestSpec())
                        .body(new UserDTO(login, name))
                        .queryParam("fields", "id,login")
                        .when()
                        .post(ENDPOINT_USER);
        if(response.statusCode() == 200) {
            currentId = response.jsonPath().getString("id");
        }
        return this;
    }

    public UserClient createTmpUser() {
        response = given()
                .spec(requestSpec())
                .body(new UserDTO("user_" + UUID.randomUUID().toString().substring(0, 8)))
                .queryParam("fields", "id,login")
                .when()
                .post(ENDPOINT_USER);
        currentId = response.jsonPath().getString("id");
        return this;
    }

    public UserClient readUsers() {
        response = given()
                        .spec(requestSpec())
                        .queryParam("fields", "id,login,name")
                        .when()
                        .get(ENDPOINT_USER);
        return this;
    }

    public UserClient readUserById() {
        response = given()
                .spec(requestSpec())
                .queryParam("fields", "id,login,name")
                .pathParam("user_id", currentId)
                .when()
                .get(ENDPOINT_USER + "/{user_id}");
        return this;
    }

    public UserClient updateUser(String login, String name) {
        response = given()
                        .spec(requestSpec())
                        .pathParam("user_id", currentId)
                        .body(new UserDTO(login, name))
                        .queryParam("fields", "id,login")
                        .when()
                        .post(ENDPOINT_USER + "/{user_id}");
        //currentId = response.jsonPath().getString("id");
        return this;
    }

    public UserClient deleteUser() {
        response = given()
                .spec(requestSpec())
                .pathParam("user_id", currentId)
                .when()
                .delete(ENDPOINT_USER + "/{user_id}");
        return this;
    }

    public UserSpecs passes() {
        return new UserSpecs(response, this);
    }

}
