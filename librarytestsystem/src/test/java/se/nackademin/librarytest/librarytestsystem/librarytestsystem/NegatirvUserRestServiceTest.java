/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import com.jayway.restassured.response.Response;
import java.util.Random;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import se.nackademin.librarytest.librarytestsystem.model.SingleUser;
import se.nackademin.librarytest.librarytestsystem.model.User;

/**
 *
 * @author testautom-nack
 */
public class NegatirvUserRestServiceTest {

    // * Endpoint: /users
    //-----------------------------    
    //The user id was already i the database
    @Test
    public void testCreateUserWithIDInDB_StusCode400() {

        // user with id 13 finns i DB
        //Get a user from DB 
        // verifiera att user finns
        //get det user id
        //create a random user 
        // set the random usersid till user som vi hämtade
        // post random user with the user id som vi hämtade först
        //verifiera att det går inte att skapa user
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        Response responseGetUser = userRestTestClient.getUser(13);
        assertEquals("Status code should be 200", 200, responseGetUser.statusCode());

        User user = responseGetUser.jsonPath().getObject("user", User.class);
        Assert.assertNotNull(user);

        User userRandom = new User();
        userRandom = userRestTestClient.createRandomUser();
        userRandom.setId(user.getId());

        Response responsePostUser = userRestTestClient.createUser(new SingleUser(user));    //.createUser(new SingleUser(user));

        System.out.println("Status koden ska vara 400: " + responsePostUser.statusCode());
        assertEquals("En user med samma id finns redan i databasen,", 400, responsePostUser.statusCode());

    }

    // * Endpoint: /users
    //-----------------------------    
    // Create a user with no display name.
    @Test
    public void testCreateUserWithNoDisplayName_StusCode400() {

        //create a random user
        //set display name till null
        //posta den 
        // verifiera att det går inte skapa user
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User userRandom = new User();
        userRandom = userRestTestClient.createRandomUser();

        userRandom.setDisplayName(null);
        Response responsePostUser = userRestTestClient.createUser(new SingleUser(userRandom));
        assertEquals("User utan display name kan inte skapas", 400, responsePostUser.statusCode());
        System.out.println("User utan display name kan inte skapas");

    }

    // * Endpoint: /users
    //-----------------------------    
    // Create a user with no password.
    @Test
    public void testCreateUserWithNoPassword_StusCode400() {

        //create a random user
        //set password till null
        //posta den 
        // verifiera att det går inte skapa user
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User userRandom = new User();
        userRandom = userRestTestClient.createRandomUser();

        userRandom.setPassword(null);
        Response responsePostUser = userRestTestClient.createUser(new SingleUser(userRandom));
        assertEquals("User utan password kan inte skapas", 400, responsePostUser.statusCode());
        System.out.println("User utan password kan inte skapas därför statuscode blir " + responsePostUser.statusCode());

    }
    // * Endpoint: /users
    //-----------------------------    
    // Create a user with no role.

    @Test
    public void testCreateUserWithNoRole_StusCode400() {

        //create a random user
        //set role till null
        //posta den 
        // verifiera att det går inte skapa user
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User userRandom = new User();
        userRandom = userRestTestClient.createRandomUser();

        userRandom.setRole(null);
        Response responsePostUser = userRestTestClient.createUser(new SingleUser(userRandom));
        assertEquals("User utan role kan inte skapas", 400, responsePostUser.statusCode());
        System.out.println("User utan role kan inte skapas därför statuscode blir " + responsePostUser.statusCode());

    }

    // * Endpoint: /users
    //-----------------------------    
    // Create a user with display name which allredy exist in DB
    @Test
    public void testCreateUserDisplayNameWhichExistInDB_StusCode400() {

        //Get an user which exist in DB. tex user id 13
        //Get display name för user
        //create a random user   
        //sätt diplay name för that till display name which we get from DB
        //posta den 
        // verifiera att det går inte skapa user
        UserRestTestClient userRestTestClient = new UserRestTestClient();

        Response responseGetUser = userRestTestClient.getUser(13);
        User user13 = responseGetUser.jsonPath().getObject("user", User.class);
        Assert.assertNotNull(user13);

        User userRandom = new User();
        userRandom = userRestTestClient.createRandomUser();

        userRandom.setDisplayName(user13.getDisplayName());
        Response responsePostUser = userRestTestClient.createUser(new SingleUser(userRandom));
        assertEquals("Display name allredy exist in the DB", 400, responsePostUser.statusCode());
        System.out.println("Display name allredy exist in the DB. Därför status code för din körning blir: " + responsePostUser.statusCode());

    }

    // * Endpoint: /users
    //-----------------------------    
    // Update a user with no display name.
    @Test
    public void testUpdateUserWithNoDisplayName_StusCode400() {

        //create a random user
        //posta den and verifiy the user exist in the DB
        //Get the random user
        //set display name till null
        //Update / put  den 
        // verifiera att det går inte updatera user
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User userRandom = new User();
        userRandom = userRestTestClient.createRandomUser();

        Response responsePostRandomUser = userRestTestClient.createUser(new SingleUser(userRandom));
        assertEquals("A new user är skapat", 201, responsePostRandomUser.statusCode());

        userRandom.setDisplayName(null);

        Response responsePostUser = userRestTestClient.putUser(new SingleUser(userRandom));
        assertEquals("User utan display name kan inte uppdateras", 400, responsePostUser.statusCode());
        System.out.println("User utan display name kan inte uppdateras");

    }

