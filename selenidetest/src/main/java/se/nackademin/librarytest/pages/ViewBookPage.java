
package se.nackademin.librarytest.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class ViewBookPage extends MenuPage{

    @FindBy(css = "#gwt-uid-3") 
    SelenideElement titleField;

    @FindBy(css = "#gwt-uid-5")
    SelenideElement authorFiled;

    @FindBy(css = "#gwt-uid-7")
    SelenideElement descriptionFiled;

    @FindBy(css = "#gwt-uid-11")
    SelenideElement publishDateFiled;

    @FindBy(css = "#gwt-uid-15")
    SelenideElement totNbrOfBooksFiled;

    @FindBy(css = "#gwt-uid-13")
    SelenideElement availebleNbrOfBooksFiled;

    @FindBy(css = "#edit-book-button")
    SelenideElement editBookButton;

    @FindBy(css = "#borrow-book-button")
    SelenideElement borrowBookButton;

    @FindBy(css = "#return-book-button") 
    SelenideElement returnBookButton;
    
    @FindBy(css = "#delete-book-button")
    SelenideElement deleteBookButton;
          

    public String getTitle() {
        return titleField.getText();
    }

    public String getAuthor() {
        return authorFiled.getText();
    }

    public String getDescription() {
        return descriptionFiled.getText();
    }

    public String getPublishDate() {

//        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
//        try {
//            return formatter.parse(publishDateFiled.getText());
//
//        } catch (ParseException ex) {
//
//            Logger.getLogger(ViewBookPage.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
        return publishDateFiled.getText();
    }

    public Integer getTotNbrOfBooks() {
        return Integer.parseInt(totNbrOfBooksFiled.getText());
    }

    public Integer getAvailebleNbrOfBooks() {
       return Integer.parseInt(availebleNbrOfBooksFiled.getText());        
    }

    public void clickEditBookButton() {
        
        clickButton("edit book button", editBookButton);
    }
    public void clickDeleteBookButton(){
        
        clickButton("delete book button", deleteBookButton);
        
    }

    public void clickBorrowBookButton() {

       clickButton("Borrow book button", borrowBookButton);       
    }

    public void clickReturnBookButton() {
        
        clickButton("return book button", returnBookButton);
    }
}
