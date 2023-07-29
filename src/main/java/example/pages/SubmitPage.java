package example.pages;

import example.testbase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmitPage extends TestBase {
    @FindBy(css=".fourth-submit-section button[type='button']")
    WebElement submitApplication;

    @FindBy(css=".confirm-modal-container button")
    WebElement verifyEmail;


    public SubmitPage(){
        PageFactory.initElements(driver,this);
    }

    public void submitDetails(){
        submitApplication.click();
        if(verifyEmail.isDisplayed())
        {
            verifyEmail.click();
        }
        
    }
}
