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
public class BrowseAuthorsPage {

    @FindBy(css = "#gwt-uid-3")
    SelenideElement nameFiled;

    @FindBy(css = "#search-authors-button")
    SelenideElement searchAuthorsButton;
   
    @FindBy (css = "td.v-grid-cell:nth-child(1) > a:nth-child(1)")       
    SelenideElement firstResultTitle;      

    public void clickFirstResultTitle() {
        firstResultTitle.click();
    }

    public void setNameFiled(String name) {

 //       nameFiled.clear();
        nameFiled.sendKeys(name);
    }

    public void clickSearchAuthorsButton() {
        searchAuthorsButton.click();
    }

}
