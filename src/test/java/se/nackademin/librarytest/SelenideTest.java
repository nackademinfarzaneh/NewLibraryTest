package se.nackademin.librarytest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.codeborne.selenide.Selenide.*;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.util.UUID;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;

import org.junit.Test;
import se.nackademin.librarytest.helpers.BookHelper;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.pages.AddUserPage;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.MyProfilePage;
import se.nackademin.librarytest.pages.SignInPage;

/**
 *
 * @author testautom-nack
 */
public class SelenideTest {

    public SelenideTest() {
        System.setProperty("webdriver.chrome.driver", "/home/testautom-nack/testautom-nack/seleniumdrivers/chromedriver");
        System.setProperty("selenide.browser", "Chrome");
        open("http://localhost:8080/librarytest");

    }

    @Test
    public void testFetchBook() {
        
        Book book = BookHelper.fetchBook("Guards!");
        
        assertEquals("Title should be, 'Guards! Guards!", "Guards! Guards!", book.getTitle());

        sleep(1000);
    }

    @Test
    
    public void testLogin() {

        ChromeDriverManager.getInstance().setup();
//        WebDriver driver = new ChromeDriver();        
//        driver.get("http://localhost:8080/librarytest");

//        System.setProperty("webdriver.chrome.driver", "/home/testautom-nack/testautom-nack/seleniumdrivers/chromedriver");
//        System.setProperty("selenide.browser", "Chrome");
//        open("http://localhost:8080/librarytest");

        String uuid = UUID.randomUUID().toString();

        MenuPage menuPage = page(MenuPage.class);
        UserHelper.createNewUser(uuid, uuid);
        UserHelper.logInAsUser(uuid, uuid);

        menuPage.navigateToMyProfile();
        MyProfilePage myProfilePage = page(MyProfilePage.class);
        myProfilePage.getUserName();

        Assert.assertEquals("Username should be shown in profile", uuid, myProfilePage.getUserName());
        sleep(3000);

    }
}
