/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.librarytestsystem.librarytestsystem;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author testautom-nack
 */
public class TestBase {
    
    private static RequestSpecification spec;

    @BeforeClass
    public static void initSpec() {
        
        System.setProperty("webdriver.chrome.driver", "/home/testautom-nack/testautom-nack/seleniumdrivers/chromedriver");
        System.setProperty("selenide.browser", "Chrome");
        spec = new RequestSpecBuilder().setContent(ContentType.JSON).setBaseUri("\"http://localhost:8080/librarytest\"").build();
        

        
    }

}
