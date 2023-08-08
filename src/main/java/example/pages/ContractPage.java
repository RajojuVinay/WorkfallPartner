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
public class ContractPage extends TestBase {

    @FindBy(css="span[class='boxOverLay']")
    WebElement yetToSubmit;


    @FindBy(xpath = "//span[contains(text(),'08')]/..")
    List<WebElement> logs;

    @FindBy(css=".dateCss")
    List<WebElement> dateLogs;

//    @FindBy(css=".boxOverLay .contractType")
//    List<WebElement> logStatus;
    @FindBy(css=".reminder")
    WebElement logPopup;
    @FindBy(css= ".reminder textarea")
    WebElement submitLog;

    public ContractPage(){
        PageFactory.initElements(driver,this);
    }

    public void yetToSubmit(){
        if(TestUtils.weekDayVerifier()){
           for(int i=0;i<=dateLogs.size();i++){
               //System.out.println(dateLogs.get(i).getText()+"1");
               LocalDate day= LocalDate.now();
               String currentDay=day.format(DateTimeFormatter.ofPattern("dd"));
               String currentMonth= day.format(DateTimeFormatter.ofPattern("MMM"));
               if(currentDay.equalsIgnoreCase(dateLogs.get(i).getText())){
                   System.out.println("Its a Weekday");
                   String logStatus= "//span[contains(text(),'"+currentDay+"')]/../label/div";
                   String getStatus =  driver.findElement(By.xpath(logStatus)).getText();
                   System.out.println(getStatus);
                   if(getStatus.equalsIgnoreCase( "")){
                       dateLogs.get(i).click();
                       waitForElement(logPopup);
                       submitLog.sendKeys(prop.getProperty("log_comments"));
                       String submitButton = "//button[contains(text(),'MARK ATTENDANCE FOR "+currentDay+" "+currentMonth+"')]";
                       System.out.println("entered commments");
                       driver.findElement(By.xpath(submitButton)).click();
                       break;
                   }
               }

           }
       }
        else {
            System.out.println("its a Weekend");
        }
    }
}
