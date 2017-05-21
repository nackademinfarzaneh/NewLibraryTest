/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import java.util.Date;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author testautom-nack
 */
public class EditBookPage extends MenuPage{

    @FindBy(css = "#gwt-uid-3")
    SelenideElement titleFiled;

    @FindBy(css = "#gwt-uid-7")
    SelenideElement publishedDate;

    @FindBy(css = "#save-book-button")
    SelenideElement saveBookButton;

    public void setPublishedDateFiled(Date datum) {

        publishedDate.clear();
        publishedDate.sendKeys(datum.toString());

    }

    public void clickSaveBookButton() {

        clickButton("Save book button", saveBookButton);
    }

}
