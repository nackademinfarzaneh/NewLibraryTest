package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import se.nackademin.librarytest.librarytestsystem.model.Authors;
import se.nackademin.librarytest.librarytestsystem.model.Book;
import se.nackademin.librarytest.librarytestsystem.model.SingleBook;

import com.jayway.restassured.response.Response;

import java.io.IOException;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import se.nackademin.librarytest.librarytestsystem.model.Author;
import se.nackademin.librarytest.librarytestsystem.model.Loan;
import se.nackademin.librarytest.librarytestsystem.model.SingleLoan;
import se.nackademin.librarytest.librarytestsystem.model.SingleUser;
import se.nackademin.librarytest.librarytestsystem.model.User;

/**
 *
 * @author testautom-nack
 */
public class BookRestServiceTest {

    /*
        Endpoint /books
     */
    @Test
    public void testGetAllBooks() throws IOException {

        Response response = new BookRestTestClient().getAllBooks();
        assertEquals("Status code should be 200", 200, response.statusCode());
    }

    /*
        Endpoint /books , Post
        Skapa en random book, post boken, verifiera att boken är skapat
     */
    @Test
    public void testCreateBook() throws ParseException {

        BookRestTestClient restTestClient = new BookRestTestClient();

        Book book = restTestClient.createRandomBook();
        Response response = restTestClient.createBook(new SingleBook(book));

        assertEquals("Status code should be 201", 201, response.statusCode());
        System.out.println("new book " + response.statusCode());
    }

    /*
        Endpoint: PUt /books
         create a randombook , post it to DB , verifiera att book är skapad i DB
         uppdatera boken med ny data, put the data to DB, verifiera att boken ärupdaterat.   
     */
    @Test
    public void testUpdateBook() {

        BookRestTestClient bookRestTestClient = new BookRestTestClient();

        Book book = bookRestTestClient.createRandomBook();
        Assert.assertNotNull(book);

        Response responsPost = bookRestTestClient.createBook(new SingleBook(book));
        assertEquals("Status code should be 201", 201, responsPost.statusCode());

        String description = UUID.randomUUID().toString().substring(1, 5);
        String isbn = UUID.randomUUID().toString().substring(1, 5);

        book.setDescription(description);
        book.setIsbn(isbn);

        Response responsePut = bookRestTestClient.putBook(new SingleBook(book));
        assertEquals("Status code should be 200", 200, responsePut.statusCode());
    }

    /*
        Hämta alla böcker från DB
        Endpoint: /books/{id}   
     */
    @Test
    public void testGetBookById() {

        Response response = new BookRestTestClient().getBook(5);
        assertEquals("Status code should be 200", 200, response.statusCode());
    }

    /*
        Endpoint: /books/{id}
        Delete the book whit the specified id. This also deletes all loans of this book.
        
        Create random book, verifiera att booken är skapad
        Create user för the loan
        create loan för the book  and verifiera att loan är skapad
        delete the book, verifiera att booken finns inte
       
        get the loan of the book and verifiera att den finns inte
     */
    @Test
    public void testDeleteBookById() {

        BookRestTestClient restTestClient = new BookRestTestClient();

        //Create a book      
        Book book= restTestClient.createRandomBook();   
        Response response = restTestClient.createBook(new SingleBook(book));
      
        System.out.println("New books id är: " + book.getId() + " och statusCode är: " + response.statusCode());
        assertEquals("Status code should be 201", 201, response.statusCode());

        //Create a user
        User user = new UserRestTestClient().createRandomUser();
        org.junit.Assert.assertNotNull(user);

        Response responsePostUser = new UserRestTestClient().createUser(new SingleUser(user));
        assertEquals("Status code should be 201 ", 201, responsePostUser.statusCode());

        //Create a loan
        LoanRestTestClient loanRestTestClient = new LoanRestTestClient();

        String dateBorrowedStr = loanRestTestClient.barrowRandomDate();
        String dateDueStr = loanRestTestClient.dueRandomDate();

        Loan loan = new Loan(book, dateBorrowedStr, dateDueStr, user);
        Response responsePost = loanRestTestClient.createLoan(new SingleLoan(loan));

        System.out.println("new loan är skapad " + responsePost.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());

        //get the loan of the book
        Response responseGetLoan = loanRestTestClient.getLoanOfBook(book.getId());
        assertEquals("Status code should be 200", 200, responseGetLoan.statusCode());

        Response responseDeleteBookWithUser = new BookRestTestClient().deleteBook(book.getId());
        // Response responseDeleteBookWithUser = new BookRestTestClient().deleteBook(235);

        System.err.println("Book med id " + book.getId() + " är delete från databsen och status kod är:  " + responseDeleteBookWithUser.statusCode());
        assertEquals("Status code should be 204", 204, responseDeleteBookWithUser.statusCode());

        //get the loan of the book and verifiera att den finns inte
        Response responseGetLoan2 = loanRestTestClient.getLoanOfBook(book.getId());
        assertEquals("Status code should be 404", 404, responseGetLoan2.statusCode());
    }

