/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author testautom-nack
 */
public class SignOutPage extends MenuPage {
    
        @FindBy(css = "#main-content ")
    SelenideElement mainContentFiled;   

    @FindBy(css = ".v-label") 
    SelenideElement logOutButton;   

   
    public void clickLogOut(){
        logOutButton.click();
    }

    
}
