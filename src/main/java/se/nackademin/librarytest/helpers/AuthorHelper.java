/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import static se.nackademin.librarytest.helpers.UserHelper.menuPage;
import se.nackademin.librarytest.model.Author;
import se.nackademin.librarytest.pages.AddAuthorPage;
import se.nackademin.librarytest.pages.AddUserPage;
import se.nackademin.librarytest.pages.AuthorPage;

import se.nackademin.librarytest.pages.BookPage;
import se.nackademin.librarytest.pages.BrowseAuthorsPage;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.MenuPage;

/**
 *
 * @author testautom-nack
 */
public class AuthorHelper {

    public static void createNewAuthor(Author author) {
        
        

        AddAuthorPage addAuthorPage = page(AddAuthorPage.class);

        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToAddAuthor();

        addAuthorPage.setFirstName(author.getFirstName());
        addAuthorPage.setLastNameFiled(author.getLastName());
        addAuthorPage.setCountryFiled(author.getCountry());
        addAuthorPage.setBiographyFiled(author.getBiography());

        addAuthorPage.clickAddAuthorButton();

        //author = fetchAuthor()
    }

    public static Author fetchAuthor(String searchQuery) {
        // public static Author fetchAuthor() {

        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToBrowseAuthors();
        BrowseAuthorsPage browseAuthorsPage = page(BrowseAuthorsPage.class);
        //      browseAuthorsPage.setNameFiled("sheri");
        browseAuthorsPage.clickSearchAuthorsButton();

        AuthorPage authorPage = page(AuthorPage.class);
        Author author = new Author();
        author.setFirstName(authorPage.getName());
        author.setCountry(authorPage.getCountry());
        author.setBiography(authorPage.getBiography());

        return author;
    }

}
