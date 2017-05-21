package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import se.nackademin.librarytest.pages.AddUserPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.SignInPage;

public class UserHelper {

    static MenuPage menuPage = page(MenuPage.class);

    public static void createNewUser(String username, String password) {

        AddUserPage addUserPage = page(AddUserPage.class);

        menuPage.navigateToAddUser();
        addUserPage.setUserName(username);

        addUserPage.setPassWordFiled(password);
        addUserPage.clickAddUserButton();
    }

    public static void createNewUser(String username, String password, String email) {
        AddUserPage addUserPage = page(AddUserPage.class);

        menuPage.navigateToAddUser();

        addUserPage.setUserName(username);
        addUserPage.setEmail(email);
        addUserPage.setPassWordFiled(password);

        addUserPage.clickAddUserButton();
    }
 

    public static void logInAsUser(String username, String password) {

        SignInPage signInpage = page(SignInPage.class);
        menuPage.navigateToSignIn();

        signInpage.setUserName(username);
        signInpage.setPassWordFiled(password);
        signInpage.clickLogIn();
    }

}
