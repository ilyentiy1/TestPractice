package api.tests.User;

import api.BaseRestAssuredTest;
import api.client.UserClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;



public abstract class UserBaseTest extends BaseRestAssuredTest {

    protected UserClient userClient;

    @BeforeEach
    public void setUpUserTest() {
        userClient = new UserClient();
    }
}
