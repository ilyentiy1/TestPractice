package api.specs.request;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import static api.utils.ConfigProvider.*;
import static io.restassured.http.ContentType.JSON;

public class RequestSpecs {

    private static RequestSpecification requestSpec(String endpoint) {
        return new RequestSpecBuilder()
                .setBaseUri(URL + endpoint)
                .addHeader("Authorization", "Bearer " + TOKEN)
                .setAccept(JSON)
                .setContentType(JSON)
                .build();
    }

    public static RequestSpecification userRequestSpec() {
        return requestSpec(ENDPOINT_USER);
    }

    public static RequestSpecification issueRequestSpec() {
        return requestSpec(ENDPOINT_ISSUE);
    }

    public static RequestSpecification projectRequestSpec() {
        return requestSpec(ENDPOINT_PROJECT);
    }

    public static RequestSpecification authRequestSpec(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(ENDPOINT_AUTH)
                .addHeader("Authorization", "Bearer " + token)
                .setAccept(JSON)
                .setContentType(JSON)
                .log(LogDetail.URI)
                .build();
    }
}
