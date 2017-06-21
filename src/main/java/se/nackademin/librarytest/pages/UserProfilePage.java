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
public class UserProfilePage extends MenuPage {

    @FindBy(css = "#gwt-uid-5")
    SelenideElement userNameFiled;

    @FindBy(css = "#gwt-uid-7")
    SelenideElement userFirstNameFiled;

    @FindBy(css = "#gwt-uid-9")
    SelenideElement userLastNameFiled;

    @FindBy(css = "#gwt-uid-13")
    SelenideElement emailFiled;

    @FindBy(css = "#edit-user")
    SelenideElement editUserButton;

    @FindBy(css = "#gwt-uid-3")
    SelenideElement bookLoanFiled;

    @FindBy(css = "#delete-user-button")
    SelenideElement deleateUserButton;

    // @FindBy(css = "td.v-grid-cell:nth-child(1) > a:nth-child(1)")
    @FindBy(css = "td.v-grid-cell:nth-child(1)")
    SelenideElement bookTitleField;

    @FindBy(css = "td.v-grid-cell:nth-child(2)") 
    SelenideElement dateAvBorrowField;

    @FindBy(css = "td.v-grid-cell:nth-child(3)")
    SelenideElement dateAvDueField;

    public String getUserName() {
        return userNameFiled.getText();
    }

    public String getUserFirstName() {
        return userFirstNameFiled.getText();
    }

    public String getUserLastName() {
        return userLastNameFiled.getText();
    }

    public String getBookTitle() {
        return bookTitleField.getText();
    }

    public String getDateAvBookBorrow() {
        return dateAvBorrowField.getText();
    }

    public String getDateAvBookDue() {
        return dateAvDueField.getText();
    }

    public String getBookLoanFiled() {

        return bookLoanFiled.getText();
    }

    public String getEmailFiled() {
        return emailFiled.getText();

    }

    public void clickEditUserButton() {

        clickButton("Edit user button", editUserButton);
    }

    public void clickDeleateUserButton() {

        clickButton("Deleat user", deleateUserButton);
    }

    public void clickFirstResultBookLoan() {
        bookTitleField.click();
    }

}
