package example.pages;

import example.testbase.TestBase;
import example.testutil.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static example.testutil.TestUtils.currentDay;

public class ContractPage extends TestBase {

    WorkstreamListPage workstreamListPage = new WorkstreamListPage();

    @FindBy(css=".dateCss")
    List<WebElement> dateLogs;
    public WebElement currentDayLog(String currentDay) {
        return driver.findElement(By.xpath("//span[contains(text(),'"+currentDay+"')]/../label"));
    }
    public WebElement currentDayLogStatus(String currentDay) {
        return driver.findElement(By.xpath(" //span[contains(text(),'"+currentDay+"')]/../label/div"));
    }
    public WebElement disabledLog(String currentDay) {
        return driver.findElement(By.xpath("//span[contains(text(),'11')]/../label[@class='boxOverLay disableCss']"));
    }


    public WebElement currentDayLogField(String currentLogDate) {
        return driver.findElement(By.xpath("//div[contains(text(),'What did you work on "+currentLogDate+"?')]/parent::div//following-sibling::textarea"));
    }

    public WebElement submitLog(String currentDay,String currentMonth) {
        return driver.findElement(By.xpath("//button[contains(text(),'MARK ATTENDANCE FOR "+currentDay+" "+currentMonth+"')]"));
    }


//    @FindBy(css=".boxOverLay .contractType")
//    List<WebElement> logStatus;
//    @FindBy(css=".reminder")
//    WebElement logPopup;
//    @FindBy(css= ".reminder textarea")
//    WebElement submitLog;


    public ContractPage(){
        PageFactory.initElements(driver,this);
    }

//    public boolean statusDisplayed(){
//        try{
//            WebElement status = currentDayLogStatus(currentDay);
//            return status.isDisplayed();
//        }
//        catch(NoSuchElementException e)
//        {
//        return false;
//        }
//    }

    public void yetToSubmit(){
        if(TestUtils.weekDayVerifier()) {
            for (int j = 0; j <= dateLogs.size(); j++) {
                String dayText = dateLogs.get(j).getText() + "1";
                LocalDate day = LocalDate.now();
                String currentDay = day.format(DateTimeFormatter.ofPattern("dd"));
                String currentMonth = day.format(DateTimeFormatter.ofPattern("MMM"));
                String currentLogDate = day.format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy"));
                System.out.println("Checking the condition currentDay");
                if (currentDay.equalsIgnoreCase(dateLogs.get(j).getText())) {
                    System.out.println("Its a Weekday");
                    // if(statusDisplayed()) {
                    try{
                        if(currentDayLogStatus(currentDay).isDisplayed()) {
                            String getStatus = currentDayLogStatus(currentDay).getText();
                            //System.out.println(getStatus + "null");
                            if (getStatus.equalsIgnoreCase("")) {
                                currentDayLog(currentDay).click();
                                //waitForElement(submitLog(currentDay,currentMonth));
                                currentDayLogField(currentLogDate).sendKeys(prop.getProperty("log_comments"));
                                //String submitButton = "//button[contains(text(),'MARK ATTENDANCE FOR "+currentDay+" "+currentMonth+"')]";
                                System.out.println("Submitted comments");
                                //driver.findElement(By.xpath(submitButton)).click();
                                submitLog(currentDay, currentMonth).click();

                                System.out.println("Submitted "+currentDay+" log for "+workstreamListPage.contractId);
                            }
                        }
                    }
                    catch(NoSuchElementException e){
                        System.out.println("Logs are disabled for "+workstreamListPage.contractId);
                    }
                    break;
                    }
                }
            }
            else
            {
            System.out.println("You can't submit logs for today cause its a Weekend");
            }
        }
    }