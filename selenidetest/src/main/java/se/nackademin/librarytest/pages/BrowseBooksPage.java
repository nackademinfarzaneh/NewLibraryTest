package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class BrowseBooksPage extends MenuPage {

    @FindBy(css = "#gwt-uid-3")
    SelenideElement titleFiled;

    @FindBy(css = "#search-books-button")
    SelenideElement searchBooksButton;

    @FindBy(css = "td.v-grid-cell:nth-child(1) > a:nth-child(1)")
    SelenideElement firstResultTitle;

    public void clickFirstResultTitle() {
        firstResultTitle.click();
    }

    public void setTitleFiled(String title) {

        titleFiled.clear();
        titleFiled.sendKeys(title);
    }

    public String getTitleFiled() {
        return titleFiled.getText();
    }

    public void clickSearchBooksButton() {
        searchBooksButton.click();
    }
}
