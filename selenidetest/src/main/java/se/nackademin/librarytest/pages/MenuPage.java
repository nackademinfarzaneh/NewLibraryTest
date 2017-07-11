package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import java.util.logging.Logger;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends PageBase {

    private static final Logger Log = Logger.getLogger(MenuPage.class.getName());

    @FindBy(css = "#side-menu-link-add-user")
    SelenideElement addUser;

    @FindBy(css = "#side-menu-link-sign-in")
    SelenideElement signIn;

    @FindBy(css = " #side-menu-link-sign-out") 
    SelenideElement signOut;

    @FindBy(css = "#side-menu-link-my-profile")
    SelenideElement myProfile;

    @FindBy(css = "#side-menu-link-browse-books")
    SelenideElement browseboks;

    @FindBy(css = "#side-menu-link-add-author")
    SelenideElement addAuthor;

    @FindBy(css = "#side-menu-link-add-book")
    SelenideElement addBook;

    @FindBy(css = "#side-menu-link-browse-authors")
    SelenideElement browseAuthors;

    public void navigateToAddAuthor() {
        clickButton("Add Author", addAuthor);
    }

    public void navigateToAddUser() {

        clickButton("Add user", addUser);
    }

    public void navigateToAddBook() {

        clickButton("Add book", addBook);
    }

    public void navigateToSignIn() {

        clickButton("Sign in", signIn);
    }

    public void navigateToSignOut() {

        clickButton("Sign out", signOut);
    }

    public void navigateToMyProfile() {
        clickButton("My profile", myProfile);
    }

    public void navigateToBrowseBooks() {

        clickButton("Browse Books", browseboks);
    }

    public void navigateToBrowseAuthors() {

        clickButton("Browse authors", browseAuthors);
    }
}
