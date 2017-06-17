/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import java.util.Random;
import se.nackademin.librarytest.librarytestsystem.model.Loan;
import se.nackademin.librarytest.librarytestsystem.model.SingleLoan;

/**
 *
 * @author testautom-nack
 */
public class LoanRestTestClient {

    private static final String restUrl = "http://localhost:8080/librarytest-rest/";

    public Response createLoan(SingleLoan singleloan) {
        String postResourceName = "loans";
        Response response = resoursCreator(postResourceName, singleloan);
        return response;
    }

    private static Response resoursCreator(String resourceName, SingleLoan singleloan) {
        Response response = given().contentType(ContentType.JSON).body(singleloan).log().all().post(restUrl + resourceName);
        return response;
    }

    public Response getLoan(int id) {

        String resourceName = "loans/" + id;
        Response response = resoursGetter(resourceName);
        return response;
    }

    private static Response resoursGetter(String resourceName) {
        Response response = given().accept(ContentType.JSON).log().all().get(restUrl + resourceName).prettyPeek();
        return response;
    }

    public Response getAllLoan() {
        Response response = resoursGetter("loans");
        return response;
    }

    Response getLoanOfBook(int id) {
        String resourceName = "loans/ofbook/" + id;
        Response response = resoursGetter(resourceName);
        return response;
    }

    Response getLoanOfUser(int id) {

        String resourceName = "loans/ofuser/" + id;
        Response response = resoursGetter(resourceName);
        return response;
    }

    Response getLoanOfBookByUser(SingleLoan singleLoan) {

        Loan loan = singleLoan.getLoan();
        int bookId = loan.getBook().getId();
        int userId = loan.getUser().getId();

        String resourceName = "loans/ofuser/" + userId + "/ofbook/" + bookId;

        Response response = given().contentType(ContentType.JSON).body(loan.getUser()).log().all().get(restUrl + resourceName).prettyPeek();   //.body(singleLoan)
        return response;

    }

    public Response deleteLoan(int id) {

        String deleteResourceName = "loans/" + id;
        Response deleteResponse = delete(restUrl + deleteResourceName);
        return deleteResponse;
    }

    public Response putLoan(SingleLoan singleLoan) {
        Response response = resoursPutter("loans", singleLoan);
        return response;
    }

    private static Response resoursPutter(String resourceName, SingleLoan singleLoan) {
        Response response = given().contentType(ContentType.JSON).body(singleLoan).log().all().put(restUrl + resourceName).prettyPeek();
        return response;
    }

    public String barrowRandomDate() {

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

    public String dueRandomDate() {

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
            dag1 = "" + dag.toString();
        }
        if (manad < 10) {
            manad1 = "0" + manad.toString();
        }
        String date = ar + "-" + manad1 + "-" + dag1;
        return date;
    }

}
