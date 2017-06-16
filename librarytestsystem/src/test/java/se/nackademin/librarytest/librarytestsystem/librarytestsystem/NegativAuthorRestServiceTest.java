/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import se.nackademin.librarytest.librarytestsystem.model.SingleAuthor;
import se.nackademin.librarytest.librarytestsystem.model.Author;
import se.nackademin.librarytest.librarytestsystem.model.Book;
import se.nackademin.librarytest.librarytestsystem.model.SingleBook;
import com.jayway.restassured.response.Response;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author testautom-nack
 */
public class NegativAuthorRestServiceTest {

    @Test
    public void authorsIdExistInDB_StatusCode400() {

        //create a Random authors
        //försök create a new authors with samma id
        //
        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();

        Author author = authorRestTestClient.createRandomAuthor();
        int authorID = author.getId();

        Response response = authorRestTestClient.createAuthor(new SingleAuthor(author));

        assertEquals("Status code should be 201", 201, response.statusCode());
        System.out.println("status code should be 201:  " + response.statusCode());

        Response response2 = authorRestTestClient.createAuthor(new SingleAuthor(author));
        assertEquals("Status code should be 400 ", 400, response2.statusCode());
        System.out.println("status code should be 400  :" + response2.statusCode());
    }

    @Test
    public void testCreatAuthorWithNoFirstName_StatusCode400() {

        //create a Random authors
        //försök create a new authors with samma id
        //
        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();

        Author author = authorRestTestClient.createRandomAuthor();
        String firstName = author.getFirstName();
        author.setFirstName(null);

        Response responsePostAuthor = authorRestTestClient.createAuthor(new SingleAuthor(author));
        assertEquals("Status code should be 400 ", 400, responsePostAuthor.statusCode());
        System.out.println("status code should be 400  :" + responsePostAuthor.statusCode());
    }

    @Test
    public void testCreatAuthorWithNoLastName_StatusCode400() {

        //create a Random authors
        //försök create a new authors with samma id
        //
        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();

        Author author = authorRestTestClient.createRandomAuthor();
        String firstName = author.getFirstName();
        author.setLastName(null);

        Response response2 = authorRestTestClient.createAuthor(new SingleAuthor(author));
        assertEquals("Status code should be 400 ", 400, response2.statusCode());
        System.out.println("status code should be 400  :" + response2.statusCode());
    }

    @Test
    public void testUpdateAuthorWithNoAuthorsFirstName_StatusCode400() {

        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();
        
        Author author = authorRestTestClient.createRandomAuthor();    
        
        Response responsePostAuthor = authorRestTestClient.createAuthor(new SingleAuthor(author));
        assertEquals("Status code should be 201 ", 201, responsePostAuthor.statusCode());

        author.setFirstName(null);
        Response response = authorRestTestClient.putAuthor(new SingleAuthor(author));

        System.out.println("Status koden ska vara 400: " + response.statusCode());
        assertEquals("Boken som du föröker uppdatera har ingen title", 400, response.statusCode());
    }

    @Test
    public void testUpdateAuthorWithNoAuthorsLastName_StatusCode400() {

        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();
        Author randomAuthor = authorRestTestClient.createRandomAuthor();        
        
        Response responsePostAuthor = authorRestTestClient.createAuthor(new SingleAuthor(randomAuthor));
        assertEquals("Status code should be 201 ", 201, responsePostAuthor.statusCode());
        
        randomAuthor.setLastName(null);
;
        Response response = authorRestTestClient.putAuthor(new SingleAuthor(randomAuthor));

        System.out.println("Status koden ska vara 400: " + response.statusCode());
        assertEquals("Boken som du föröker uppdatera har ingen title", 400, response.statusCode());
    }

    @Test
    public void testAuthorsGetNotFound_StatusCode404() {
        //creat an random id
        //get an author med random id        
        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;

        Response response = new AuthorRestTestClient().getAuthor(id);
        assertEquals("Status code should be 404", 404, response.statusCode());
    }

    @Test
    public void testAuthorsDeleteNotFound_StatusCode404() {

        //Create an random author
        //Bekräfta att den är skapat
        //Deleta Author
        //Bekräfta att den finns inte i DB
        //Hämta Author
        //Bekräfta att den finns inte
        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();
        Author author = authorRestTestClient.createRandomAuthor();

        Response response = authorRestTestClient.createAuthor(new SingleAuthor(author));
        assertEquals("Status code should be 201", 201, response.statusCode());

        Response deleateResponse = authorRestTestClient.deleteAuthor(author.getId());
        assertEquals("Status code should be 204", 204, deleateResponse.statusCode());

        Response getResponse = new AuthorRestTestClient().getAuthor(author.getId());
        assertEquals("Status code should be 404", 404, getResponse.statusCode());
        System.out.println(response.statusCode());
    }

    @Test
    public void testDeleteAuthorWithBookInDB_StatusCode409() {

        //Create an Random author
        //create a book with the author
        //delete the author
        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();
        Author author = authorRestTestClient.createRandomAuthor();

        Response response = authorRestTestClient.createAuthor(new SingleAuthor(author));
        assertEquals("Status code should be 201", 201, response.statusCode());

        BookRestTestClient restTestClient = new BookRestTestClient();

        Book book = restTestClient.createRandomBook();
        book.setAuthor(author);

        Response responsePost = restTestClient.createBook(new SingleBook(book));

        System.out.println("new book " + response.statusCode());
        assertEquals("Status code should be 201", 201, responsePost.statusCode());

        Response deleateResponse = authorRestTestClient.deleteAuthor(author.getId());
        assertEquals("Status code should be 204", 409, deleateResponse.statusCode());

    }

}
