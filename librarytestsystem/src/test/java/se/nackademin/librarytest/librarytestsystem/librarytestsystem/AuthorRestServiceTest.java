/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import se.nackademin.librarytest.librarytestsystem.model.SingleAuthor;
import se.nackademin.librarytest.librarytestsystem.model.Author;
import com.jayway.restassured.response.Response;
import java.io.IOException;
import java.text.ParseException;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author testautom-nack
 */
public class AuthorRestServiceTest {

    @Test
    @Ignore
    public void testDeleteAuthor() {

        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();
        Response deleateResponse = authorRestTestClient.deleteAuthor(170);

        assertEquals("Status code should be 204", 204, deleateResponse.statusCode());

    }

    /*
         Endpoint:  Get /authors
         Hämta alla author från databasen
     */
    @Test
    public void testGetAllAuthors() throws IOException {

        Response response = new AuthorRestTestClient().getAllAuthor();
        assertEquals("Status code should be 200", 200, response.statusCode());
        System.out.println(response.statusCode());
    }

    /*
        Endpint: post/authors
        Create a random author, post author och verifiera att author är skapat.
     */
    @Test
    public void testCreateAuthor() throws ParseException {

        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();
        Author author = authorRestTestClient.createRandomAuthor();

        Response response = authorRestTestClient.createAuthor(new SingleAuthor(author));

        System.out.println("new book " + response.statusCode());
        assertEquals("Status code should be 201", 201, response.statusCode());
    }

    /*
         // Endpint: PUT/authors
         Create a random author. Posti author och verifiera att den är skapat
         Updatera authorsName med ny namn. Verifiera att author är uppdaterat         
     */
    @Test
    public void testUpdateAuthor() {

        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();

        AuthorRestTestClient authorRestTestClient1 = new AuthorRestTestClient();
        Author author2 = authorRestTestClient1.createRandomAuthor();

        Response responsePost = authorRestTestClient.createAuthor(new SingleAuthor(author2));
        assertEquals("Status code should be 201", 201, responsePost.statusCode());
        author2.setFirstName("Farzaneh");

        Response responsePut1 = authorRestTestClient.putAuthor(new SingleAuthor(author2));
        assertEquals("Status code should be 200", 200, responsePut1.statusCode());

    }

    /*
        endpoint /authors/{id}  
        Hämtade author med id=5
        verifiera att den är hämtad
     */

    @Test
    public void testGetAuthorWithSpecifiedID() {

        Response response = new AuthorRestTestClient().getAuthor(5);
        Author author = response.jsonPath().getObject("author", Author.class);

        Assert.assertNotNull(author);
        assertEquals("Status code should be 200", 200, response.statusCode());
        System.out.println(" Det author id som vi hämtade är " + author.getId() + " " + "Status code should be: " + response.statusCode());
    }

    /*
        End point Delete, /authors/{id}
        //create en random author
        //kolla att den är skapad statusCode 201
        //Deleta den från databasen 
        // bekräfta att den är deletat statusCod 204
     */

    @Test
    public void testDeleteAuthorWithSpecifiedID() {

        AuthorRestTestClient authorRestTestClient = new AuthorRestTestClient();
        Author author = authorRestTestClient.createRandomAuthor();

        SingleAuthor singleAuthor = new SingleAuthor(author);
        singleAuthor.getAuthor();
        Response response = authorRestTestClient.createAuthor(singleAuthor);
        assertEquals("Status code should be 201", 201, response.statusCode());

        Response deleateResponse = authorRestTestClient.deleteAuthor(author.getId());

        assertEquals("Status code should be 204", 204, deleateResponse.statusCode());
        System.out.println("Author with id " + author.getId() + " is delete.");

    }

}
