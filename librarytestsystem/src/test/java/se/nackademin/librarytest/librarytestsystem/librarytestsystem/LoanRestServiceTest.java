/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import se.nackademin.librarytest.librarytestsystem.model.Author;
import se.nackademin.librarytest.librarytestsystem.model.Book;
import se.nackademin.librarytest.librarytestsystem.model.SingleBook;
import se.nackademin.librarytest.librarytestsystem.model.Loan;
import se.nackademin.librarytest.librarytestsystem.model.SingleAuthor;
import se.nackademin.librarytest.librarytestsystem.model.SingleLoan;
import se.nackademin.librarytest.librarytestsystem.model.User;
import se.nackademin.librarytest.librarytestsystem.model.SingleUser;

/**
 *
 * @author testautom-nack
 */
public class LoanRestServiceTest {

    @Test
    @Ignore
    public void testDeleateLoan() {

        Response responseDelete = new LoanRestTestClient().deleteLoan(176);
        Response responseDelete2 = new LoanRestTestClient().deleteLoan(176);
  
        assertEquals("Status code should be 204 ", 204, responseDelete.statusCode());
    }

    // End point /loans
    @Test
    public void testCreateloan() {

        //Create a random user
        //Verifiera att user finns
        //Create a new loan
        //add the user and book to it
        //post the loan
        //verifiera att Status code är 201
        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());
        Book book = new BookRestServiceTest().createBook();

        Assert.assertNotNull(book);

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Loan loan = new Loan(book, dateBorrowedStr, dateDueStr, user);
        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));

        System.out.println("new loan är skapad " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());
    }

    //End poind  Put  /loans
    //Jag försökte skapa en lån med det format som vi fick i API dokumentaionen
    //Därför jag nollsällde de variable som kulle inte presenteras under lån. Det gick och skapa de men inte uppdatera dem.
    //formatet som visades i output va det som visades i dokumentationen men i DB persenterades alla variablar.
    //Därför bortkomenterade jag nollställningen
    @Test
    public void testUpdateLoanWithNewData() {
        //uppdate user data

        //Create a random user
        //Verifiera att user finns
        //Create a new loan
        //add the user and book to it
        //post the loan
        //verifiera att Status code är 201
        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestServiceTest().createBook();
        Assert.assertNotNull(book);

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();
        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);
        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));

        System.out.println("new loan är skapad " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());

        String displayName = UUID.randomUUID().toString().substring(1, 5);

        loan.getUser().setDisplayName(displayName);

        Response responsePut = new LoanRestTestClient().putLoan(new SingleLoan(loan));
        assertEquals("Status code should be 200", 200, responsePut.statusCode());

    }

    // End point: Get /loans
    @Test
    public void testGetAllaLoan() {

        Response responseGet = new LoanRestTestClient().getAllLoan();
        assertEquals("Status code should be 200 ", 200, responseGet.statusCode());

    }

    // End point: Get /loan/{id}
    @Test
    public void testGetLoanWithSpicifiedID() {

        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestServiceTest().createBook();
        Assert.assertNotNull(book);

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();
        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);
        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));

        Response responseGet = new LoanRestTestClient().getLoan(loan.getId());
        assertEquals("Status code should be 200 ", 200, responseGet.statusCode());
    }

    // End point: Delete /loan/{id}
    @Test
    public void testDeleteWithSpicifiedID() {
        //create Loan
        //bekräfta att den är skapad
        //Delete loan
        //bekräfta att den är detete

        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestServiceTest().createBook();

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();
        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);
        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));

        System.out.println("new loan är skapad " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());

        Response responseDelete = new LoanRestTestClient().deleteLoan(loan.getId());
        assertEquals("Status code should be 204 ", 204, responseDelete.statusCode());
    }

    // End point: Get  /loans/ofuser/{user id}
    @Test
    public void testGetAllLoansOfTheSpecifiedUser() {
        //Create a user and verifiera att user är skapat
        //Create två books
        //Create två loan 

        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestServiceTest().createBook();

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        Book book2 = new BookRestServiceTest().createBook();

        Response responsePostBook2 = new BookRestTestClient().createBook(new SingleBook(book2));
        assertEquals("Status code should be 201 ", 201, responsePostBook2.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();
        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);
        Assert.assertNotNull(loan);

        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));
        System.out.println("new loan är skapad " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());

        Integer id2 = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan2 = new Loan(id2, book2, dateBorrowedStr, dateDueStr, user);
        Assert.assertNotNull(loan);

        Response responsePost2 = loanRestTestClient.createLoan(new SingleLoan(loan2));
        assertEquals("Status code should be 201", 201, responsePost2.statusCode());
        System.out.println("new loan är skapad " + responsePost2.statusCode());

        Response responseGet = new LoanRestTestClient().getLoanOfUser(user.getId());
        assertEquals("Status code should be 200 ", 200, responseGet.statusCode());

    }

    // End point: Get  /loans/ofbook/{book id}
    @Test
    public void testGetAllLoansOfTheSpecifiedBook() {

        //create a book verifiera att booken är skapat
        //create user1 verifiera att user är skapat
        //create user2 verifiera att user2 är skapat
        //create a loan av book för the user1
        //create a loan2 av book för the user2
        //get all loan of specified book
        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        User user2 = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user2);

        Response responsePostUser2 = new UserRestTestClient().createUser(new SingleUser(user2));
        assertEquals("Status code should be 201 ", 201, responsePostUser2.statusCode());

        Book book = new BookRestServiceTest().createBook();
        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();
        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id2 = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan2 = new Loan(id2, book, dateBorrowedStr, dateDueStr, user2);
        Assert.assertNotNull(loan2);

        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;
        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);
        Assert.assertNotNull(loan);

        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));
        System.out.println("new loan är skapad " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());

        Response responsePost1 = loanRestTestClient.createLoan(new SingleLoan(loan2));
        System.out.println("new loan är skapad " + responsePost1.statusCode());
        assertEquals("Status code should be 201", 201, responsePost1.statusCode());

        Response responseGet = new LoanRestTestClient().getLoanOfBook(book.getId());
        assertEquals("Status code should be 200 ", 200, responseGet.statusCode());

    }

    // End Point: Get /loans/ofuser/ {user id}/ofbook/{book id}
    @Test
    public void testGetLoanoOfSpecifiedBookBySpecifiedUser() {
        //Create randomBook, Verifiera att booken är skapad
        //Create randomUser, Verifiera att User är skapad
        //Create a loan av book by the user
        //Get the loan        

        Book book = new BookRestServiceTest().createBook();
        
        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);
        Assert.assertNotNull(loan);

        Response responsePostLoan = loanRestTestClient.createLoan(new SingleLoan(loan));
        System.out.println("new loan är skapad " + responsePostLoan.statusCode());
        assertEquals("Status code should be 201", 201, responsePostLoan.statusCode());

        Response responseGet = new LoanRestTestClient().getLoanOfBookByUser(new SingleLoan(loan));
        assertEquals("Status code should be 200 ", 200, responseGet.statusCode());
    }

}
