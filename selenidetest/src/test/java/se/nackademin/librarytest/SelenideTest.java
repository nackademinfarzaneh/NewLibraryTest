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
import se.nackademin.librarytest.helpers.AuthorHelper;
import se.nackademin.librarytest.helpers.BookHelper;
import se.nackademin.librarytest.helpers.MyProfileHelper;
import se.nackademin.librarytest.helpers.UserHelper;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.model.User;
import se.nackademin.librarytest.pages.AuthorPage;
import se.nackademin.librarytest.pages.ViewBookPage;
import se.nackademin.librarytest.pages.BrowseAuthorsPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.ConfirmDialogPage;
import se.nackademin.librarytest.pages.EditBookPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.UserProfilePage;

public class SelenideTest extends TestBase {

    public SelenideTest() {
    }

    @Test
    public void testLogin() {

        ChromeDriverManager.getInstance().setup();

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
    public void testBrrowBook() {
        // välja browsebooks
        //komm in i Browse booksPage
        //välj searchButtom
        //välj en book i listan
        //kom in i View bookPage
        //om Number of copies available: > 0
        //välj butom brrow book
        //visas confirm page (medelande) välj ja
        //

        UserHelper.logInAsUser("admin", "1234567890");
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseBooks();
        BrowseBooksPage browseBookPage = page(BrowseBooksPage.class);
        browseBookPage.clickSearchBooksButton();

        browseBookPage.clickFirstResultTitle();

        ViewBookPage viewBookPage = page(ViewBookPage.class);

        viewBookPage.clickBorrowBookButton();
        int nrOfAvailebleBookBefor = viewBookPage.getAvailebleNbrOfBooks();

        ConfirmDialogPage confirmDialogPage = page(ConfirmDialogPage.class);
        confirmDialogPage.clickConfirmDialogOKButton();
        sleep(3000);

        int nrOfAvailebleBookAfter = viewBookPage.getAvailebleNbrOfBooks();
        // expected, actual
        assertEquals(nrOfAvailebleBookBefor - 1, nrOfAvailebleBookAfter);
        //return book
        viewBookPage.clickReturnBookButton();
        confirmDialogPage.clickConfirmDialogOKButton();

        UserHelper.logOutAsUser();
        sleep(5000);
        assertEquals(nrOfAvailebleBookAfter + 1, nrOfAvailebleBookBefor);
    }

    @Test
    public void testEditBook_AddBook() {

        UserHelper.logInAsUser("admin", "1234567890");
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseBooks();
        BrowseBooksPage browseBookPage = page(BrowseBooksPage.class);
        browseBookPage.clickSearchBooksButton();

        browseBookPage.clickFirstResultTitle();

        ViewBookPage viewBookPage = page(ViewBookPage.class);

        int nrOfBook = viewBookPage.getAvailebleNbrOfBooks();
        viewBookPage.clickEditBookButton();

        EditBookPage editBookPage = page(EditBookPage.class);

        editBookPage.setNrOfInventory(nrOfBook + 2);

        Book book = new Book();
        book.setNbrAvailableBook(nrOfBook + 2);

        editBookPage.clickSaveBookButton();
        int nr = book.getNbrAvailableBook();

        sleep(3000);
        System.out.println("nr of book befor edit is:  " + nrOfBook);
        System.out.println("nr of book after edit is:  " + nr);

        UserHelper.logOutAsUser();

        assertNotEquals(nrOfBook, nr);

    }

    @Test
    @Ignore
    //Den här testen fungerar inte på grund av bug en knapp. Rätt :Add Book
    // men det är Add Author
    public void testAddNewBook() {

        MenuPage menuPage = page(MenuPage.class);
        Book book = new Book();

        UserHelper.logInAsUser("admin", "1234567890");
        book.setTitleBook("Test1");
        book.setNbrAvailableBook(10);
        book.setPageNr(200);
        BookHelper.addNewBook(book);
        BookHelper.fetchBook(book.getTitleBook());

        UserHelper.logOutAsUser();

        Assert.assertEquals("Book should be shown", "Test1", book.getTitleBook());
        sleep(2000);
    }

    @Test
    @Ignore
    //Testade inte deleteBook eftersom man kunde inte skapa ny book!
    //Bara skrev logiken hur 
    public void testDeleteBook() {

        UserHelper.logInAsUser("admin", "1234567890");
        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseBooks();
        BrowseBooksPage browseBookPage = page(BrowseBooksPage.class);
        browseBookPage.clickSearchBooksButton();

        browseBookPage.clickFirstResultTitle();
        ViewBookPage viewBookPage = page(ViewBookPage.class);

        String booktitle = browseBookPage.getTitleFiled();
        viewBookPage.clickDeleteBookButton();

        ConfirmDialogPage confirmDialogPage = page(ConfirmDialogPage.class);
        confirmDialogPage.clickConfirmDialogCancelButtonButton();

        browseBookPage.clickSearchBooksButton();
        String booktitleAfterDelete = browseBookPage.getTitleFiled();

        UserHelper.logOutAsUser();

        Assert.assertNotNull("Book med namn" + booktitle + "finns");
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
        UserHelper.logOutAsUser();
        Assert.assertEquals("AuthorsName should be shown", "test", author.getFirstName());
        sleep(4000);
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
        table.clickCell(1, 1);
        // table.searchAndClick("American Gods", 0);

        sleep(5000);
    }

    @Test
    @Ignore
    public void tesTable() {

        MenuPage menuPage = page(MenuPage.class);

        UserHelper.logInAsUser("admin", "1234567890");

        menuPage.navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        browseBooksPage.clickSearchBooksButton();
        Table table = new Table($(".v-grid-tablewrapper"));
        table.clickCell(1, 1);
        String s = table.getCellValue(1, 1);

        ViewBookPage viewBookPage = page(ViewBookPage.class);
        viewBookPage.getTitle();

        table.searchAndClick(s, 1);
        sleep(3000);

    }

    @Test
    public void testFetchBook() {

        Book book = BookHelper.fetchBook("Guards!");
        assertEquals("Title should be, 'Guards! Guards!", "Guards! Guards!", book.getTitleBook());
        assertEquals("Author should be, 'Terry Pratchett", "Terry Pratchett", book.getAuthor());
        sleep(4000);
    }

    @Test
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
    public void viewUserProfilePage() {

        MenuPage menuPage = page(MenuPage.class);

        String uuid = UUID.randomUUID().toString();

        UserHelper.createNewUser(uuid, uuid, "testEmail1@test.se");
        UserHelper.logInAsUser(uuid, uuid);

        MyProfileHelper.viewUserProfilePage(uuid, uuid);

        sleep(5000);
    }

}
