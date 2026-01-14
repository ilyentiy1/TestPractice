package api;

import api.dto.UserDTO;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static io.restassured.RestAssured.given;

import static api.specifications.Specifications.requestSpec;

@DisplayName("API-Тестирование Youtrack")
public abstract class BaseRestAssuredTest {

    @BeforeEach
    public void setUp()  {
    }

}
