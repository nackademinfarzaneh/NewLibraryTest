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
public class AddBookPage extends MenuPage {

    @FindBy(css = "#gwt-uid-3")
    SelenideElement booktitleFiled;

    @FindBy(css = "#add-book-button")
    SelenideElement addBookButton;

    public void setBooktitle(String booktitle) {

        booktitleFiled.clear();
        booktitleFiled.sendKeys(booktitle);
    }

    public void clickAddBookButton() {

        clickButton("Add book button", addBookButton);
    }

}
