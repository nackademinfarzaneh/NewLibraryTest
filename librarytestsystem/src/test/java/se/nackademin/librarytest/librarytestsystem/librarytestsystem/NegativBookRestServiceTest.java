/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import se.nackademin.librarytest.librarytestsystem.model.Authors;
import se.nackademin.librarytest.librarytestsystem.model.Author;
import se.nackademin.librarytest.librarytestsystem.model.Book;
import se.nackademin.librarytest.librarytestsystem.model.SingleBook;
import com.jayway.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;
import se.nackademin.librarytest.librarytestsystem.model.SingleAuthor;

/**
 *
 * @author testautom-nack
 */
public class NegativBookRestServiceTest {

    // * Endpoint: /books
    //-----------------------------    
    //The books id was already i the database
    @Test
    public void testCreateBookWithIDInDB_StusCode400() {
        //Det book with id = 5 finns i databasen

        //Get a book from DB 
        // verifiera att booken finns
        //get det books id
        //create a book with samma id
        //verifiera att det går inte att skapa boken        
        BookRestTestClient restTestClient = new BookRestTestClient();

        Response responseGetBook = restTestClient.getBook(5);
        assertEquals("Status code should be 200", 200, responseGetBook.statusCode());

        Book book = restTestClient.createRandomBook();
        book.setId(5);
        Response response = restTestClient.createBook(new SingleBook(book));

        System.out.println("Status koden ska vara 400: " + response.statusCode());
        assertEquals("En book med samma id finns redan i databasen,", 400, response.statusCode());
    }

    // * Endpoint: /books
    //-----------------------------    
    // Create a book with no title (the book had no title set)
    @Test
    public void testCreateBookWithNoTitle_StusCode400() {

        //create a random book
        //set bookens title till null
        //posta den 
        // verifiera att det går inte skapa booken
        BookRestTestClient restTestClient = new BookRestTestClient();

        Book book = restTestClient.createRandomBook();
        book.setTitle(null);

        Response response = restTestClient.createBook(new SingleBook(book));
        assertEquals("Boken som du föröker skapa har ingen title", 400, response.statusCode());
        System.out.println("Status koden ska vara 400: " + response.statusCode());
    }

    // * Endpoint: /books
    //-----------------------------    
    //The book contained an author with no id field set
    @Test
    public void testCreateBookWithNoAuthorsID_StusCode400() {
        //Det book with id = 5 finns i databasen    
        //create book withNoAuthor
        //Create an Author with no AuthorID
        //add author to book
        //verifiera att det går inte addera en author with no ID to book

        BookRestTestClient restTestClient = new BookRestTestClient();
        Book book = restTestClient.createRandomBookWithNoAuthor();

        Author author = restTestClient.createRandomAuthor();
        author.setId(null);
        book.setAuthor(author);

        SingleBook postSingleBook = new SingleBook(book);
        Response response = restTestClient.createBook(postSingleBook);

        System.out.println("Status koden ska vara 400: " + response.statusCode());
        assertEquals("Boken som du föröker skapa har ingen title", 400, response.statusCode());
    }

    // * Endpoint: /books
    //-----------------------------    
    //the book contained an author that didn't exist in the database
    @Test
    public void testCreateBookWithNoAuthorsNameInDB_StusCode400() {

        //create a random book 
        //set an author to the book 
        //the author dose not exist in the database befor post the book 
        BookRestTestClient restTestClient = new BookRestTestClient();
        Book book = restTestClient.createRandomBookWithNoAuthor();

        Author author = restTestClient.createRandomAuthor();
        book.setAuthor(author);

        SingleBook postSingleBook = new SingleBook(book);
        Response response = restTestClient.createBook(postSingleBook);

        System.out.println("Status koden ska vara 400: " + response.statusCode());
        assertEquals("Author för boken som du föröker skapa finns inte i DB", 400, response.statusCode());
    }

