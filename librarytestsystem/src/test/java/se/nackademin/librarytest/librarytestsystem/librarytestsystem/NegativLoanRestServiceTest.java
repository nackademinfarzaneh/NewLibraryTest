/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import com.jayway.restassured.response.Response;
import java.util.Random;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import se.nackademin.librarytest.librarytestsystem.model.Book;
import se.nackademin.librarytest.librarytestsystem.model.Loan;
import se.nackademin.librarytest.librarytestsystem.model.Loans;
import se.nackademin.librarytest.librarytestsystem.model.SingleBook;
import se.nackademin.librarytest.librarytestsystem.model.SingleLoan;
import se.nackademin.librarytest.librarytestsystem.model.SingleUser;
import se.nackademin.librarytest.librarytestsystem.model.User;
import se.nackademin.librarytest.librarytestsystem.model.Users;

/**
 *
 * @author testautom-nack
 */
public class NegativLoanRestServiceTest {

    //End point: Post  /loans
    @Test
    public void createLoanUserNotFoundInDB_StatusCode400() {

        //Create random book, Post it, verifiera att book is in DB    
        //Create random user, do not post it
        //Create loan 
        // post the loan , verifiera att user finns inte i DB
        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Book book = new BookRestTestClient().createRandomBook();
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
        assertEquals("Status code should be 400", 400, responsePost.statusCode());

    }

