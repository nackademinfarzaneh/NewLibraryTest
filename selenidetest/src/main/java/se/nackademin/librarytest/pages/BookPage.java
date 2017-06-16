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
public class BookPage {
    @FindBy(css = "#gwt-uid-3")
    SelenideElement titleField;
    
    @FindBy( css = "#gwt-uid-5")
    SelenideElement authorFiled;
    
    @FindBy(css = "#gwt-uid-7")
    SelenideElement descriptionFiled;
    
    public String getTitle(){
        return titleField.getText();
    }
    
    public String getAuthor(){
        return authorFiled.getText();
    }
    
    public String getDescription(){
        return descriptionFiled.getText();
    }
    
  
    
}
