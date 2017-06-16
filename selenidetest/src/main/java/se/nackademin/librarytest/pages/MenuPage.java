/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.util.logging.Logger;
import org.openqa.selenium.support.FindBy;


/**
 *
 * @author testautom-nack
 */
public class MenuPage extends PageBase{

    private static final Logger Log = Logger.getLogger(MenuPage.class.getName());
    
    @FindBy(css = "#side-menu-link-add-user")
    SelenideElement addUser;

    @FindBy(css = "#side-menu-link-sign-in")
    SelenideElement signIn;

    @FindBy(css = "#side-menu-link-my-profile")
    SelenideElement myProfile;
    
    @FindBy(css = "#side-menu-link-browse-books")
    SelenideElement browseboks;
    
   

    public void navigateToAddUser() {
  
        clickButton("Add user", addUser);
    }

    public void navigateToSignIn() {
        
        clickButton("Sign in", signIn);
    }

    public void navigateToMyProfile() {
         clickButton("My profile", myProfile);
    }
    public void nabigateToBrowseBooks(){
        
        clickButton("SBrowse Books", browseboks);
    }
}
