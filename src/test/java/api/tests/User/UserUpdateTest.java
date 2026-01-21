package api.tests.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Тестирование обновления данных пользователя")
public class UserUpdateTest extends UserBaseTest{

    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/UserUpdateParameters.csv", numLinesToSkip = 1)
    public void userUpdateTest(String login, String name, int expectedStatusCode, boolean isPositive) {
        /*
        Если сценарий позитивный то:
        1.Создание случайного пользователя
        2.Обновление его данных
        3.Чтение данных этого пользователя
        4.Удаление
        Если сценарий негативный, то:
        1.Создание случайного пользователя
        2.Обновление его данных
        3.Вывод сообщения об ошибке
         */
        if(isPositive) {
            userClient
                    .createTmpUser()
                    .updateUser(login, name)
                    .passes()
                    .userUpdateCheck(expectedStatusCode)
                    .then()
                    .readUserById()
                    .passes()
                    .userReadCheck()
                    .then()
                    .deleteUser();
        } else {
            userClient
                    .createTmpUser()
                    .updateUser(login, name)
                    .passes()
                    .userUpdateCheck(expectedStatusCode)
                    .then()
                    .deleteUser();
        }
    }
}
