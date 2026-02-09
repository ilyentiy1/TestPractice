package api.specs.request;

import io.restassured.specification.RequestSpecification;

import static api.utils.ConfigProvider.ENDPOINT_PROJECT;

public class ProjectRequestSpec extends BaseRequestSpec{
    public static RequestSpecification projectRequestSpec() {
        return requestSpec(ENDPOINT_PROJECT);
    }
}
