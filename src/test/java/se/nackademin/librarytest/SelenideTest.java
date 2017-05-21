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
import se.nackademin.librarytest.helpers.AuthorHelper;
import se.nackademin.librarytest.helpers.BookHelper;
import se.nackademin.librarytest.helpers.MyProfileHelper;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.pages.AddAuthorPage;
import se.nackademin.librarytest.pages.AddUserPage;
import se.nackademin.librarytest.pages.AuthorPage;
import se.nackademin.librarytest.pages.BrowseAuthorsPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.UserProfilePage;

public class SelenideTest extends TestBase {

    public SelenideTest() {
    }

    @Test
    public void testUsingTable() {

        page(MenuPage.class).navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.setTitleFiled("G");

        browseBooksPage.clickSearchBooksButton();
        Table table = new Table($(".v-grid-tablewrapper"));

        System.out.println(table.getRowCount());
        System.out.println(table.getColumnCount());
        System.out.println(table.getCellValue(0, 0));
        sleep(2000);
        //  table.clickCell(1, 1);
        table.searchAndClick("American Gods", 0);
        sleep(5000);
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
    public void testLogin() {

        ChromeDriverManager.getInstance().setup();
//        WebDriver driver = new ChromeDriver();        
//        driver.get("http://localhost:8080/librarytest");

        String uuid = UUID.randomUUID().toString();

        MenuPage menuPage = page(MenuPage.class);
        UserHelper.createNewUser(uuid, uuid);
        UserHelper.logInAsUser(uuid, uuid);

        menuPage.navigateToMyProfile();
        UserProfilePage myProfilePage = page(UserProfilePage.class);
        myProfilePage.getUserName();
        Assert.assertEquals("Username should be shown in profile", uuid, myProfilePage.getUserName());

        sleep(2000);
    }

    @Test
    public void testChangeEmailFromMyProfile() {
        MenuPage menuPage = page(MenuPage.class);

        UserHelper.createNewUser("Testnr7", "Testnr7", "testEmail1@test.se");
        UserHelper.logInAsUser("Testnr7", "Testnr7");

        menuPage.navigateToMyProfile();
        UserProfilePage userProfilePage = page(UserProfilePage.class);
        userProfilePage.getUserName();

        Assert.assertEquals("Testnr7", userProfilePage.getUserName());
        Assert.assertEquals("testEmail1@test.se", userProfilePage.getEmailFiled());

        MyProfileHelper.changeEmail();
        menuPage.navigateToMyProfile();

        userProfilePage.getEmailFiled();
        Assert.assertEquals("test22@test.se", userProfilePage.getEmailFiled());

        sleep(3000);
    }

    @Test
    public void testAddAuthor() {

        MenuPage menuPage = page(MenuPage.class);
        UserHelper.logInAsUser("admin", "1234567890");

        Author author = new Author();
        author.setFirstName("test");
        author.setLastName("Test");
        author.setCountry("Sverige");
        author.setBiography("Jag lerver i Sverige");

        AuthorHelper.createNewAuthor(author);

        AuthorHelper.fetchAuthor(author.getFirstName());

        //      Assert.assertEquals("AuthorsName should be shown", "test", authorPage.getName());
        sleep(2000);
    }

    @Test
    public void testFetchAuthor() {

        MenuPage menuPage = page(MenuPage.class);

        page(MenuPage.class).navigateToAddAuthor();
        BrowseAuthorsPage browseAuthorsPage = page(BrowseAuthorsPage.class);

        browseAuthorsPage.setNameFiled("test");
        browseAuthorsPage.clickSearchAuthorsButton();

        Table table = new Table($(".v-grid-tablewrapper"));

        System.out.println(table.getRowCount());
        System.out.println(table.getColumnCount());
        System.out.println(table.getCellValue(0, 0));
        
        sleep(2000);
        
        table.searchAndClick("test", 0);
        table.getCellValue(0, 0);

        sleep(5000);

        //   assertEquals("Authors name should be, 'Sheri", "Sheri", author.getFirstName());
       
    }

}
