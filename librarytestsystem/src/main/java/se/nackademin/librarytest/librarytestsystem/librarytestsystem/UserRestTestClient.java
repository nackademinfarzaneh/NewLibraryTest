/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.post;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;
import se.nackademin.librarytest.librarytestsystem.model.SingleUser;
import se.nackademin.librarytest.librarytestsystem.model.User;
import se.nackademin.librarytest.librarytestsystem.model.User.Role;

/**
 *
 * @author testautom-nack
 */
public class UserRestTestClient {

    private static final String restUrl = "http://localhost:8080/librarytest-rest/";

    public User createRandomUser() {

        BookRestTestClient restTestClient = new BookRestTestClient();

        //  r.nextInt((max - min) + 1) + min;
        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        String password = UUID.randomUUID().toString().substring(1, 5);
        Role role;
        String firstName = UUID.randomUUID().toString().substring(1, 5);
        String lastName = UUID.randomUUID().toString().substring(1, 5);
        String phone = UUID.randomUUID().toString().substring(1, 5);
        String email = UUID.randomUUID().toString().substring(1, 5);

        User user = new User(id, firstName, password, Role.LOANER, firstName, lastName, phone, email);
        return user;
    }
    
        public Response createUserWithId(int id) {

        String postResourceName = "users/ " + id;
        Response postResponse = post(restUrl + postResourceName);
        return postResponse;
    }

    public Response createUser(SingleUser singleUser) {
        String postResourceName = "users/";
        Response response = resoursCreator(postResourceName, singleUser);
        return response;
    }

    private static Response resoursCreator(String resourceName, SingleUser singleUser) {
        Response response = given().contentType(ContentType.JSON).body(singleUser).log().all().post(restUrl + resourceName);
        return response;
    }

    public Response putUser(SingleUser singleUser) {
        Response response = resoursPutter("users", singleUser);
        return response;
    }

    private static Response resoursPutter(String resourceName, SingleUser singleUsers) {
        Response response = given().contentType(ContentType.JSON).body(singleUsers).log().all().put(restUrl + resourceName).prettyPeek();
        return response;
    }

    public Response getAllUser() {
        Response response = resoursGetter("users");
        return response;
    }

    public Response getUser(int id) {

        String resourceName = "users/" + id;
        Response response = resoursGetter(resourceName);
        return response;
    }

    private static Response resoursGetter(String resourceName) {
        Response response = given().accept(ContentType.JSON).log().all().get(restUrl + resourceName).prettyPeek();
        return response;
    }
    
    public Response deleteUser(int id) {

        String deleteResourceName = "users/" + id;
        Response deleteResponse = delete(restUrl + deleteResourceName);
        return deleteResponse;
    }

 

}
