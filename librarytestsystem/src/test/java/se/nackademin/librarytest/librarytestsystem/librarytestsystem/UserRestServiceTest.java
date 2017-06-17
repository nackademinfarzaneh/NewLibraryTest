/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import com.jayway.restassured.response.Response;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import se.nackademin.librarytest.librarytestsystem.model.Book;
import se.nackademin.librarytest.librarytestsystem.model.Loan;
import se.nackademin.librarytest.librarytestsystem.model.SingleBook;
import se.nackademin.librarytest.librarytestsystem.model.SingleLoan;
import se.nackademin.librarytest.librarytestsystem.model.SingleUser;
import se.nackademin.librarytest.librarytestsystem.model.User;
import se.nackademin.librarytest.librarytestsystem.model.Users;

/**
 *
 * @author testautom-nack
 */
public class UserRestServiceTest {

    @Test
    @Ignore
    public void testDeteUser() {

        UserRestTestClient userRestTestClient = new UserRestTestClient();
        Response deleteResponse1 = userRestTestClient.deleteUser(244);
           Response deleteResponse2 = userRestTestClient.deleteUser(293);
              Response deleteResponse3 = userRestTestClient.deleteUser(179);
                 Response deleteResponse4 = userRestTestClient.deleteUser(288);
                 
                    Response deleteResponse5 = userRestTestClient.deleteUser(133);
           Response deleteResponse6 = userRestTestClient.deleteUser(219);
              Response deleteResponse7 = userRestTestClient.deleteUser(89);
            //     Response deleteResponse8 = userRestTestClient.deleteUser(293);
       
       
    }

    /**
     * End point /users Create a random user, post the user, verifiera att user
     * är skapades
     */
    @Test
    public void testCreateUser() {

        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User user = userRestTestClient.createRandomUser();
        SingleUser singleUser = new SingleUser(user);

        Response responsePost = userRestTestClient.createUser(singleUser);

        System.out.println("new book " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());

    }

    // 
    /**
     * End point /users Create a random user, post the user, verifiera att user
     * är skapades change phone nr, Put the user with changes, verifiera att
     * ändringen sparades statuscod 200
     *
     */
    @Test
    public void testUpdateUser() {

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

    /**
     * Endpoint /users
     *
     * @throws java.io.IOException
     */
    @Test
    public void testGetAllUser() throws IOException {

        Response response = new UserRestTestClient().getAllUser();
        assertEquals("Status code should be 200", 200, response.statusCode());
        System.out.println(response);
    }

    /**
     * Endpoint /users/{id} Get the user with the specified id Create a random
     * user, post and verifiera att booken är skapad Get the user with this Id
     * verifiera att booken with same id är hämtat
     */
    @Test
    public void testGetUserWithSpecifiedId() {

        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User user = userRestTestClient.createRandomUser();
        SingleUser singleUser = new SingleUser(user);

        Response responsePost = userRestTestClient.createUser(singleUser);

        System.out.println("new book " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());

        Response response = new UserRestTestClient().getUser(user.getId());
        assertEquals("Status code should be 200", 200, response.statusCode());

    }

    // 
    //
    /**
     * Endpoint /users/{id} , Deleat user with the specified id Create a
     * randomUser and post the User, Verifiera att user är skapa
     *
     */
    @Test
    public void testDeleteUserWithSpecifiedId() {
        //
        //t
        //create loan för the user         -------------------->?
        // Deleta User och verifiera att user är delete
        //verifiera att loan är borta   ---------------------------------->?
        // 
        UserRestTestClient userRestTestClient = new UserRestTestClient();
        User user = userRestTestClient.createRandomUser();
        SingleUser singleUser = new SingleUser(user);

        Response responsePostUser = userRestTestClient.createUser(singleUser);

        System.out.println("new users id är: " + user.getId() + ". Status code för ny book är: " + responsePostUser.statusCode());
        assertEquals("Status code should be 201", 201, responsePostUser.statusCode());

        Book book1 = new BookRestTestClient().createRandomBook();
        Assert.assertNotNull(book1);

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book1));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;
        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Loan loan = new Loan(id, book1, dateBorrowedStr, dateDueStr, user);
        Response responsePostLoan = loanRestTestClient.createLoan(new SingleLoan(loan));

        System.out.println("new loan är skapad " + responsePostLoan.statusCode());
        assertEquals("Status code should be 201", 201, responsePostLoan.statusCode());

        Response deleteResponse = userRestTestClient.deleteUser(user.getId());
        System.err.println("Book with id: " + user.getId() + " är delete. Status code är: " + deleteResponse.statusCode());
        assertEquals("Status code should be 204", 204, deleteResponse.statusCode());

        Response responseGet = new LoanRestTestClient().getLoan(loan.getId());
        System.err.println("XXXXXXXXXXstatus kod för att hämta loan är :  " + responseGet.statusCode());
        assertEquals("Status code should be 404 ", 404, responseGet.statusCode());

    }

}
