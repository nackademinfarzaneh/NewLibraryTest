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
public class BorrowBooksConfirmPage {

    @FindBy(css = "#confirmdialog-ok-button")
    SelenideElement confirmDialogOKButton;

    @FindBy(css = "#confirmdialog-message")
    SelenideElement confirmDialog_messField;

    @FindBy(css = "#confirmdialog-cancel-button")
    SelenideElement confirmDialogCancelButton;

    public String getConfirmDialogMessage() {
        return confirmDialog_messField.getText();
    }

    public void clickConfirmDialogOKButton() {

        confirmDialogOKButton.click();
    }
    
        public void clickConfirmDialogCancelButtonButton() {

        confirmDialogCancelButton.click();
    }

}
