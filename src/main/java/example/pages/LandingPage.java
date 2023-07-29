package example.pages;

import example.testbase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LandingPage extends TestBase {
    @FindBy(css="")
    WebElement signUpButton;

    @FindBy(css=".checkmark")
    List<WebElement> checkboxes;

    @FindBy(css=".confirm-popup button[type='submit']")
    WebElement confirmSignup;

    @FindBy(css="input[name='firstName']")
    WebElement firstNameField;

    @FindBy(css="input[name='lastName']")
    WebElement lastNameField;

    @FindBy(css=".left-section-content .orange-button")
    WebElement nextButton;

    @FindBy(css=".left-section-content input[name='email']")
    WebElement emailField;

    @FindBy(css=".left-section-content input[name='password']")
    WebElement passwordField;

    @FindBy(css=".left-section-content .checkmark")
    WebElement termsCheckbox;

    @FindBy(css=".left-section-content button[type='submit']")
    WebElement signupButton;

    public LandingPage(){
        PageFactory.initElements(driver,this);
    }
    public void applicableToApply(){
        driver.manage().deleteAllCookies();
        driver.get("https://wf.testingmonkey.com/partner/signup");
        wait.until(ExpectedConditions.visibilityOfAllElements(checkboxes));
//        for(WebElement cb:checkboxes){
//            cb.click();
//        }
        for(int i=0;i<checkboxes.size();i++)
        {
            if(!checkboxes.get(i).isSelected())
            {
                checkboxes.get(i).click();
            }
        }
    confirmSignup.click();
    }

    public void submitBasicDetails(String fName,String lName,String email,String pwd) throws InterruptedException {
        firstNameField.sendKeys(fName);
        lastNameField.sendKeys(lName);
        nextButton.click();
        emailField.sendKeys(email);
        passwordField.sendKeys(pwd);
        wait.until(ExpectedConditions.visibilityOf(termsCheckbox));
        boolean termscheck = termsCheckbox.isSelected();
        if(termscheck==false)
        {
            termsCheckbox.click();
        }
        Thread.sleep(3000);
        signupButton.click();
    }
}
