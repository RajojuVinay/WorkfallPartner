package example.pages;

import example.testbase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class LandingPage extends TestBase {
    @FindBy(css="")
    WebElement signUpButton;

    @FindBy(css=".popup-from-container .checkBox")
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
    @FindBy(css=".alert-container")
    WebElement errorMessage;

    @FindBy(css=".left-section-content button[type='submit']")
    WebElement signupButton;
    @FindBy(css="div[class='cookiesLayout']")
    WebElement footerText;

    public LandingPage(){
        PageFactory.initElements(driver,this);
    }
    public void applicableToApply(){
        driver.manage().deleteAllCookies();
        driver.get(prop.getProperty("partnerURL"));
        wait.until(ExpectedConditions.visibilityOfAllElements(checkboxes));
        for(WebElement cb:checkboxes){
            cb.click();
        }
    confirmSignup.click();
    }

    public void signup(String fName,String lName,String email,String pwd) throws InterruptedException {
        firstNameField.sendKeys(fName);
        lastNameField.sendKeys(lName);
        //nextButton.click();
        emailField.sendKeys(email);
        passwordField.sendKeys(pwd);
        wait.until(ExpectedConditions.visibilityOf(termsCheckbox));
        boolean termsCheck = termsCheckbox.isSelected();
        if (!termsCheck) {
            termsCheckbox.click();
        }
        Thread.sleep(2000);
        executeJavaScript("document.querySelector(\"div[class='cookiesLayout']\").style.display='none'");
        scrollToElement(signupButton);
        signupButton.click();
        try {
            System.out.println(errorMessage.getText());
            Assert.assertFalse(errorMessage.isDisplayed(), "cannot register");
        }
        catch (Exception e) {
            System.out.println("No such element");
        }
        finally {
            System.out.println("Redirecting to application form");
        }
    }
}