    /**
     * Endpoint : /books/byauthor/{author id} //Get all books by the specified
     * author
     */
    @Test
    public void testgetAllBooksBySpecifiedAuthor() {

        Response response = new BookRestTestClient().getAllBooksBySpecificAuthor(4);
        assertEquals("Status code should be 200", 200, response.statusCode());
    }

    /**
     * Endpoint : /books/{book_id}/authorsBook Get the authors of the specified
     * book
     */
    @Test
    public void testgetAuthorsOfSpecifiedBook() {

        Response response = new BookRestTestClient().getAuthorsOfSpecifiedBook(6);
        assertEquals("Status code should be 200", 200, response.statusCode());
    }

    /*
        Endpoint: Post /books/{book_id}/authors
        Add an author to the specified book
     */

    @Test
    @Ignore
    public void testAddAuthorToSpecifiedBook() { //fungerar ej //när jag lägger till en author till en book. rubriken för authror lista blir authros 
        //create a randomBook
        //verifiera att booken är skapad
        //get the book
        //verifiera att booken är get
        //Get the all author 
        // lägg en specifick author to det authorsBook list of the book
        // anropa post metoden

        BookRestTestClient restTestClient = new BookRestTestClient();

        Book book = restTestClient.createRandomBook();
        Assert.assertNotNull(book);

        Response response = restTestClient.createBook(new SingleBook(book));
        assertEquals("Status code should be 201", 201, response.statusCode());

        Response responsGetAllaAuthors = new AuthorRestTestClient().getAllAuthor();
        assertEquals("Status code should be 200", 200, responsGetAllaAuthors.statusCode());

        Authors allAuthors = responsGetAllaAuthors.jsonPath().getObject("authors.author", Authors.class);
        Assert.assertNotNull(allAuthors);
        
        book.setAuthor(allAuthors.get(0));
      
        Response responsePost = restTestClient.addAuthorToSpecifiedBook(book.getId(), new SingleBook(book));
        assertEquals("status kod must be 201", 201, responsePost.statusCode());

    }

    //Update a books list of authors with a new list of authors
    @Test
    @Ignore
    public void testUppdateAuthorListOfSpecifiedBook() {

        //create a randomBook
        //verifiera att booken är skapad
        //get the book
        //verifiera att booken är get
        //Get the all author 
        BookRestTestClient restTestClient = new BookRestTestClient();

        Book book = restTestClient.createRandomBook();

        Response response = restTestClient.createBook(new SingleBook(book));
        assertEquals("Status code should be 201", 201, response.statusCode());


        Response responsGetAllaAuthors = new AuthorRestTestClient().getAllAuthor();
        assertEquals("Status code should be 200", 200, responsGetAllaAuthors.statusCode());

        Authors allAuthors = responsGetAllaAuthors.jsonPath().getObject("authors.author", Authors.class);
        Assert.assertNotNull(allAuthors);
        book.setAuthor(allAuthors.get(0));

        Response responsePut = restTestClient.uppdateAuthorOfSpecifiedBook(book.getId(), new SingleBook(book));
        assertEquals("status kod must be 200", 200, responsePut.statusCode());

    }
}
