package se.nackademin.librarytest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import se.nackademin.librarytest.helpers.Table;
import static com.codeborne.selenide.Selenide.*;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import se.nackademin.librarytest.helpers.AuthorHelper;
import se.nackademin.librarytest.helpers.BookHelper;
import se.nackademin.librarytest.helpers.MyProfileHelper;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.pages.AuthorPage;
import se.nackademin.librarytest.pages.BookPage;
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
    public void testFetchBook() {

        Book book = BookHelper.fetchBook("Guards!");
        assertEquals("Title should be, 'Guards! Guards!", "Guards! Guards!", book.getTitleBook());
        assertEquals("Author should be, 'Terry Pratchett", "Terry Pratchett", book.getAuthor());
        sleep(2000);
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

        UserHelper.createNewUser("Testnr8", "Testnr7", "testEmail1@test.se");
        UserHelper.logInAsUser("Testnr8", "Testnr7");

        menuPage.navigateToMyProfile();
        UserProfilePage userProfilePage = page(UserProfilePage.class);
        userProfilePage.getUserName();

        Assert.assertEquals("Testnr8", userProfilePage.getUserName());
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

        BrowseAuthorsPage browseAuthorsPage = page(BrowseAuthorsPage.class);

        AuthorHelper.createNewAuthor(author);
        menuPage.navigateToMyProfile();
        AuthorPage authorPage = page(AuthorPage.class);

        AuthorHelper.fetchAuthor(author.getFirstName());

        Assert.assertEquals("AuthorsName should be shown", "test", author.getFirstName());
        sleep(2000);
    }

    @Test
    public void testFetchAuthor() {

        Author author = AuthorHelper.fetchAuthor("Test");
        assertEquals("Title should be, 'Test", "test Test", author.getFirstName());

        sleep(2000);
    }

    @Test
    public void testChangePublishDateBook() {

        MenuPage menuPage = page(MenuPage.class);
        UserHelper.logInAsUser("admin", "1234567890");

        //       Book book = BookHelper.fetchBook("Good Omens");
        //      assertEquals("Title should be, 'Good Omens'", "Good Omens", book.getTitle());
        BookHelper.changePublishDateBook("Good Omens");

        Book book = BookHelper.fetchBook("Good Omens");

        BookPage bookPage = page(BookPage.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date convertedCurrentDate = sdf.parse("2017-05-21");

            System.out.println(book.getDatePublishedBook());
            System.out.print(bookPage.getPublishDate());

            assertEquals("Published date should be, '2017-05-21'", convertedCurrentDate, book.getDatePublishedBook());

        } catch (ParseException ex) {

            Logger.getLogger(SelenideTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testBorrowBook() {

        MenuPage menuPage = page(MenuPage.class);
        BookPage bookPage = page(BookPage.class);

        UserHelper.createNewUser("hasan", "hasan");
        UserHelper.logInAsUser("hasan", "hasan");

        BookHelper.borrowBook("Good Omens");
    //    int nbrAvilableBookAfter = book.getNbrAvailableBook();

       int avbook=  bookPage.getAvailebleNbrOfBooks();

        assertEquals("Nr off available book after borrowBook", "3", bookPage.getAvailebleNbrOfBooks());
        // assertEquals("Nr off available book after total books", "", bookPage.getTotNbrOfBooks());
    }

}
