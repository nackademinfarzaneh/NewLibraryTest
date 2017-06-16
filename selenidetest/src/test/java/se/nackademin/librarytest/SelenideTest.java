package se.nackademin.librarytest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import se.nackademin.librarytest.helpers.Table;
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
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.MyProfilePage;

/**
 *
 * @author testautom-nack
 */
public class SelenideTest extends TestBase {

    public SelenideTest() {
    }

    @Test
    public void testUsingTable() {

        page(MenuPage.class).nabigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleFiled("G");
        browseBooksPage.clickSearchBooksButton();
        Table table = new Table($(".v-grid-tablewrapper"));

        System.out.println(table.getRowCount());
        System.out.println(table.getColumnCount());
        System.out.println(table.getCellValue(0, 0));
        System.out.println(table.getCellValue(1, 1));
        //  table.clickCell(1, 1);
        table.searchAndClick("American Gods", 0);
        sleep(3000);
    }

    @Test
    @Ignore
    public void testFetchBook() {

        Book book = BookHelper.fetchBook("Guards!");
        assertEquals("Title should be, 'Guards! Guards!", "Guards! Guards!", book.getTitle());
        assertEquals("Author should be, 'Terry Pratchett", "Terry Pratchett", book.getAuthor());
        sleep(1000);
    }

    @Test
    @Ignore
    public void testLogin() {

        ChromeDriverManager.getInstance().setup();
//        WebDriver driver = new ChromeDriver();        
//        driver.get("http://localhost:8080/librarytest");

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
