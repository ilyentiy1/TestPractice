package api.specifications;

import static api.utils.ConfigProvider.URL;
import static api.utils.ConfigProvider.TOKEN;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Specifications {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .addHeader("Authorization", "Bearer " + TOKEN)
                .setAccept(JSON)
                .setContentType(JSON)
                .build();
    }


    public static ResponseSpecification responseSpec(int status) {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(status)
                .build();
    }

    public static ResponseSpecification createUserResponseSpec(int status) {
        return status == 200? new ResponseSpecBuilder()
                .expectStatusCode(status)
                .expectBody(matchesJsonSchemaInClasspath("api/jsonSchema/userResponseSchema.json"))
                .log(LogDetail.ALL)
                .build() :
                new ResponseSpecBuilder()
                        .expectStatusCode(status)
                        .log(LogDetail.ALL)
                        .build();
    }

    public static ResponseSpecification createIssueResponseSpec(int status) {
        return status == 200? new ResponseSpecBuilder()
                .expectStatusCode(status)
                .expectBody(matchesJsonSchemaInClasspath("api/jsonSchema/issueResponseSchema.json"))
                .log(LogDetail.ALL)
                .build() :
                new ResponseSpecBuilder()
                        .expectStatusCode(status)
                        .log(LogDetail.ALL)
                        .build();
    }

    public static ResponseSpecification getIssueResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .build();
    }
}