    // * Endpoint: /books
    //-----------------------------    
    //uppdate the book had no title set
    @Test
    public void testUpdateBookWithoutTitle_StusCode400() {

        BookRestTestClient restTestClient = new BookRestTestClient();

        Book book = restTestClient.createRandomBook();
         Response responsePostBook = restTestClient.createBook(new SingleBook(book));
        assertEquals("Status code should be 201", 201, responsePostBook.statusCode());
        book.setTitle(null);

        Response response = restTestClient.putBook(new SingleBook(book));
        assertEquals("Boken som du föröker uppdatera har ingen title", 400, response.statusCode());
        System.out.println("Status koden ska vara 400: " + response.statusCode());
    }

    // * Endpoint: /books
    //-----------------------------    
    // uppdate the book contained an author with no id field set
    @Test
    public void testUpdateBookWithNoAuthorsID_StusCode400() {
        //create a random book            
        //create an author with no id
        //add the author to the book
        //uppdate the book 
        //verifiera att the går inte

        BookRestTestClient restTestClient = new BookRestTestClient();
        Book book = restTestClient.createRandomBookWithNoAuthor();
        Response responsePostBook = restTestClient.createBook(new SingleBook(book));
        assertEquals("Status code should be 201", 201, responsePostBook.statusCode());

        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();
        Author author = restTestClient.createRandomAuthor();
         Response responsePostAuthor = authorRestTestClient.createAuthor(new SingleAuthor(author));

        System.out.println("new book " + responsePostAuthor.statusCode());
        assertEquals("Status code should be 201", 201, responsePostAuthor.statusCode());
        author.setId(null);

        book.setAuthor(author);
        Response response = restTestClient.putBook(new SingleBook(book));
        assertEquals("Boken som du föröker uppdater har ingen id", 400, response.statusCode());
        System.out.println("Status koden ska vara 400: " + response.statusCode());
    }

    // * Endpoint: /books
    //-----------------------------    
    //Uppdate the book contained an author that didn't exist in the database 
    @Test
    @Ignore
    public void testUpdateBookWithNoAuthorsNameInDB_StusCode400() {

        //create a random book 
        //post the book 
        //set an author to the book 
        //the author dose not exist in the database befor uppdatein the book 
        BookRestTestClient restTestClient = new BookRestTestClient();

        Book book = restTestClient.createRandomBook();
        //       Response responsePost = restTestClient.postBook(book.getId());           
        //        assertEquals("Status code should be 201", 201, responsePost.statusCode());    

        //       Response responsePost = restTestClient.createBook(new SingleBook(book));
        //       assertEquals("Status code should be 201", 201, responsePost.statusCode());
        book.getAuthors().clear();

        Author author = restTestClient.createRandomAuthor();
        book.setAuthor(author); //.setAuthors(author);

        SingleBook postSingleBook = new SingleBook(book);

        Response response = restTestClient.putBook(postSingleBook);

        System.out.println("Status koden ska vara 400: " + response.statusCode());
        assertEquals("Author för boken som du föröker skapa finns inte i DB", 400, response.statusCode());

//        BookRestTestClient restTestClient = new BookRestTestClient();
//        SingleBook randomSingleBook = restTestClient.createRandomSingleBook();
//        Response responsePost = restTestClient.createBook(randomSingleBook);
//        assertEquals("Status code should be 201", 201, responsePost.statusCode());
//        Book randomBook = randomSingleBook.getBook();
//        //   Author author = book.getAuthor();
//        //   author.setFirstName("Farzaneh");
//        // book.setAuthor(author);
//        List<Author> randomAuthor = randomBook.getAuthors();
//        Author author = new Author(0, "farzaneh", "yousofi", "iran", "första bok");
//        randomAuthor.set(0, author);
//        randomBook.setAuthors(randomAuthor);
//        SingleBook putSingleBook = new SingleBook(randomBook);
//        Response response = restTestClient.putBook(putSingleBook);
//
//        System.out.println("Status koden ska vara 400: " + response.statusCode());
//        assertEquals("Författare för boken som du föröker skapa finns inte i DB", 400, response.statusCode());
    }

    // * Endpoint: /books
    //-----------------------------    
    //Book was not found
    @Test
    public void testGetBookByIdNotfound_StatusCode404() {
        //create an random id
        //get det book
        //verifiera att booken finns inte i DB

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Response response = new BookRestTestClient().getBook(id);
        assertEquals("Status code should be 404", 404, response.statusCode());
    }

