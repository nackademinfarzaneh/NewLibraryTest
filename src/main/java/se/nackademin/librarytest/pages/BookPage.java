/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import static java.lang.String.format;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.support.FindBy;

/**
 *
 * @author testautom-nack
 */
public class BookPage {

    @FindBy(css = "#gwt-uid-3")
    SelenideElement titleField;

    @FindBy(css = "#gwt-uid-5")
    SelenideElement authorFiled;

    @FindBy(css = "#gwt-uid-7")
    SelenideElement descriptionFiled;

    @FindBy(css = "#gwt-uid-11")
    SelenideElement publishDateFiled;

    @FindBy(css = "#gwt-uid-15")
    SelenideElement totNbrOfBooksFiled;

    @FindBy(css = "#gwt-uid-13")
    SelenideElement availebleNbrOfBooksFiled;

    @FindBy(css = "#edit-book-button")
    SelenideElement editBookButton;

    @FindBy(css = "#borrow-book-button")
    SelenideElement borrowBookButton;

    @FindBy(css = "#confirmdialog-ok-button")
    SelenideElement confirmDialogOKButton;

    @FindBy(css = "#return-book-button")
    SelenideElement returnBookButton;

    public String getTitle() {
        return titleField.getText();
    }

    public String getAuthor() {
        return authorFiled.getText();
    }

    public String getDescription() {
        return descriptionFiled.getText();
    }

    public Date getPublishDate() {

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
        try {
            return formatter.parse(publishDateFiled.getText());

        } catch (ParseException ex) {

            Logger.getLogger(BookPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer getTotNbrOfCopies() {
        return Integer.parseInt(totNbrOfBooksFiled.getText());
    }

    public Integer getAvailebleNbrOfCopies() {
        return Integer.parseInt(availebleNbrOfBooksFiled.getText()) ;
    }

    public void clickEditBookButton() {
        editBookButton.click();
    }

    public void clickBorrowBookButton() {

        borrowBookButton.click();
    }

    public void clickConfirmDialogOKButton() {

        confirmDialogOKButton.click();
    }

    public void clickReturnBookButtonButton() {

        returnBookButton.click();
    }
}
