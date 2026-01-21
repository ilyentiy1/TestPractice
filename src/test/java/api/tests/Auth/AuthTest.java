package api.tests.Auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Проверка авторизации по постоянному токену")
public class AuthTest extends AuthBaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/AuthParameters.csv", numLinesToSkip = 1)
    public void authTest(String token, int expectedStatusCode) {

        authClient
                .executeAuth(token)
                .passes()
                .authCheck(expectedStatusCode);
    }
}
