package api.client;

import api.dto.UserDTO;
import api.specs.response.UserSpecs;
import io.restassured.response.Response;
import lombok.Getter;

import java.util.UUID;

import static api.specs.request.UserBaseRequestSpec.userRequestSpec;
import static io.restassured.RestAssured.given;

public class UserClient {

    private Response response;
    @Getter
    private String currentId;

    public UserClient createUser(String login, String name) {
        response = given()
                        .spec(userRequestSpec())
                        .body(new UserDTO(login, name))
                        .queryParam("fields", "id,login")
                        .when()
                        .post();
        if(response.statusCode() == 200) {
            currentId = response.jsonPath().getString("id");
        }
        return this;
    }

    public UserClient createTmpUser() {
        response = given()
                .spec(userRequestSpec())
                .body(new UserDTO("user_" + UUID.randomUUID().toString().substring(0, 8)))
                .queryParam("fields", "id,login")
                .when()
                .post();
        currentId = response.jsonPath().getString("id");
        return this;
    }

    public Response readUsers() {
        return given()
                .spec(userRequestSpec())
                .queryParam("fields", "id,login,name")
                .when()
                .get();
    }

    public UserClient readUserById() {
        response = given()
                .spec(userRequestSpec())
                .queryParam("fields", "id,login,name")
                .pathParam("user_id", currentId)
                .when()
                .get( "/{user_id}");

        return this;
    }

    public UserClient updateUser(String login, String name) {
        response = given()
                        .spec(userRequestSpec())
                        .pathParam("user_id", currentId)
                        .body(new UserDTO(login, name))
                        .queryParam("fields", "id,login")
                        .when()
                        .post( "/{user_id}");
        return this;
    }

    public UserClient deleteUser() {
        response = given()
                .spec(userRequestSpec())
                .pathParam("user_id", currentId)
                .when()
                .delete( "/{user_id}");
        return this;
    }


    public UserSpecs passes() {
        return new UserSpecs(response, this);
    }

}
