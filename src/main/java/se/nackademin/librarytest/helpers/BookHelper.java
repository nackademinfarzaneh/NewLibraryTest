package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.ConfirmDialogPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.EditBookPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.UserProfilePage;

public class BookHelper {

    public static String randomDate() {

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

    public static void addNewBook(Book book) {

        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddBook();

    }

    public static Book fetchBook(String searchQuery) {

        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseBooks();
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);

        browseBooksPage.setTitleFiled(searchQuery);   //"Guards!"
        browseBooksPage.clickSearchBooksButton();

        browseBooksPage.clickFirstResultTitle();

        BookPage bookPage = page(BookPage.class);
        Book book = new Book();

        book.setTitleBook(bookPage.getTitle());
        book.setAuthor(bookPage.getAuthor());
        book.setDatePublishedBook(bookPage.getPublishDate());
        book.setNbrAvailableBook(bookPage.getAvailebleNbrOfBooks());
        book.setDescriptionBook(bookPage.getDescription());

        return book;
    }

    public static void convertDate(Date date) {

        String newDate = date.toString();
    }

    public static void changePublishDateBook(String searchQuery, Book book) {

        //   Book book = fetchBook(searchQuery);
        fetchBook(searchQuery);
        book.getTitleBook();

        book.getDatePublishedBook();

        BookPage bookPage = page(BookPage.class);
        EditBookPage editBookPage = page(EditBookPage.class);
        bookPage.clickEditBookButton();

        String randDate = randomDate();
        book.setDatePublishedBook(randDate);

        editBookPage.setPublishedDateFiled(bookPage.getPublishDate());
//
//        String bookPage1 = book.getDatePublishedBook().toString();

        //editBookPage.setPublishedDateFiled(convertedCurrentDate);
        editBookPage.clickSaveBookButton();

    }

    public static Book borrowBook(Book book) {

        book.getNbrAvailableBook();

        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        BookPage bookPage = page(BookPage.class);

        bookPage.clickBorrowBookButton();

        ConfirmDialogPage confirmDialogPage = page(ConfirmDialogPage.class);
        confirmDialogPage.clickConfirmDialogOKButton();

        String s = bookPage.getAvailebleNbrOfBooks().toString();

//        MenuPage menuPage = page(MenuPage.class);
//        menuPage.navigateToMyProfile();
//        UserProfilePage userProfilePage = page(UserProfilePage.class);
//        Table table = new Table($(".v-grid-tablewrapper"));
//        
//        table.searchAndClick(book.getTitleBook(), 1);
//        table.searchAndClick(book.getDateBorrow().toString(), 2);
//        table.searchAndClick(book.getDateDUE().toString(), 3);
        book.setNbrAvailableBook(bookPage.getAvailebleNbrOfBooks());
        return book;
    }

    public static Book returnBook(Book book) {

        int nrofbook = book.getNbrAvailableBook();

        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        BookPage bookPage = page(BookPage.class);

        bookPage.clickReturnBookButton();

        ConfirmDialogPage confirmDialogPage = page(ConfirmDialogPage.class);
        confirmDialogPage.clickConfirmDialogOKButton();

        nrofbook = book.getNbrAvailableBook();

        book.setNbrAvailableBook(book.getNbrAvailableBook());
        return book;
    }

}
