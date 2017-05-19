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
public class AddUserPage extends MenuPage {

    @FindBy(css = "#gwt-uid-3")
    SelenideElement userNameFiled;

    @FindBy(css = "#gwt-uid-5")
    SelenideElement passWordFiled;

    @FindBy(css = "#add-user-button")
    SelenideElement addUserButton;

    public void setUserName(String userName) {

        setTextFieldValue("User name filed", userName, userNameFiled);
    }

    public void setPassWordFiled(String passWord) {
        passWordFiled.clear();
        passWordFiled.sendKeys(passWord);
        setTextFieldValue("Password filed", passWord, passWordFiled);
    }

    public void clickAddUserButton() {

        clickButton("Add user button", addUserButton);
    }

}
