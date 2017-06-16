/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import com.jayway.restassured.response.Response;
import java.io.IOException;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import se.nackademin.librarytest.librarytestsystem.model.SingleUser;
import se.nackademin.librarytest.librarytestsystem.model.User;
import se.nackademin.librarytest.librarytestsystem.model.Users;

/**
 *
 * @author testautom-nack
 */
public class UserRestServiceTest {

    // End point /users
    @Test
    public void testCreateUser() {

        //Create a random user
        //post the user
        //verifiera att user är skapades
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User user = userRestTestClient.createRandomUser();
        SingleUser singleUser = new SingleUser(user);

        Response responsePost = userRestTestClient.createUser(singleUser);

        System.out.println("new book " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());

    }

    // End point /users
    @Test
    public void testUpdateUser() {

        //Create a random user
        //post the user
        //verifiera att user är skapades
        // change phone nr
        //Put the user with changes
        //verifiera att ändringen sparades statuscod 200
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User user = userRestTestClient.createRandomUser();
        SingleUser singleUser = new SingleUser(user);

        Response responsePost = userRestTestClient.createUser(singleUser);

        System.out.println("new book " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());

        String phone = UUID.randomUUID().toString().substring(1, 5);
        user.setPhone(phone);

        Response responsePut = userRestTestClient.putUser(singleUser);

        System.out.println("status kod " + responsePut.statusCode());
        assertEquals("Status code should be 200", 200, responsePut.statusCode());

    }

    //  Endpoint /users
    @Test
    public void testGetAllUser() throws IOException {

        Response response = new UserRestTestClient().getAllUser();
        assertEquals("Status code should be 200", 200, response.statusCode());
        System.out.println(response);
    }

    // Endpoint /users/{id}
    //Get the user with the specified id
    @Test
    public void testGetUserWithSpecifiedId() {

        //Create a random user
        //post and verifiera att booken är skapad
        //Get the user with this Id 
        //verifiera att booken with same id är hämtat
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User user = userRestTestClient.createRandomUser();
        SingleUser singleUser = new SingleUser(user);

        Response responsePost = userRestTestClient.createUser(singleUser);

        System.out.println("new book " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());

        Response response = new UserRestTestClient().getUser(user.getId());
        assertEquals("Status code should be 200", 200, response.statusCode());

    }

    // Endpoint /users/{id}
    //Deleat user with the specified id 
    @Test
    public void testDeleteUserWithSpecifiedId() {
        //Create a randomUser and post the User
        //Verifiera att user är skapat
        //create loan för the user         -------------------->?
        // Deleta User och verifiera att user är delete
        //verifiera att loan är borta   ---------------------------------->?
        // 
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User user = userRestTestClient.createRandomUser();
        SingleUser singleUser = new SingleUser(user);

        Response responsePost = userRestTestClient.createUser(singleUser);

        System.out.println("new users id är: "+ user.getId()+". Status code för ny book är: " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());
        
        Response deleteResponse = userRestTestClient.deleteUser(user.getId());  
        System.err.println("Book with id: "+ user.getId()+ " är delete. Status code är: " + deleteResponse.statusCode());
        assertEquals("Status code should be 204", 204, deleteResponse.statusCode());

    }

}
