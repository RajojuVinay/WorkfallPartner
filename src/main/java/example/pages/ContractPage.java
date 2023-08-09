package example.pages;

import example.testbase.TestBase;
import example.testutil.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static example.testutil.TestUtils.currentDay;

public class ContractPage extends TestBase {

    @FindBy(css="span[class='boxOverLay']")
    WebElement yetToSubmit;


    @FindBy(xpath = "//span[contains(text(),'08')]/..")
    List<WebElement> logs;

    @FindBy(css=".dateCss")
    List<WebElement> dateLogs;
    public WebElement currentDayLog(String currentDay) {
        return driver.findElement(By.xpath("//span[contains(text(),'"+currentDay+"')]/../label"));
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

    public void yetToSubmit(){
        if(TestUtils.weekDayVerifier()){
           for(int j=0;j<=dateLogs.size();j++){
              String dayText = dateLogs.get(j).getText()+"1";
               LocalDate day= LocalDate.now().minusDays(1);
               String currentDay=day.format(DateTimeFormatter.ofPattern("dd"));
               String currentMonth= day.format(DateTimeFormatter.ofPattern("MMM"));
               String currentLogDate= day.format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy"));
               if(currentDay.equalsIgnoreCase(dateLogs.get(j).getText())){
                   System.out.println("Its a Weekday");
                   String xpath= "//span[contains(text(),'"+currentDay+"')]/../label/div";
                   String getStatus =  driver.findElement(By.xpath(xpath)).getText();
                   System.out.println(getStatus+"null");
                   if(getStatus.equalsIgnoreCase( "")){
                      currentDayLog(currentDay).click();
                       //waitForElement(submitLog(currentDay,currentMonth));
                       currentDayLogField(currentLogDate).sendKeys(prop.getProperty("log_comments"));
                       //String submitButton = "//button[contains(text(),'MARK ATTENDANCE FOR "+currentDay+" "+currentMonth+"')]";
                       System.out.println("entered commments");
                       //driver.findElement(By.xpath(submitButton)).click();
                       submitLog(currentDay,currentMonth).click();
                   }
                   break;
               }
           }
       }
        else {
            System.out.println("its a Weekend");
        }
    }
}
