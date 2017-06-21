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
public class SignInPage extends MenuPage{

    @FindBy(css = "#input-username")
    SelenideElement userNameFiled;

    @FindBy(css = "#input-password")
    SelenideElement passWordFiled;

    @FindBy(css = "#login-button")
    SelenideElement logInButton;
    
    public void setUserName(String userName){
        userNameFiled.clear();
        userNameFiled.sendKeys(userName);
    }
    
    public void setPassWordFiled(String passWord){
        passWordFiled.clear();
        passWordFiled.sendKeys(passWord);         
    }
    
    public void clickLogIn(){
        logInButton.click();
    }
    
}
