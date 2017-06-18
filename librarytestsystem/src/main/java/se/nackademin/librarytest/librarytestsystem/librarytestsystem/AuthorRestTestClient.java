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

    public Response deleteAuthor(int id) {

        String deleteResourceName = "authors/" + id;
        Response deleteResponse = delete(restUrl + deleteResourceName);
        return deleteResponse;
    }
}