    // * Endpoint: /users
    //-----------------------------    
    // Update a user with no password.
    @Test
    public void testUpdateUserWithNoPassword_StusCode400() {

        //create a random user
        //posta den and verifiy the user exist in the DB
        //Get the random user
        //set password till null
        //Update / put  den 
        // verifiera att det går inte updatera user
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User userRandom = new User();
        userRandom = userRestTestClient.createRandomUser();

        Response responsePostRandomUser = userRestTestClient.createUser(new SingleUser(userRandom));
        assertEquals("A new user är skapat", 201, responsePostRandomUser.statusCode());

        userRandom.setPassword(null);

        Response responsePostUser = userRestTestClient.putUser(new SingleUser(userRandom));
        assertEquals("User utan password kan inte uppdateras", 400, responsePostUser.statusCode());
        System.out.println("User utan password kan inte uppdaters");

    }

    // * Endpoint: /users
    //-----------------------------    
    // Update a user with no role
    @Test
    public void testUpdateUserWithNoRole_StusCode400() {

        //create a random user
        //posta den and verifiy the user exist in the DB
        //Get the random user
        //set role till null
        //Update / put  den 
        // verifiera att det går inte updatera user
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User userRandom = new User();
        userRandom = userRestTestClient.createRandomUser();

        Response responsePostRandomUser = userRestTestClient.createUser(new SingleUser(userRandom));
        assertEquals("A new user är skapat", 201, responsePostRandomUser.statusCode());

        userRandom.setRole(null);

        Response responsePostUser = userRestTestClient.putUser(new SingleUser(userRandom));
        assertEquals("User utan role kan inte uppdateras", 400, responsePostUser.statusCode());
        System.out.println("User utan role kan inte uppdateras");

    }

    // * Endpoint: /users
    //-----------------------------    
    // update a user with display name which allredy exist in DB
    @Test
    public void testUpdateUserDisplayNameWhichExistInDB_StusCode400() {

        //Get an user which exist in DB. tex user id 13
        //Get display name för user
        //create a random user 
        //post that and verifiera att den är skapad
        //sätt diplay name för that till display name which we get from DB
        //uppdate random user 
        // verifiera att det går inte uppdater user with a user wich allredy exist in the DB
        UserRestTestClient userRestTestClient = new UserRestTestClient();

        Response responseGetUser = userRestTestClient.getUser(13);
        User user13 = responseGetUser.jsonPath().getObject("user", User.class);
        Assert.assertNotNull(user13);

        User userRandom = new User();
        userRandom = userRestTestClient.createRandomUser();

        Response responsePostRandomUser = userRestTestClient.createUser(new SingleUser(userRandom));
        assertEquals("A new user är skapat", 201, responsePostRandomUser.statusCode());

        userRandom.setDisplayName(user13.getDisplayName());

        Response responsePostUser = userRestTestClient.putUser(new SingleUser(userRandom));
        assertEquals("Display name allredy exist in the DB", 400, responsePostUser.statusCode());
        System.out.println("Display name allredy exist in the DB. Därför status code för din körning blir: " + responsePostUser.statusCode());

    }
    // * Endpoint: /users
    //-----------------------------    
    // update a user which dose not exist in the DB 

    @Test
    public void testUpdateUserWhichNoExistInDB_StatusCode404() {
        //create an random user
        //update the user 
        //verifiera att user existera inte i DB

        User userRandom = new User();
        userRandom = new UserRestTestClient().createRandomUser();
        SingleUser singleUser = new SingleUser(userRandom);

        Response response = new UserRestTestClient().putUser(singleUser);
        assertEquals("Status code should be 404", 404, response.statusCode());

        System.out.println("User som du föröker uppdater finns inte i DB. Därför status code för din körning blir: " + response.statusCode());
    }

    // Endpoint: /users/{id}
    //Get
    @Test
    public void testGetUserWithSpecifiedIdNotFound_StatusCode404() {

        //Create random id
        //get the user för the random id
        //verifiera att user finns inte i DB
        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Response response = new UserRestTestClient().getUser(id);
        assertEquals("Status code should be 404", 404, response.statusCode());
        System.out.println("User som du föröker hämta finns inte i DB. Därför status code för din körning blir: " + response.statusCode());
    }
    
      // Endpoint: /users/{id}
    //Delete
    
    @Test
    public void testDeleteUserWithSpecifiedIdNotFound_StatusCode404(){
        
                //Create random id
        //get the user för the random id
        //verifiera att user finns inte i DB
        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Response response = new UserRestTestClient().deleteUser(id);
        assertEquals("Status code should be 404", 404, response.statusCode());
        System.out.println("User som du föröker göra delete finns inte i DB. Därför status code för din körning blir: " + response.statusCode());
    }
}
