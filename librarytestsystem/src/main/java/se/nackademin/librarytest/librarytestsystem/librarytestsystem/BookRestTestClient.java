/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import se.nackademin.librarytest.librarytestsystem.model.Book;
import se.nackademin.librarytest.librarytestsystem.model.SingleBook;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.post;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.net.URL;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author testautom-nack
 */
public class BookRestTestClient {

    URL url;
    private String jsonString = "";

    private static final String restUrl = "http://localhost:8080/librarytest-rest/";

    public Response getBook(int id) {

        String resourceName = "books/" + id;
        Response response = resoursGetter(resourceName);
        return response;
    }

    public Response getAllBooks() {
        Response response = resoursGetter("books");
        return response;
    }

    private static Response resoursGetter(String resourceName) {
        Response response = given().accept(ContentType.JSON).log().all().get(restUrl + resourceName).prettyPeek();
        return response;
    }

    public Response getAllBooksBySpecificAuthor(int authorId) {

        Response response = given().accept(ContentType.JSON).log().all().get(restUrl + "books/byauthor/" + authorId).prettyPeek();
        return response;
    }

    Response getAuthorsOfSpecifiedBook(int bookId) {
        Response response = given().accept(ContentType.JSON).log().all().get(restUrl + "books/" + bookId + "/authors").prettyPeek();
        return response;
    }

    Response addAuthorToSpecifiedBook1(int bookId) {

//        String postResourceName = "books/ + id";
//        Response postResponse = post(restUrl + postResourceName);
        Response postResponse = given().accept(ContentType.JSON).log().all().post(restUrl + "books/" + bookId + "/authors").prettyPeek();

        return postResponse;
    }

    public Response addAuthorToSpecifiedBook(int bookId, SingleBook singleBook) {

        Response postResponse = given().contentType(ContentType.JSON).body(singleBook).log().all().post(restUrl + "books/" + bookId + "/authors");
        return postResponse;
    }

    Response uppdateAuthorOfSpecifiedBook(Integer bookId, SingleBook singleBook) {

        Response putResponse = given().contentType(ContentType.JSON).body(singleBook).log().all().put(restUrl + "books/" + bookId + "/authors");
        return putResponse;
    }

    public Response postBook(int id) {

        String postResourceName = "books/ " + id;
        Response postResponse = post(restUrl + postResourceName);
        return postResponse;
    }

    public Response createBook(SingleBook singleBook) {
        String postResourceName = "books";
        Response response = resoursCreator(postResourceName, singleBook);
        return response;
    }

    private static Response resoursCreator(String resourceName, SingleBook singleBook) {
        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().post(restUrl + resourceName);
        return response;
    }

    public Response putBook(SingleBook singleBook) {
        Response response = resoursPutter("books", singleBook);
        return response;
    }

    private static Response resoursPutter(String resourceName, SingleBook singleBook) {
        Response response = given().contentType(ContentType.JSON).body(singleBook).log().all().put(restUrl + resourceName).prettyPeek();
        return response;
    }

    public Response deleteBook(int id) {

        String deleteResourceName = "books/" + id;
        Response deleteResponse = delete(restUrl + deleteResourceName);
        return deleteResponse;
    }  

    public String randomDate() {

        //random.nextInt(max - min + 1) + min
        Random ran = new Random();
        //  Integer dag = ran.nextInt(12);
        Integer manad = ran.nextInt(12 - 1 + 1) + 1;
        // Integer manad = ran.nextInt(30);
        Integer dag = ran.nextInt(30 - 1 + 1) + 1;
        Integer ar = ran.nextInt(2000 - 1990 + 1) + 1990;
        String dag1 = dag.toString();
        String manad1 = manad.toString();
        if (dag < 10) {
            dag1 = "0" + dag.toString();
        }
        if (manad < 10) {
            manad1 = "0" + manad.toString();
        }
        String date = ar + "-" + manad1 + "-" + dag1;
        return date;
    }

    public Book createRandomBook() {

        BookRestTestClient restTestClient = new BookRestTestClient();
        //  r.nextInt((max - min) + 1) + min;
        Random Randomizer = new Random();
        Integer id = Randomizer.nextInt((300 - 50) + 1) + 50;
        String description = UUID.randomUUID().toString().substring(1, 5);
        String isbn = UUID.randomUUID().toString().substring(1, 5);

        Integer nberPage = Randomizer.nextInt((300 - 50) + 1) + 50;
        String publicationDate = restTestClient.randomDate();
        String title = UUID.randomUUID().toString().substring(1, 5);
        Integer totalNbrCopies = Randomizer.nextInt((300 - 50) + 1) + 50;

        Book book = new Book();
        book.setId(id);
        book.setDescription(description);
        book.setIsbn(isbn);
        book.setNbrPages(nberPage);
        book.setPublicationDate(publicationDate);
        book.setTitle(title);
        book.setTotalNbrCopies(totalNbrCopies);

        return book;
    }

}
