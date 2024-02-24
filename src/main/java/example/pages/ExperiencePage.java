package example.pages;

import example.testbase.TestBase;
import example.testutil.TestUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExperiencePage extends TestBase {
    @FindBy(xpath="//button[text()=' Add employment ']")
    WebElement addEmploymentButton;
    @FindBy(xpath="//button[text()=' Add Certificate ']")
    WebElement addCertificateButton;
    @FindBy(xpath="(//button[@type='submit'])[1]")
    WebElement continueButton;
    @FindBy(xpath="//input[@formcontrolname='jobTitle']")
    WebElement jobTitle;
    @FindBy(xpath = "//input[@formcontrolname='company']")
    WebElement company;
    @FindBy(xpath = "//ng-select[@formcontrolname='industryWorkedList']//input[@type='text']")
    WebElement industryDomain;
    @FindBy(css = "input[formcontrolname='fromDate']")
    WebElement fromDate;
    @FindBy(css = ".checkmark")
    WebElement checkbox;
    @FindBy(css=".simple-textarea")
    WebElement expDescription;
    @FindBy(css="div[formgroupname='employmentDetails'] .orange-button")
    WebElement saveButton;



    public ExperiencePage(){
        PageFactory.initElements(driver,this);
    }
    public void fillingEmploymentDetails(String descp) throws InterruptedException {
        scrollToElement(addEmploymentButton);
        addEmploymentButton.click();
        //jobTitle.sendKeys("QA Engineer");
        TestUtils.enterTextField(jobTitle,"QA Engineer");
        //company.sendKeys("ABDFG");
        TestUtils.enterTextField(company,"ABV Pvt Limited");
        industryDomain.sendKeys("DevTools"+ Keys.ENTER);
        Thread.sleep(2500);
        checkbox.click();
        fromDate.sendKeys("2015-05-11");
        //expDescription.sendKeys(descp);
        TestUtils.enterTextField(expDescription,descp);
        scrollToElement(saveButton);
        saveButton.click();
        Thread.sleep(3000);
        scrollToElement(continueButton);
        continueButton.click();
    }
}
