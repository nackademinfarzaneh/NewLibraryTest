/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import se.nackademin.librarytest.pages.AddUserPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.SignInPage;

/**
 *
 * @author testautom-nack
 */
public class UserHelper {

    static MenuPage menuPage = page(MenuPage.class);

    public static void createNewUser(String username, String password) {

        AddUserPage addUserPage = page(AddUserPage.class);

        menuPage.navigateToAddUser();
        addUserPage.setUserName(username);

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
