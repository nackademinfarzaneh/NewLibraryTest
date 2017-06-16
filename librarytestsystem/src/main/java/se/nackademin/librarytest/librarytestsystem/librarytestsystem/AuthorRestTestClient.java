/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import se.nackademin.librarytest.librarytestsystem.model.SingleAuthor;
import se.nackademin.librarytest.librarytestsystem.model.Author;
import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author testautom-nack
 */
public class AuthorRestTestClient {

    private static final String restUrl = "http://localhost:8080/librarytest-rest/";

    public Author createRandomAuthor() {

        Random Randomizer = new Random();

        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;
        String bio = UUID.randomUUID().toString();
        String country = UUID.randomUUID().toString().substring(1, 5);;
        String firstName = UUID.randomUUID().toString().substring(1, 5);
        String lastName = UUID.randomUUID().toString().substring(1, 5);

        Author author = new Author(id, firstName, lastName, country, bio);
        return author;
    }

    public SingleAuthor updateRandomSingleAuthor(Author author) {
        
        Random Randomizer = new Random();

        String firstNameRandom = UUID.randomUUID().toString().substring(1, 5);

        String bio = "Sri Lankabhimanya Sir Arthur Charles Clarke, CBE, FRAS (16 December 1917 – 19 "
                + "March 2008) was a British science fiction writer, science writer and futurist,"
                + " inventor, undersea explorer, and television series host.He is perhaps most"
                + " famous for being co-writer of the screenplay for the movie 2001: A Space Odyssey,"
                + " widely considered to be one of the most influential films of all time. "
                + "His other science fiction writings earned him a number of Hugo and Nebula awards,"
                + " which along with a large readership made him one of the towering figures of science fiction.";
        String country = author.getCountry();
        String firstName = author.getFirstName();
        String lastName = author.getLastName();

        Author author2 = new Author(4, firstNameRandom, lastName, country, bio);
        return new SingleAuthor(author2);

    }

    public Response putAuthor(SingleAuthor singleAuthor) {
        Response response = resoursPutter("authors", singleAuthor);
        return response;
    }

    private static Response resoursPutter(String resourceName, SingleAuthor singleAuthor) {
        Response response = given().contentType(ContentType.JSON).body(singleAuthor).log().all().put(restUrl + resourceName).prettyPeek();
        return response;
    }

    public Response createAuthor(SingleAuthor singleAuthor) {
        Response response = resoursCreator("authors", singleAuthor);
        return response;
    }

    private static Response resoursCreator(String resourceName, SingleAuthor singleAuthor) {
        Response response = given().contentType(ContentType.JSON).body(singleAuthor).log().all().post(restUrl + resourceName);
        return response;
    }

    public Response getAuthor(int id) {
        String resourceame = "authors/" + id;
        Response response = resoursGetter(resourceame);
        return response;
    }

    Response getAllAuthor() {
        Response response = resoursGetter("authors");
        return response;
    }

    private static Response resoursGetter(String resourceName) {
        Response response = given().accept(ContentType.JSON).log().all().get(restUrl + resourceName).prettyPeek();
        return response;
    }

    public SingleAuthor creatRandomSingleAuthor() {

        Author author = new Author();
        author = createRandomAuthor();
        SingleAuthor singleAuthor = new SingleAuthor(author);

        return singleAuthor;
    }
    
        public Response deleteAuthor(int id) {

        String deleteResourceName = "authors/" + id;
        Response deleteResponse = delete(restUrl + deleteResourceName);
        return deleteResponse;
    }

}
