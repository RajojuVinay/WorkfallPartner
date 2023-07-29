package example.pages;

import example.testbase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static example.testbase.TestBase.driver;

public class HomePage extends TestBase {

    @FindBy(css = "a[routerlink='/user/my-dashboard/monthlyContract/contracts']")
    public WebElement workstreams;


    public HomePage(){
        PageFactory.initElements(driver,this);
    }
}
