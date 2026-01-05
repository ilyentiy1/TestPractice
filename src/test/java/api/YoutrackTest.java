package api;

import api.dto.UserDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static api.Specifications.reqSpec;

@DisplayName("API-Тестирование Youtrack")
public class YoutrackTest {

    private UserDTO tmpUser;

    @BeforeEach
    public void prepareUser() {
        tmpUser = RestAssured.given()
                .spec(reqSpec())
                .body(new UserDTO("tmpUser" + System.currentTimeMillis()))
                .queryParam("fields", "id,login")
                .when()
                .post("/hub/api/rest/users")
                .then()
                .statusCode(200)
                .extract().body().as(UserDTO.class);
    }

    @Test
    @DisplayName("Получить список пользователей")
    @Execution(ExecutionMode.CONCURRENT)
    public void getUserTest() {
        RestAssured.given()
                .spec(reqSpec())
                .queryParam("fields", "id,login,name")
                .when()
                .get("/hub/api/rest/users")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @DisplayName("Создать пользователя")
    @Execution(ExecutionMode.CONCURRENT)
    public void createUserTest(){
        RestAssured.given()
                .spec(reqSpec())
                .body(new UserDTO("John.Gee"))
                .queryParam("fields", "id,login")
                .when()
                .post("/hub/api/rest/users")
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("jsonSchema/userResponseSchema.json"));
    }

    @ParameterizedTest
    @DisplayName("Параметризованное тестирование создание пользователя")
    @CsvFileSource(resources = "/valueSource/UserParameters.csv", numLinesToSkip = 1)
    public void createUserParameterizedTest(String login, String name, int expectedStatusCode, boolean isPositive) {
        System.out.println("Login: " + login +
                "\nName: " + name +
                "\nExpected Status code: " + expectedStatusCode +
                "\nIs positive?: " + isPositive);

        Response response = RestAssured.given()
                .spec(reqSpec())
                .body(new UserDTO(login, name))
                .queryParam("fields", "id,login")
                .when()
                .post("/hub/api/rest/users");

        response.then().statusCode(expectedStatusCode);

        if(response.getStatusCode() == 200) {
            response.then()
                    .log().all()
                    .body(matchesJsonSchemaInClasspath("jsonSchema/userResponseSchema.json"));
        }

    }

    @ParameterizedTest
    @DisplayName("Параметризованное тестирование обновление данных пользователя")
    @CsvFileSource(resources = "/valueSource/UserParameters.csv", numLinesToSkip = 1)
    public void updateUserParameterizedTest(String login, String name, int expectedStatusCode, boolean isPositive) {
        System.out.println("Login: " + login +
                "\nName: " + name +
                "\nExpected Status code: " + expectedStatusCode +
                "\nIs positive?: " + isPositive);

        RestAssured.given()
                .spec(reqSpec())
                .pathParam("user_id", tmpUser.getId())
                .body(new UserDTO(login))
                .queryParam("fields", "id,login")
                .when()
                .post("/hub/api/rest/users/{user_id}")
                .then()
                .log().all()
                .statusCode(expectedStatusCode);
    }

    @Test
    @DisplayName("Обновить пользователя")
    @Execution(ExecutionMode.CONCURRENT)
    public void updateUserTest(){
        RestAssured.given()
                .spec(reqSpec())
                .pathParam("user_id", tmpUser.getId())
                .queryParam("fields", "id,login")
                .body(new UserDTO("John.Doe", "John Doe"))
                .when()
                .post("/hub/api/rest/users/{user_id}")
                .then()
                .log().all()
                .statusCode(200);
    }


    @Test
    @DisplayName("Удалить пользователя")
    @Execution(ExecutionMode.CONCURRENT)
    public void deleteUserTest() {
        RestAssured.given()
                .spec(reqSpec())
                .pathParam("user_id", tmpUser.getId())
                .log().uri()
                .when()
                .delete("/hub/api/rest/users/{user_id}")
                .then()
                .log().all()
                .statusCode(200);
    }


    @AfterEach
    public void cleanUp() {
        RestAssured.given()
                .spec(reqSpec())
                .pathParam("user_id", tmpUser.getId())
                .when()
                .delete("hub/api/rest/users/{user_id}");
    }
//
//    @Test
//    @DisplayName("Получить список задач")
//    public void getIssueTest() {
//        Response response = given()
//                .spec(reqSpec())
//                .queryParam("fields", "summary, idReadable, description")
//                .when()
//                .get("/api/issues");
//        System.out.println("Status Code: " + response.getStatusCode());
//        System.out.println("Response: " + response.getBody().asString());
//    }
//
//    @Test
//    @DisplayName("Создать задачу")
//    public void createIssueTest() {
//
//    }
//
//    @Test
//    @DisplayName("Обновить задачу")
//    public void updateIssueTest() {
//
//    }
//
//    @Test
//    @DisplayName("Удалить задачу")
//    public void deleteIssueTest() {
//
//    }
}
