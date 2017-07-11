package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class EditBookPage extends MenuPage {

    @FindBy(css = "#gwt-uid-3")
    SelenideElement titleFiled;

    @FindBy(css = "#gwt-uid-7")
    SelenideElement publishedDate;

    @FindBy(css = "#gwt-uid-5")
    SelenideElement nrOfInventoryFiled;

    @FindBy(css = "#save-book-button") 
    SelenideElement saveBookButton;

    public void setPublishedDateFiled(String datum) {

        publishedDate.clear();
        publishedDate.sendKeys(datum);

    }
        public String getNrOfBook() {
        return nrOfInventoryFiled.getText();
    }

    public void setNrOfInventory(Integer nrOfInventory) {

        this.nrOfInventoryFiled.clear();
        this.nrOfInventoryFiled.sendKeys(nrOfInventory.toString());

    }

    public void clickSaveBookButton() {

        clickButton("Save book button", saveBookButton);
    }
}
