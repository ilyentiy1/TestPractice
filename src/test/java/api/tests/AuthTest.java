package api.tests;

import api.client.AuthClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Проверка авторизации по постоянному токену")
public class AuthTest extends BaseTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/api/valueSource/AuthParameters.csv", numLinesToSkip = 1)
    public void authTest(String token, int expectedStatusCode) {
        new AuthClient()
                .executeAuth(token)
                .passes()
                .authCheck(expectedStatusCode);
    }
}
