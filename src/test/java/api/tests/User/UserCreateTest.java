package api.tests.User;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


@DisplayName("Тестирование создания,чтения,удаления пользователя")
public class UserCreateTest extends UserBaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/UserParameters.csv", numLinesToSkip = 1)
    public void userParameterizedTest(String login, String name, int expectedStatusCode, boolean isPositive) {
        /*
        1. создание сущности пользователя
        2. чтение созданной сущности
        3. удаление сущности
         */
        if(isPositive) {
            userClient
                    .createUser(login, name)
                    .passes()
                    .userCreationCheck(expectedStatusCode)
                    .then()
                    .readUserById()
                    .passes()
                    .userReadCheck()
                    .then()
                    .deleteUser()
                    .passes()
                    .userDeleteCheck();
        } else {
            userClient
                    .createUser(login, name)
                    .passes()
                    .userCreationCheck(expectedStatusCode);
        }
    }
}
