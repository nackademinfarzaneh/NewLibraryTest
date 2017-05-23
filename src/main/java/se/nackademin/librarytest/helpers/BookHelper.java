package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static void addNewBook(Book book) {
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

    public static void changePublishDateBook(String searchQuery) {

        Book book = fetchBook(searchQuery);
        book.getTitleBook();
        book.getDatePublishedBook();

        BookPage bookPage = page(BookPage.class);

        Date dateBefor = bookPage.getPublishDate();

        bookPage.clickEditBookButton();
        EditBookPage editBookPage = page(EditBookPage.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date convertedCurrentDate = sdf.parse("2017-05-21");
            editBookPage.setPublishedDateFiled(convertedCurrentDate);

            editBookPage.clickSaveBookButton();

        } catch (ParseException ex) {

            Logger.getLogger(BookHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Book borrowBook(String searchQuery) {

        Book book = new Book();

        book.getNbrAvailableBook();

        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        BookPage bookPage = page(BookPage.class);

        bookPage.getTitle();
        bookPage.getAuthor();
        bookPage.getAvailebleNbrOfBooks();
        bookPage.getDescription();

        bookPage.clickBorrowBookButton();

        ConfirmDialogPage borrowBooksConfirmPage = page(ConfirmDialogPage.class);
        borrowBooksConfirmPage.clickConfirmDialogOKButton();

        MenuPage menuPage = page(MenuPage.class);
        UserProfilePage userProfilePage = page(UserProfilePage.class);
        menuPage.navigateToMyProfile();

//        String s = bookPage.getAvailebleNbrOfBooks().toString();
//
//        book.setNbrAvailableBook(bookPage.getAvailebleNbrOfBooks());
        return book;
    }

    public static Book returnBook(String searchQuery) {

        Book book = fetchBook(searchQuery);

        book.getNbrAvailableBook();

        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        BookPage bookPage = page(BookPage.class);

        bookPage.getTitle();
        bookPage.getAuthor();
        bookPage.getAvailebleNbrOfBooks();
        bookPage.getDescription();

        bookPage.clickReturnBookButton();

        //  book.setNbrAvailableBook(bookPage.getAvailebleNbrOfBooks());
        return book;
    }

    public static Book borrowBook(Book book) {

        book.getNbrAvailableBook();

        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        BookPage bookPage = page(BookPage.class);

        bookPage.clickBorrowBookButton();

        ConfirmDialogPage confirmDialogPage = page(ConfirmDialogPage.class);
        confirmDialogPage.clickConfirmDialogOKButton();

        String s = bookPage.getAvailebleNbrOfBooks().toString();

        book.setNbrAvailableBook(bookPage.getAvailebleNbrOfBooks());
        return book;
    }

    public static Book returnBook(Book book) {

        book.getNbrAvailableBook();

        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        BookPage bookPage = page(BookPage.class);

        bookPage.clickReturnBookButton();

        ConfirmDialogPage confirmDialogPage = page(ConfirmDialogPage.class);
        confirmDialogPage.clickConfirmDialogOKButton();

        //   book.setNbrAvailableBook(bookPage.getAvailebleNbrOfBooks());
        return book;
    }

}
