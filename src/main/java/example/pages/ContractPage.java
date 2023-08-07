package example.pages;

import example.testbase.TestBase;
import example.testutil.TestUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;

public class ContractPage extends TestBase {

    @FindBy(css="span[class='boxOverLay']")
    WebElement yetToSubmit;

    public ContractPage(){
        PageFactory.initElements(driver,this);
    }

    public void yetToSubmit(){
        if(TestUtils.isWeekday){


        }
    }



}
