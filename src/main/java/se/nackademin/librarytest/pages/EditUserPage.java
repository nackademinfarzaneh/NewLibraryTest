package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

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
