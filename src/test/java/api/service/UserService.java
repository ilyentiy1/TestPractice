package api.service;

import api.dto.UserDTO;
import com.epam.http.annotations.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("http://localhost:8080/hub/api/rest")
@Header(name = "Authorization", value = "Bearer perm-aWx5ZW50aXlf.NDMtMQ==.iiibOJ4PrXyoSZjEsNOEKYHRkHsGI8")
@Header(name = "Accept", value = "application/json")
@ContentType(JSON)
public class UserService {

//    @GET("/users")
//    @QueryParameter()
//    public Response getUsers(){
//        return RestAssured.get("/users");
//    }
//
//    @POST("/users")
//    public static Response createUser;
//
//    @POST("/users/{user_id}")
//    public static Response updateUser;
//
//    @DELETE("/users/{user_id}")
//    public static Response deleteUser;
}
