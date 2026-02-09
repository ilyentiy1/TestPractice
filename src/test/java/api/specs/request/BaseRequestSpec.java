package api.specs.request;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static api.utils.ConfigProvider.*;
import static io.restassured.http.ContentType.JSON;

public class BaseRequestSpec {

    protected static RequestSpecification requestSpec(String endpoint) {
        return new RequestSpecBuilder()
                .setBaseUri(URL + endpoint)
                .addHeader("Authorization", "Bearer " + TOKEN)
                .setAccept(JSON)
                .setContentType(JSON)
                .build();
    }

}