    //End point: Post  /loans
    @Test
    public void testCreateLoanBookNotFoundInDB_StatusCode400() {

        //Create random book, do not Post it,    
        //Create random user, do not post it
        //Create loan 
        // post the loan , verifiera att user finns inte i DB
        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestTestClient().createRandomBook();
        Assert.assertNotNull(book);

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);
        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));

        System.out.println("Loan är inte skapad eftersom book hitades inte " + responsePost.statusCode());
        assertEquals("Status code should be 400", 400, responsePost.statusCode());
    }

    /*
        //End point: Post  /loans
        Det här är en potensiell bugg
        The gick och skapa loan utan att user ha rol tillsatt
     */
    @Test
    @Ignore
    public void testCreateLoanUserHasNoRole_StatusCode400() {

        //Create random book, do not Post it,    
        //Create random user, do not post it
        //Create loan 
        // post the loan , verifiera att user finns inte i DB
        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);
        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestTestClient().createRandomBook();
        Assert.assertNotNull(book);

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);
        user.setRole(null);

        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));

        System.out.println("Loan är inte skapad eftersom user har inte Role " + responsePost.statusCode());
        assertEquals("Status code should be 400", 400, responsePost.statusCode());
    }

    /*
        //End point: Post  /loans
        Det går inte uppdatera displayName av det user som man har skapat med en userDeiplayName som finns i DB
    
        //Get all loan
        //get the displayname of user
        //Spara de i en array
        //4-//hämta första user o get display name för that

        //Create user
        // update userDisplayName with the user from DB
        //Create book
        //innan skapa en loan uppdatera userDisplayName av user med userDisplayName som vi hämtade från DBi punkt 4
        //Create loan
        //Verifiera att det går inte skapa loan eftersom the user displayname already exist i DB   
     */
    @Test
    @Ignore
    public void testCreateLoanButUserDisplayNameAlreadyExistDB_StatusCode400() {

        Response responseGet = new LoanRestTestClient().getAllLoan();
        assertEquals("Status code should be 200 ", 200, responseGet.statusCode());

        Loans allloan = responseGet.jsonPath().getObject("loans.loan", Loans.class);
        Assert.assertNotNull(allloan);

        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestTestClient().createRandomBook();
        Assert.assertNotNull(book);

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        user.setDisplayName(allloan.getLoans().get(0).getUser().getDisplayName());

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);

        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));

        System.out.println("Loan är inte skapad eftersom userDisplay name finns i uner en annan loan i DB: " + responsePost.statusCode());
        assertEquals("Status code should be 400", 400, responsePost.statusCode());
    }

    /*
        End point: Post, /loans
     */
    @Test
    public void testCreateLoanWithSameUserAndBook_StatusCode409() {

        //Get all loan
        //Create a loan with same user, same book igen
        //verifiera att det går inte
        Response responseGet = new LoanRestTestClient().getAllLoan();
        assertEquals("Status code should be 200 ", 200, responseGet.statusCode());

        Loans allloan = responseGet.jsonPath().getObject("loans.loan", Loans.class);
        Assert.assertNotNull(allloan);

        Loan loan = allloan.get(0);
        loan.getBook();
        loan.getUser();

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();
        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));

        System.out.println("Loan är inte skapad eftersom userDisplay name finns i uner en annan loan i DB: " + responsePost.statusCode());
        assertEquals("Status code should be 400", 409, responsePost.statusCode());
    }

    @Test
    public void testCreateLoanWithNotEnoughAntallKopiaAvBook_StatusCode409() {

        //Create Book, verifiera att booken är skapad
        //Sätt antal kopia av booken till 0
        //Create User ; verifiera att user är skapd
        //Create a loan with same user, same book igen
        //verifiera att det går inte skapa lån
        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestTestClient().createRandomBook();
        Assert.assertNotNull(book);

        book.setTotalNbrCopies(0);
        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);

        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));

        System.out.println("Loan är inte skapad eftersom det finns inte tillräklig antall av booken tillgänglig: " + responsePost.statusCode());
        assertEquals("Status code should be 409", 409, responsePost.statusCode());

    }

    /*
        End point Put, /loans
        Vet inte vad menas
        //the loan had id set ?
    
     */
    @Test
    public void testPutLoanIdSet_StatusCode400() {

    }

    /*
        End point Put, /loans
        //Get all loan
        //uppdate Date of Borrowed with null
        //Update the loan
        // jag uppdaterar date of borrowed with null /0 , men det gick och uppdatera den
        // Bugg
        //
     */
    @Test
    @Ignore
    public void testPutLoanHadNoDateBorrowedSet_StatusCode400() {

        Response responseGet = new LoanRestTestClient().getAllLoan();
        assertEquals("Status code should be 200 ", 200, responseGet.statusCode());

        Loans allloan = responseGet.jsonPath().getObject("loans.loan", Loans.class);
        Assert.assertNotNull(allloan);

        Loan loan = allloan.get(0);
        loan.setDateBorrowed(null);

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();
        Response responsePut = loanRestTestClient.putLoan(new SingleLoan(loan));

        System.out.println("Loan är inte skapad eftersom Date of borrowed is not set " + responsePut.statusCode());
        assertEquals("Status code should be 400", 400, responsePut.statusCode());

    }

    /*
        End point Put, /loans
    
        //Get all loan
        //uppdate Date of Due with null
        //Update the loan
        // jag uppdaterar date of due with null /0 , men det gick  uppdatera loan
    
        potential bugg
        Det går update loan faste date of due är set till null
     */
    @Test
    @Ignore
    public void testPutLoanHadNoDateOfDueSet_StatusCode400() {

        Response responseGet = new LoanRestTestClient().getAllLoan();
        assertEquals("Status code should be 200 ", 200, responseGet.statusCode());

        Loans allloan = responseGet.jsonPath().getObject("loans.loan", Loans.class);
        Assert.assertNotNull(allloan);

        Loan loan = allloan.get(0);
        loan.setDateDue(null);

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();
        Response responsePut = loanRestTestClient.putLoan(new SingleLoan(loan));

        System.out.println("Loan är inte skapad eftersom Date of borrowed is not set " + responsePut.statusCode());
        assertEquals("Status code should be 400", 400, responsePut.statusCode());

    }

    /*
        End point Put , /loans
        //Get all loan        
        //Create a random user och verifiera att den är skpad
        //change the user of first loan in the loanlista
        //Update loan with new user, verifiera att det går inte uppdatera loan with new user
     */
    @Test
    public void testPutLoanWithUserChanged_StatusCode400() {

        Response responseGet = new LoanRestTestClient().getAllLoan();
        assertEquals("Status code should be 200 ", 200, responseGet.statusCode());

        Loans allloan = responseGet.jsonPath().getObject("loans.loan", Loans.class);
        Assert.assertNotNull(allloan);

        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Loan loan = allloan.get(0);

        loan.setUser(user);

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();
        Response responsePut = loanRestTestClient.putLoan(new SingleLoan(loan));

        System.out.println("Loan är inte updatera loan med en annan user is not set " + responsePut.statusCode());
        assertEquals("Status code should be 400", 400, responsePut.statusCode());

    }

    /*
         End point Put, /loans
         get all loan
         Create new book
         Change the book of frist loan i loan lista med new book
         verifiera att det går inte updatera loan with new book
     */
    @Test
    public void testPutLoanWithBookChanged_StatusCode400() {
        Response responseGet = new LoanRestTestClient().getAllLoan();
        assertEquals("Status code should be 200 ", 200, responseGet.statusCode());

        Loans allloan = responseGet.jsonPath().getObject("loans.loan", Loans.class);
        Assert.assertNotNull(allloan);

        Book book = new BookRestTestClient().createRandomBook();
        Assert.assertNotNull(book);

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        Loan loan = allloan.get(0);
        loan.setBook(book);

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();
        Response responsePut = loanRestTestClient.putLoan(new SingleLoan(loan));

        System.out.println("Loan går inte updatera loan med en annan book " + responsePut.statusCode());
        assertEquals("Status code should be 400", 400, responsePut.statusCode());

    }

    /*
        End point /loans
        updatera loanBut loan not found
    
        Create random book, Post it and verifiera att den är skapat
        Create random user, post it and verifiera att den är skapat
    
     */
    @Test
    public void testPutLoanNotFound_StatusCode404() {

        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestTestClient().createRandomBook();
        Assert.assertNotNull(book);

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);

        Response responsePut = loanRestTestClient.putLoan(new SingleLoan(loan));

        System.out.println("Det går inte hita loan:  " + responsePut.statusCode());
        assertEquals("Status code should be 404", 404, responsePut.statusCode());

    }

    /*
        End point Get, /loans/id
     */
    @Test
    public void testGetLoanNotFound_StatusCode_404() {

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Response responseGet = new LoanRestTestClient().getLoan(id);
        assertEquals("Status code should be 404 ", 404, responseGet.statusCode());

    }

    /*
        End point Get, /loans/id
     */
    @Test
    public void testDeleteLoanNotFound_StatusCode_404() {

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Response responsedelete = new LoanRestTestClient().deleteLoan(id);
        assertEquals("Status code should be 404 ", 404, responsedelete.statusCode());
    }

    /*
        End point Get /loans/ofuser/ {user id}
     */
    @Test
    public void testGetLoanOfSpecifiedUserNotFound_StatusCode404() {

        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responseGet = new LoanRestTestClient().getLoanOfUser(user.getId());
        assertEquals("Status code should be 404 ", 404, responseGet.statusCode());
    }

    /*
        End point Get /loans/ofuser/ {user id}
     */
    @Test
    public void testGetLoanOfSpecifiedUserLoanNotFound_StatusCode404() {

        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestTestClient().createRandomBook();
        Assert.assertNotNull(book);

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);

        Response responseGet = new LoanRestTestClient().getLoanOfUser(user.getId());
        assertEquals("Status code should be 404 ", 404, responseGet.statusCode());
    }

    /*
        End point Get /loans/ofbook/ {book id}
        Create a randomBook and post it, verifiera att booken finns
        Get the loan of book
        Verifiera att booken har ingen loan genom att retunera statusCode 404
     */
    @Test
    public void testGetLoanOfSpecifiedBookNotFound_StatusCode404() {

        Book book = new BookRestTestClient().createRandomBook();
        Assert.assertNotNull(book);

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        Response responseGetBook = new BookRestTestClient().getBook(book.getId());
        assertEquals("Status code should be 200 ", 200, responseGetBook.statusCode());

        Response responseGet = new LoanRestTestClient().getLoanOfBook(book.getId());
        assertEquals("Status code should be 404 ", 404, responseGet.statusCode());
    }

    /*
        End point Get /loans/ofuser/{user_id}/ofbook/ {book id}
        Get loan of specified book by specified user, the book not found
    
        Create a randomBook and post it, verifiera att booken finns
        Get the loan of book
        Verifiera att booken har ingen loan genom att retunera statusCode 404
     */
    @Test
    public void testGetLoanOfSpecifiedBookBySpecifiedUserBookNotFound_Status404() {

        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestTestClient().createRandomBook();
        Assert.assertNotNull(book);

        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);

        Response responseGet = new LoanRestTestClient().getLoanOfBookByUser(new SingleLoan(loan));
        assertEquals("Status code should be 404 ", 404, responseGet.statusCode());
    }

    /*
        End point Get /loans/ofuser/{user_id}/ofbook/ {book id}
        
        The book and user were found but the were no loans of the book by the user
    
        Create a randomBook and post it, verifiera att booken finns
        Get the loan of book
        Verifiera att booken har ingen loan genom att retunera statusCode 404
     */
    @Test
    public void testGetLoanOfSpecifiedBookBySpecifiedUserLoanNotFound_Status404() {

        User user = new UserRestTestClient().createRandomUser();
        Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        Book book = new BookRestTestClient().createRandomBook();
        Assert.assertNotNull(book);

        Response responsePostBook = new BookRestTestClient().createBook(new SingleBook(book));
        assertEquals("Status code should be 201 ", 201, responsePostBook.statusCode());

        Response responseGetBook = new BookRestTestClient().getBook(book.getId());
        assertEquals("Status code should be 200 ", 200, responseGetBook.statusCode());

        Response responseGetUser = new UserRestTestClient().getUser(user.getId());
        assertEquals("Status code should be 200 ", 200, responseGetUser.statusCode());
        
         LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Loan loan = new Loan(id, book, dateBorrowedStr, dateDueStr, user);

         Response responseGet = new LoanRestTestClient().getLoanOfBookByUser(new SingleLoan(loan));
        assertEquals("Status code should be 404 ", 404, responseGet.statusCode());

    }
}
