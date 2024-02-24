package example.pages;

import example.testbase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage extends TestBase {

    @FindBy(css="input[name='email'][type='text']")
    WebElement emailField;

    @FindBy(css="input[name='password']")
    WebElement passwordField;

    @FindBy(css="button[type='submit'][class='full-width-button orange-button']")
    WebElement loginButton;
    @FindBy(css= "#hubspot-conversations-iframe")
    WebElement hubspot;
    @FindBy(css="button[shape='circle']>div")
    WebElement closeHubspot;
    @FindBy(css=".fa")
    WebElement closeFooter;
    @FindBy(css="div[class='cookiesLayout']")
    WebElement footerCookie;

    public LoginPage(){
        PageFactory.initElements(TestBase.driver,this);
    }

    public void login(String emailId,String pwd){
        driver.manage().deleteAllCookies();
        TestBase.driver.get("https://app.testingmonkey.com/partner/login");
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.switchTo().frame(hubspot);
        closeHubspot.click();
        driver.switchTo().parentFrame();
        executeJavaScript("document.querySelector(\"div[class='cookiesLayout']\").style.display='none'");

       // hideElement(footerCookie);
        //closeFooter.click();
        emailField.sendKeys(emailId);
        passwordField.sendKeys(pwd);
//        if(loginButton.isDisplayed()) {
//            loginButton.click();
//        }
        scrollToElement(loginButton);
        loginButton.click();
    }
}
