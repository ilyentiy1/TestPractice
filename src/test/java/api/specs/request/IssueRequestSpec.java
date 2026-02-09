package api.specs.request;

import io.restassured.specification.RequestSpecification;

import static api.utils.ConfigProvider.ENDPOINT_ISSUE;

public class IssueRequestSpec extends BaseRequestSpec{
    public static RequestSpecification issueRequestSpec() {
        return requestSpec(ENDPOINT_ISSUE);
    }
}