    /*
       Endpoint: /books/{id}  
        //create randombook, verifiera att booken är skapad
        //deleat Book, verifiera att booken är delete
        //koll att den finns inte
        //Get the book, verifiera att booken finns inte
     */

    @Test
    public void testDeleteBookByIdNotFound_StusCode404() {

        BookRestTestClient restTestClient = new BookRestTestClient();

        Book book = restTestClient.createRandomBook();
        Response response = restTestClient.createBook(new SingleBook(book));
        assertEquals("Status code should be 201", 201, response.statusCode());

        System.out.println("new book " + response.statusCode());

        Response deleteResponse = new BookRestTestClient().deleteBook(book.getId());
        assertEquals("Status code should be 204", 204, deleteResponse.statusCode());
        System.out.println("status kod är  " + deleteResponse.statusCode());

        Response getresponse = new BookRestTestClient().getBook(book.getId());
        assertEquals("Status code should be 404", 404, getresponse.statusCode());
        System.out.println("status kod är 404 " + deleteResponse.statusCode());

    }

    // * Endpoint: /books/{id}
    // --------------------------------- 
    //uppdate the book which one of the authors din not have the id filed set
    // uppdate the book contained an author with no id field set
    @Test
    public void testUpdateAuthorsListOfBookWithNoAuthorsIDForOneAuthor_StusCode400() {
        //create a random book            
        //create an author with no id
        //add the author to the book
        //uppdate the book 
        //verifiera att the går inte

        BookRestTestClient restTestClient = new BookRestTestClient();

        Book book = restTestClient.createRandomBook();
        Response responsePostBook = restTestClient.createBook(new SingleBook(book));

        System.out.println("New books id är: " + book.getId() + " och statusCode är: " + responsePostBook.statusCode());
        assertEquals("Status code should be 201", 201, responsePostBook.statusCode());

        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();
        Author author = authorRestTestClient.createRandomAuthor();

        Response responsePostAuthor = authorRestTestClient.createAuthor(new SingleAuthor(author));
        assertEquals("Status code should be 201", 201, responsePostAuthor.statusCode());

        author.setId(null);
        book.setAuthor(author);
        Response response = restTestClient.putBook(new SingleBook(book));

        System.out.println("Status koden ska vara 400: " + response.statusCode());
        assertEquals("Boken som du föröker uppdater har ingen id", 400, response.statusCode());
    }

    //*  En point /books /{book_id} /authors
    //------------------------------------------
    @Test
    public void testGetAuthorOfSpecifiedBookNotFound_StatusCoce404() {
        //

        BookRestTestClient bookRestTestClient = new BookRestTestClient();

        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;
        Response responseGet = bookRestTestClient.getAuthorsOfSpecifiedBook(id);

        assertEquals("Status code shoud be 404", 404, responseGet.statusCode());

    }

    //*  En point /books /{book_id} /authors
    //------------------------------------------
    @Test
    public void testAddAuthorTospecifiedBookWithNoAuthorsID_StusCode400() {

        //create a randombook 
        //Create an random author, post till DB
        //bekräfta att booken finns
        //get the 
    }

    //*  En point /books /{book_id} /authors
    //------------------------------------------
    @Test
    public void testAddAuthorTospecifiedBook_StusCode400() {

    }

    //*  En point /books /{book_id} /authors
    //------------------------------------------
    @Test
    public void testAddAuthorToSpecifiedBookNotFound_StusCode404() {

    }

    //*  En point /books /{book_id} /authors
    //------------------------------------------
    @Test
    public void testUpdateBookWithAuthorListToSpecifiedBookWithNoIDForOneAuthor_StusCode400() {

    }
    //*  En point /books /{book_id} /authors
    //------------------------------------------

    @Test
    public void testUpdateBookWithAuthorListToSpecifiedBookAuthorExistInTheListOfBook_StusCode400() {

    }

    //*  En point /books /{book_id} /authors
    //------------------------------------------
    @Test
    public void testUpdateSpecifiedBookWithAuthorListBookNotFound_StusCode404() {

    }

}
