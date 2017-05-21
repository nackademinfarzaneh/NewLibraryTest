/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.helpers;

import static com.codeborne.selenide.Selenide.page;
import se.nackademin.librarytest.pages.EditUserPage;
import se.nackademin.librarytest.pages.MenuPage;
import se.nackademin.librarytest.pages.UserProfilePage;

/**
 *
 * @author testautom-nack
 */
public class MyProfileHelper {

//        public static void createNewAuthor(Author author) {
//
//        AddAuthorPage addAuthorPage = page(AddAuthorPage.class);
//
//        menuPage.navigateToAddAuthor();
//        addAuthorPage.setFirstName("Farzaneh");
//        addAuthorPage.setLastNameFiled("Yosefi");
//        addAuthorPage.setCountryFiled("Iran");
//        addAuthorPage.setBiographyFiled("Jag är född i Iran");
//
//        addAuthorPage.clickAddAuthorButton();
//
//    }
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

}
