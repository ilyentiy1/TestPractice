package api.tests.User;


import api.specs.response.UserSpecs;
import api.tests.BaseTest;
import api.client.UserClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

@DisplayName("Тестирование обновления данных пользователя")
public class UserUpdateTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/UserUpdateParameters.csv", numLinesToSkip = 1)
    public void userUpdateTest(String login, String name, int expectedStatusCode, boolean isPositive) {

        /*
        Если сценарий позитивный, то:
        1. Создание случайного пользователя
        2. Обновление его данных
        3. Чтение данных этого пользователя
        4. Удаление
        Если сценарий негативный, то:
        1. Создание случайного пользователя
        2. Обновление его данных
        3. Вывод сообщения об ошибке
         */

        UserSpecs userSpecs = new UserClient()
                .createTmpUser()
                .updateUser(login, name)
                .passes();

        if(isPositive) {
            userSpecs
                    .userUpdateCheck(expectedStatusCode)
                    .then()
                    .readUserById()
                    .passes()
                    .userReadCheck(login);
        } else {
            userSpecs
                    .userUpdateCheck(expectedStatusCode);
        }

        userSpecs.then().deleteUser();

        List<String> usersLogins = new UserClient()
                .readUsers()
                .then()
                .extract().body().jsonPath().getList("users.login");

        /*
        - Проверка на основе логинов пользователей
        - Проверка того, что после удаления ранее созданной сущности больше нет в БД
        - Проверка того, что в БД нет записи с невалидными данными
         */
        Assertions.assertTrue(usersLogins.stream().noneMatch(x -> x.contains(login)));
    }
}
