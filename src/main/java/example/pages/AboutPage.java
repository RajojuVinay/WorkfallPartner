package example.pages;

import example.testbase.TestBase;

import example.testutil.TestUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AboutPage extends TestBase {
    @FindBy(css="select[placeholder='gender']")
    WebElement gender;

    @FindBy(css="input[formcontrolname='title']")
    WebElement jobTitle;

    @FindBy(css="textarea[formcontrolname='aboutMe']")
    WebElement aboutMe;

//    @FindBy(css="#profile_pic")
//    WebElement profilePhoto;
    @FindBy(css="ng-select[formcontrolname='country'] input[type='text']")
    WebElement countryDropDown;
    @FindBy(css="ng-select[formcontrolname='city'] input[type='text']")
    WebElement cityDropDown;
    @FindBy(css=".ng-dropdown-panel-items span")
    List<WebElement> cities;
    @FindBy(css="input[formcontrolname='phoneNumber']")
    WebElement phoneNumber;
    @FindBy(css="input[accept='image/*,application/pdf']")
    WebElement nationalId;
    @FindBy(css=".open-popup-button")
    WebElement addLanguages;
    @FindBy(css="ng-select[formcontrolname='language'] input[role='combobox']")
    WebElement language;
    @FindBy(css="ng-select[placeholder='Select proficiency'] input[type='text']")
    WebElement proficiency;
    @FindBy(css=".common-container .orange-button")
    WebElement saveButton;
    @FindBy(css=".input-error-message")
    List<WebElement> missingFieldErrorMessage;
    @FindBy(css=".step-content-left-side button[type='submit']")
    WebElement continueButton;
    @FindBy(css=".selected-languages .selected-lang-container")
    WebElement languagesSelected;
    @FindBy(css="#globalErrorModal .btn-close")
    WebElement closeError;
//    @FindBy(css=".title>span>img")
//    WebElement profilePhoto;
//    @FindBy(css=".image-container>span>img")
//    WebElement profilePhoto;
    @FindBy(css=".image-container")
    WebElement profilePhoto;
    @FindBy(css="label[for='profile_pic']")
    WebElement uploadProfilePic;
    @FindBy(css="#imageInput")
    WebElement submitPhoto;
    @FindBy(css=".saveBtn")
    WebElement saveImage;
    @FindBy(css=".error-message")
    WebElement languageError;
    @FindBy(css=".modal-btn .cancel-button")
    WebElement cancelLanguageSelection;
    public AboutPage(){
        PageFactory.initElements(driver,this);
    }


    public void fillingAboutData(String selectGender, String enterJobTitle, String selfDescription, String country, String city, String mobileNumber) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        scrollToElement(gender);
        //Selecting Gender
        Select genderValue = new Select(gender);
        waitForElement(gender);
        genderValue.selectByVisibleText(selectGender);
//      js.executeScript("window.scrollBy(0,150)", "");
        boolean fileInput = profilePhoto.isDisplayed();
        if (fileInput) {
            wait.until(ExpectedConditions.visibilityOf(profilePhoto));
            do {
                uploadProfilePic.click();
            }
            while(!saveImage.isDisplayed());
            js.executeScript("document.getElementById('imageInput').style.setProperty('display', 'block', 'important')");
            submitPhoto.sendKeys(System.getProperty("user.dir") + "/image.jpg");
            wait.until(ExpectedConditions.visibilityOf(saveImage)).click();
            saveImage.click();
        } else {
            js.executeScript("arguments[0].scrollIntoView();", profilePhoto);

            System.out.println("Cant Upload the Profile picture");
        }
        //js.executeScript("arguments[0].scrollIntoView(true);", jobTitle);

        Thread.sleep(3000);
//        TestUtils.enterTextField(jobTitle,enterJobTitle);
        jobTitle.clear();
        jobTitle.sendKeys(enterJobTitle);
        Thread.sleep(3000);
        scrollToElement(aboutMe);
        wait.until(ExpectedConditions.visibilityOf(aboutMe));
        aboutMe.clear();
        aboutMe.sendKeys(selfDescription);
//        TestUtils.enterTextField(aboutMe,selfDescription);
        Thread.sleep(3000);
        scrollToElement(countryDropDown);
        TestUtils.searchAndSelectValue(countryDropDown,country);
        Thread.sleep(3000);
        cityDropDown.sendKeys(city);
        for (int i = 0; i < cities.size(); i++) {
            Thread.sleep(1000);
            cities.get(0).click();
            break;
        }
        Thread.sleep(1000);
        //phone Number
        waitForElement(phoneNumber);
        System.out.println("entering phonenumber");
        phoneNumber.clear();
        phoneNumber.sendKeys(mobileNumber);
//        TestUtils.enterTextField(phoneNumber,mobileNumber);
        System.out.println("entered phone Number");
        js.executeScript("arguments[0].scrollIntoView(true);", nationalId);
        js.executeScript("document.querySelector('.file-input-label>input').style.display='block'");
        boolean docInput = nationalId.isDisplayed();
        if (docInput) {
            nationalId.sendKeys(System.getProperty("user.dir") + "/aadhaarCard.png");
        } else {
            System.out.println("Cant Upload the Document/already Uploaded");
        }
        Thread.sleep(3000);
            scrollToElement(addLanguages);
            addLanguages.click();
            wait.until(ExpectedConditions.visibilityOf(language));
            language.sendKeys("English", Keys.ENTER);
            Thread.sleep(2000);
            proficiency.sendKeys("Full Professional", Keys.ENTER);
           try{ if(languageError.isDisplayed()) {
               cancelLanguageSelection.click();
           }
           }
            catch(Exception e) {
                saveButton.click();
            }
        Thread.sleep(3000);
            js.executeScript("arguments[0].scrollIntoView(true);", continueButton);
            continueButton.click();
        //Assert.assertEquals();
        List<WebElement> errors= new ArrayList<>(missingFieldErrorMessage);
        for(WebElement errorField:errors){
            System.out.println(errorField.getText());
            Assert.assertFalse(errorField.isDisplayed(),"Not Entered proper Data");
        }
        }
}

