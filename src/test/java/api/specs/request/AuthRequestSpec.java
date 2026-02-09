package api.specs.request;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

import static api.utils.ConfigProvider.ENDPOINT_AUTH;
import static io.restassured.http.ContentType.JSON;

public class AuthRequestSpec {
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
