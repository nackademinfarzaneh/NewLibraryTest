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
public class EditUserPage extends MenuPage {

    @FindBy(css = "#gwt-uid-13")
    SelenideElement emailFiled;

    @FindBy(css = "#save-user-button")
    SelenideElement saveUserButton;

    public void setEmailFiled(String email) {

        emailFiled.clear();
        emailFiled.sendKeys(email);
    }

    public void clickSaveUserButton() {

        clickButton("Edit user button", saveUserButton);
    }

}
