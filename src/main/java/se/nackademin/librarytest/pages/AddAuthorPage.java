package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class AddAuthorPage extends MenuPage {

    @FindBy(css = "#gwt-uid-7")
    SelenideElement firstNameFiled;

    @FindBy(css = "#gwt-uid-9")
    SelenideElement lastNameFiled;

    @FindBy(css = "#gwt-uid-3")
    SelenideElement countryFiled;

    @FindBy(css = "#gwt-uid-5")
    SelenideElement biographyFiled;

    @FindBy(css = "#add-author-button")
    SelenideElement addAuthorButton;

    public void setFirstName(String firstName) {

        firstNameFiled.clear();
        firstNameFiled.sendKeys(firstName);
        setTextFieldValue("Author firstname filed", firstName, firstNameFiled);
    }

    public void setLastNameFiled(String lastName) {
        lastNameFiled.clear();
        lastNameFiled.sendKeys(lastName);

    }

    public void setCountryFiled(String countryName) {
        countryFiled.clear();
        countryFiled.sendKeys(countryName);

    }

    public void setBiographyFiled(String biography) {
        biographyFiled.clear();
        biographyFiled.sendKeys(biography);
        
    }

    public void clickAddAuthorButton() {

        clickButton("Add author button", addAuthorButton);
    }

}
