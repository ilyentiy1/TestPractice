package api.specs.request;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static api.utils.ConfigProvider.TOKEN;
import static api.utils.ConfigProvider.URL;
import static io.restassured.http.ContentType.JSON;

public class BaseRequestSpecification {
    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .addHeader("Authorization", "Bearer " + TOKEN)
                .setAccept(JSON)
                .setContentType(JSON)
                .build();
    }
}
