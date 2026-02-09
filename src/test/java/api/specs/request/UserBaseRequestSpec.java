package api.specs.request;

import io.restassured.specification.RequestSpecification;

import static api.utils.ConfigProvider.ENDPOINT_USER;

public class UserBaseRequestSpec extends BaseRequestSpec {
    public static RequestSpecification userRequestSpec() {
        return requestSpec(ENDPOINT_USER);
    }
}
