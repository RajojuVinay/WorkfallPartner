package example.pages;

import example.testbase.TestBase;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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
    @FindBy(css="#imageInput")
    WebElement uploadPhoto;
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
        Actions action =new Actions(driver);

        Select genderValue = new Select(gender);
        genderValue.selectByVisibleText(selectGender);  //Male  //Female //Others
//        js.executeScript("window.scrollBy(0,150)", "");
//      js.executeScript("arguments[0].scrollIntoView(true);", profilePhoto);
        boolean fileInput = profilePhoto.isDisplayed();
        if (fileInput == true) {
            wait.until(ExpectedConditions.visibilityOf(profilePhoto)).click();
            js.executeScript("document.getElementById('imageInput').style.setProperty('display', 'block', 'important')");
            uploadPhoto.sendKeys(System.getProperty("user.dir") + "/images.jpeg");
            wait.until(ExpectedConditions.visibilityOf(saveImage)).click();
            saveImage.click();
        } else {
            System.out.println("Cant Upload the Profile picture");
        }
        //js.executeScript("arguments[0].scrollIntoView(true);", jobTitle);
        jobTitle.sendKeys(enterJobTitle+Keys.TAB);
        Thread.sleep(5000);
        wait.until(ExpectedConditions.visibilityOf(aboutMe));
        aboutMe.sendKeys(selfDescription+Keys.TAB);
//        action.sendKeys(Keys.TAB);
        Thread.sleep(3000);
        js.executeScript("arguments[0].scrollIntoView(true);", countryDropDown);
        countryDropDown.sendKeys(country + Keys.ENTER);
        if(closeError.isDisplayed())
        {
            closeError.click();
        }
        Thread.sleep(3000);
        cityDropDown.sendKeys(city);
        for (int i = 0; i < cities.size(); i++) {
            cities.get(0).click();
            break;
        }
        wait.until(ExpectedConditions.visibilityOf(phoneNumber));
        phoneNumber.sendKeys("8142745695"+Keys.TAB);
        if(closeError.isDisplayed())
        {
            closeError.click();
        }
       js.executeScript("arguments[0].scrollIntoView(true);", nationalId);
        js.executeScript("document.querySelector('.file-input-label>input').style.display='block'");
        boolean docInput = nationalId.isDisplayed();
        if (docInput == true) {
            nationalId.sendKeys(System.getProperty("user.dir") + "/aadhaarCard.jpg");
        } else {
            System.out.println("Cant Upload the Document");
        }
        Thread.sleep(3000);
            if(closeError.isDisplayed())
            {
                closeError.click();
            }
            addLanguages.click();
            wait.until(ExpectedConditions.visibilityOf(language));
            language.sendKeys("English", Keys.ENTER);
            Thread.sleep(2000);
            proficiency.sendKeys("Full Professional", Keys.ENTER);
//            boolean languageSelected = languageError.isDisplayed();
//            if(languageSelected == true)
//            {
//                cancelLanguageSelection.click();
//            }
        saveButton.click();
        Thread.sleep(5000);
            js.executeScript("arguments[0].scrollIntoView(true);", continueButton);
            continueButton.click();
            if(closeError.isDisplayed())
            {
                closeError.click();
            }
    }
}

