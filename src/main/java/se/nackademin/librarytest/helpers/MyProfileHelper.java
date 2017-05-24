/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import se.nackademin.librarytest.model.User;
import se.nackademin.librarytest.pages.BrowseBooksPage;
import se.nackademin.librarytest.pages.EditUserPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.UserProfilePage;

/**
 *
 * @author testautom-nack
 */
public class MyProfileHelper {

    public static User getUser() {

        MenuPage menuPage = page(MenuPage.class);

        menuPage.navigateToMyProfile();
        UserProfilePage userProfilePage = page(UserProfilePage.class);

        User user = new User();

        userProfilePage.getUserName();
        userProfilePage.getBookLoanFiled();
        userProfilePage.getEmailFiled();

        user.setUserFirstName(userProfilePage.getUserFirstName());
        user.setUserLastName(userProfilePage.getUserLastName());

        return user;
    }

    public static void changeEmail() {

        MenuPage menuPage = page(MenuPage.class);
        menuPage.navigateToMyProfile();

        UserProfilePage userProfilePage = page(UserProfilePage.class);
        userProfilePage.getEmailFiled();
        userProfilePage.clickEditUserButton();

        EditUserPage editUserPage = page(EditUserPage.class);

        editUserPage.setEmailFiled("test22@test.se");
        editUserPage.clickSaveUserButton();

    }

    public static void viewUserProfilePage(String userName, String login) {

        MenuPage menuPage = page(MenuPage.class);

        UserProfilePage userProfilePage = page(UserProfilePage.class);
        menuPage.navigateToMyProfile();
    }

}
