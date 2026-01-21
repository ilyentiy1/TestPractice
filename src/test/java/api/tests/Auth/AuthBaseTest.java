package api.tests.Auth;

import api.BaseRestAssuredTest;
import api.client.AuthClient;
import org.junit.jupiter.api.BeforeEach;

public abstract class AuthBaseTest extends BaseRestAssuredTest {

    protected AuthClient authClient;

    @BeforeEach
    public void setUpAuthTest() {
        authClient = new AuthClient();
    }
}
