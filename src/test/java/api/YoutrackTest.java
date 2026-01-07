package api;

import api.dto.IssueDTO;
import api.dto.LeaderDTO;
import api.dto.ProjectDTO;
import api.dto.UserDTO;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static api.UtilClass.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static api.Specifications.requestSpec;

@DisplayName("API-Тестирование Youtrack")
public class YoutrackTest {

    
    @Nested
    @DisplayName("Тестирование операций над пользователем")
    class UserTest {
        private UserDTO tmpUser;

        private final String usersEndpoint = "/hub/api/rest/users";

        @BeforeEach
        public void prepareUser() {
            tmpUser = given()
                    .spec(requestSpec())
                    .body(new UserDTO("tmpUser" + System.currentTimeMillis()))
                    .queryParam("fields", "id,login")
                    .when()
                    .post(usersEndpoint)
                    .then()
                    .statusCode(200)
                    .extract().body().as(UserDTO.class);
        }

        @Test
        @DisplayName("Получить список пользователей")
        @Execution(ExecutionMode.CONCURRENT)
        public void getUserTest() {
            given()
                    .spec(requestSpec())
                    .queryParam("fields", "id,login,name")
                    .when()
                    .get(usersEndpoint)
                    .then()
                    .log().all()
                    .statusCode(200);
        }

        @ParameterizedTest
        @DisplayName("Параметризованное тестирование создание пользователя")
        @CsvFileSource(resources = "/valueSource/UserParameters.csv", numLinesToSkip = 1)
        @Execution(ExecutionMode.CONCURRENT)
        public void createUserParameterizedTest(String login, String name, int expectedStatusCode, boolean isPositive) {

            printUserTestInfo(login, name, expectedStatusCode, isPositive);

            Response response = given()
                    .spec(requestSpec())
                    .body(new UserDTO(login, name))
                    .queryParam("fields", "id,login")
                    .when()
                    .post(usersEndpoint);

            response.then()
                    .statusCode(expectedStatusCode)
                    .log().all();

            if (response.getStatusCode() == 200) {
                response.then()
                        .body(matchesJsonSchemaInClasspath("jsonSchema/userResponseSchema.json"));
            }

        }

        @ParameterizedTest
        @DisplayName("Параметризованное тестирование обновление данных пользователя")
        @CsvFileSource(resources = "/valueSource/UserParameters.csv", numLinesToSkip = 1)
        @Execution(ExecutionMode.CONCURRENT)
        public void updateUserParameterizedTest(String login, String name, int expectedStatusCode, boolean isPositive) {

            printUserTestInfo(login, name, expectedStatusCode, isPositive);

            given()
                    .spec(requestSpec())
                    .pathParam("user_id", tmpUser.getId())
                    .body(new UserDTO(login))
                    .queryParam("fields", "id,login")
                    .when()
                    .post(usersEndpoint+ "/{user_id}")
                    .then()
                    .log().all()
                    .statusCode(expectedStatusCode);
        }

        @Test
        @DisplayName("Удалить пользователя")
        @Execution(ExecutionMode.CONCURRENT)
        public void deleteUserTest() {
            given()
                    .spec(requestSpec())
                    .pathParam("user_id", tmpUser.getId())
                    .when()
                    .delete(usersEndpoint+ "/{user_id}")
                    .then()
                    .log().all()
                    .statusCode(200);
        }

        @AfterEach
        public void cleanUp() {
            given()
                    .spec(requestSpec())
                    .pathParam("user_id", tmpUser.getId())
                    .when()
                    .delete(usersEndpoint+ "/{user_id}");
        }
    }


    @Test
    @DisplayName("Получить список задач")
    @Execution(ExecutionMode.CONCURRENT)
    public void getIssueTest() {
        given()
                .spec(requestSpec())
                .queryParam("fields", "summary,idReadable,description,project(id,name)")
                .when()
                .get("/api/issues")
                .then()
                .statusCode(200)
                .log().all();
    }



    @ParameterizedTest
    @DisplayName("Создание задачи")
    @CsvFileSource(resources = "/valueSource/IssueParameters.csv", numLinesToSkip = 1)
    @Execution(ExecutionMode.CONCURRENT)
    public void createIssueTest(String summary, String description,
                                int expectedStatusCode, boolean isPositive) {

        printIssueTestInfo(summary, description, expectedStatusCode, isPositive);

        Response response = given()
                .spec(requestSpec())
                .body(new IssueDTO(summary, description,
                        new ProjectDTO("0-1")))
                .queryParam("fields", "summary,description,idReadable,project(id,name)")
                .when()
                .post("/api/issues");

        response.then()
                .statusCode(expectedStatusCode)
                .log().all();

        if(response.getStatusCode() == 200) {
            response.then()
                    .body(matchesJsonSchemaInClasspath("jsonSchema/issueResponseSchema.json"));
        }
    }


    @ParameterizedTest
    @DisplayName("Создание проекта")
    @CsvFileSource(resources = "/valueSource/ProjectParameters.csv", numLinesToSkip = 1)
    @Execution(ExecutionMode.CONCURRENT)
    public void createProjectTest(String name, String shortName,
                                  String description, int expectedStatusCode, boolean isPositive) {

        printProjectTestInfo(name, shortName, description, expectedStatusCode, isPositive);

        Response response = given()
                .spec(requestSpec())
                .body(new ProjectDTO(name, shortName, description, new LeaderDTO("2-1")))
                .queryParam("fields", "id, name, shortName")
                .when()
                .post("/api/admin/projects");

        response.then()
                .log().all()
                .statusCode(expectedStatusCode);

    }


    @ParameterizedTest
    @DisplayName("Проверка авторизации по постоянному токену")
    @CsvFileSource(resources = "/valueSource/AuthParameters.csv", numLinesToSkip = 1)
    @Execution(ExecutionMode.CONCURRENT)
    public void authTest(String token, int expectedStatusCode) {
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("http://localhost:8080/api/users")
                .then()
                .statusCode(expectedStatusCode)
                .log().all();
    }

}
