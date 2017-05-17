/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author testautom-nack
 */
public class MenuPage {

    @FindBy(css = "#side-menu-link-add-user")
    SelenideElement addUser;

    @FindBy(css = "#side-menu-link-sign-in")
    SelenideElement signIn;

    @FindBy(css = "#side-menu-link-my-profile")
    SelenideElement myProfile;
    
    @FindBy(css = "#side-menu-link-browse-books")
    SelenideElement browseboks;
    
   

    public void navigateToAddUser() {
        addUser.click();
    }

    public void navigateToSignIn() {
        signIn.click();
    }

    public void navigateToMyProfile() {
        myProfile.click();
    }
    public void nabigateToBrowseBooks(){
        browseboks.click();
    }
}
