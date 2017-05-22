/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.nackademin.librarytest.model.Book;
import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.BorrowBooksConfirmPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.EditBookPage;
import se.nackademin.librarytest.pages.MenuPage;

/**
 *
 * @author testautom-nack
 */
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

        Book book = fetchBook(searchQuery);

       int b=  book.getNbrAvailableBook();
       
        BrowseBooksPage browseBooksPage = page(BrowseBooksPage.class);
        BookPage bookPage = page(BookPage.class); 
        
        bookPage.getTitle();
        bookPage.getAuthor();
       bookPage.getAvailebleNbrOfBooks();
       bookPage.getDescription();       
       
        bookPage.clickBorrowBookButton();       

        BorrowBooksConfirmPage borrowBooksConfirmPage = page(BorrowBooksConfirmPage.class);
        borrowBooksConfirmPage.clickConfirmDialogOKButton();

        book.setNbrAvailableBook(bookPage.getAvailebleNbrOfBooks());

        return book;
    }
}
