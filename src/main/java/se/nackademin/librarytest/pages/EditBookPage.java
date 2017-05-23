
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import java.util.Date;
import org.openqa.selenium.support.FindBy;


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
