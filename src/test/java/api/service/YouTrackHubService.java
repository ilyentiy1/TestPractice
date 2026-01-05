package api.service;

import api.dto.UserDTO;
import com.epam.http.annotations.*;
import com.epam.http.requests.RestDataMethod;
import com.epam.http.requests.RestMethod;

@ServiceDomain("http://localhost:8080/hub/api/rest")
@Header(name = "Authorization", value = "Bearer: ")
@Header(name = "Accept", value = "application/json")
@ContentType(io.restassured.http.ContentType.JSON)
public class YouTrackHubService {

    @GET("/users")
    @QueryParameter(name = "fields", value = "id,login,name")
    public static RestDataMethod<UserDTO> getUsers;

    @POST("/users")
    public static RestDataMethod<UserDTO> createUser;

    @POST("/users/{user_id}")
    public static RestDataMethod<UserDTO> updateUser;

    @DELETE("/users/{user_id}")
    public static RestMethod deleteUser;
}
