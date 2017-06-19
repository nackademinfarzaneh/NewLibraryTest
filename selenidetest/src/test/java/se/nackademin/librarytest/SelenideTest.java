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
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import se.nackademin.librarytest.helpers.AuthorHelper;
import se.nackademin.librarytest.helpers.BookHelper;
import se.nackademin.librarytest.helpers.MyProfileHelper;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.model.User;
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
        // table.clickCell(1, 1);
        table.searchAndClick("American Gods", 0);

        sleep(5000);
    }

    @Test
    public void tesTable() {

        String uuid = UUID.randomUUID().toString();

        MenuPage menuPage = page(MenuPage.class);
        UserHelper.createNewUser(uuid, uuid);
        UserHelper.logInAsUser(uuid, uuid);

        menuPage.navigateToMyProfile();
        UserProfilePage userProfilePage = page(UserProfilePage.class);

        Table table = new Table($(".v-grid-tablewrapper"));
//        table.searchAndClick(userProfilePage.getBookTitle(), 0);
//        table.searchAndClick(userProfilePage.getDateAvBookDue(), 1);
//        table.searchAndClick(userProfilePage.getDateAvBookBorrow(), 2);

        table.searchAndClick("book title", 0);
        table.searchAndClick("date av due", 1);
        table.searchAndClick("date av borrow", 2);

    }

    @Test
    @Ignore
    public void testFetchBook() {

        Book book = BookHelper.fetchBook("Guards!");
        assertEquals("Title should be, 'Guards! Guards!", "Guards! Guards!", book.getTitleBook());
        assertEquals("Author should be, 'Terry Pratchett", "Terry Pratchett", book.getAuthor());
        sleep(4000);
    }

    @Test
    @Ignore
    public void testLogin() {

        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();        
        driver.get("http://localhost:8080/librarytest");

        String uuid = UUID.randomUUID().toString();

        MenuPage menuPage = page(MenuPage.class);
        UserHelper.createNewUser(uuid, uuid);
        UserHelper.logInAsUser(uuid, uuid);

        menuPage.navigateToMyProfile();
        UserProfilePage myProfilePage = page(UserProfilePage.class);
        myProfilePage.getUserName();
        Assert.assertEquals("Username should be shown in profile", uuid, myProfilePage.getUserName());

        sleep(3000);
    }

    @Test
    @Ignore
    public void testChangeEmailFromMyProfile() {
        MenuPage menuPage = page(MenuPage.class);

        String uuid = UUID.randomUUID().toString();

        UserHelper.createNewUser(uuid, uuid, "testEmail1@test.se");
        UserHelper.logInAsUser(uuid, uuid);

        User user = MyProfileHelper.getUser();

        menuPage.navigateToMyProfile();
        UserProfilePage userProfilePage = page(UserProfilePage.class);
        userProfilePage.getUserName();

        Assert.assertEquals(uuid, userProfilePage.getUserName());
        Assert.assertEquals("testEmail1@test.se", userProfilePage.getEmailFiled());

        MyProfileHelper.changeEmail();
        menuPage.navigateToMyProfile();

        userProfilePage.getEmailFiled();
        Assert.assertEquals("test22@test.se", userProfilePage.getEmailFiled());

        sleep(2000);
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
    public void testAddNewBook() {

        MenuPage menuPage = page(MenuPage.class);
        Book book = new Book();

        UserHelper.logInAsUser("admin", "1234567890");
        book.setTitleBook("Test1");
        book.setNbrAvailableBook(10);
        book.setPageNr(200);
        BookHelper.addNewBook(book);
        BookHelper.fetchBook(book.getTitleBook());

        Assert.assertEquals("Book should be shown", "Test1", book.getTitleBook());
        sleep(2000);

    }

    @Test
    @Ignore
    public void testFetchAuthor() {

        Author author = AuthorHelper.fetchAuthor("Test");
        assertEquals("Title should be, 'Test", "test Test", author.getFirstName());

        sleep(2000);
    }

    @Test
    public void testChangePublishDateBook() {

        MenuPage menuPage = page(MenuPage.class);
        UserHelper.logInAsUser("admin", "1234567890");

        Book book = BookHelper.fetchBook("Good Omens");
        assertEquals("Title should be, 'Good Omens'", "Good Omens", book.getTitleBook());

        String unsectied = book.getDatePublishedBook();

        BookHelper.changePublishDateBook("Good Omens", book);

        String actual = book.getDatePublishedBook();

        assertNotEquals("Published ", unsectied, actual);
        sleep(3000);
    }

    @Test
    public void testBorrowBook() {

        MenuPage menuPage = page(MenuPage.class);
        BookPage bookPage = page(BookPage.class);
        Book book = new Book();

        UserHelper.logInAsUser("admin", "1234567890");
        book.setTitleBook("Test1");
        book.setNbrAvailableBook(10);
        book.setPageNr(200);
        BookHelper.addNewBook(book);

        book = BookHelper.fetchBook("Test1");
        sleep(2000);

        Integer antalBookBeforBorrow = book.getNbrAvailableBook();

        book = BookHelper.borrowBook(book);

        book = BookHelper.fetchBook(book.getTitleBook());

        Integer antalBookafterBorrow = book.getNbrAvailableBook();

        System.out.print("available book after borrow book");
        System.out.println(book.getNbrAvailableBook().toString());

        assertEquals("Nr off available book after borrow book", "9", book.getNbrAvailableBook().toString());

        book = BookHelper.returnBook(book);

        book = BookHelper.fetchBook(book.getTitleBook());

        System.out.print("available book after retunr book");
        System.out.println(book.getNbrAvailableBook().toString());

        Integer antalBookafter = book.getNbrAvailableBook();

        assertEquals("Nr off available book after return book", "10", book.getNbrAvailableBook().toString());

        sleep(3000);
    }

    @Test
    public void viewUserProfilePage() {

        MenuPage menuPage = page(MenuPage.class);

        String uuid = UUID.randomUUID().toString();

        UserHelper.createNewUser(uuid, uuid, "testEmail1@test.se");
        UserHelper.logInAsUser(uuid, uuid);

        MyProfileHelper.viewUserProfilePage(uuid, uuid);

        sleep(5000);
    }

}
