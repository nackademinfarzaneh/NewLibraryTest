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
public class BrowseBooksPage {

    @FindBy(css = "#gwt-uid-3")
    SelenideElement titleFiled;

    @FindBy(css = "#search-books-button")
    SelenideElement searchBooksButton;
    
    @FindBy(css = "td.v-grid-cell:nth-child(1) > a:nth-child(1)")
    SelenideElement firstResultTitle;
    
    public void clickFirstResultTitle(){
        firstResultTitle.click();
    }

    public void setTitleFiled(String title) {

        titleFiled.clear();
        titleFiled.sendKeys(title);
    }

    public void clickSearchBooksButton() {
        searchBooksButton.click();
    }

}
