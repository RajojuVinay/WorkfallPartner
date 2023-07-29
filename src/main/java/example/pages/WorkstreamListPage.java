package example.pages;

import example.testbase.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WorkstreamListPage extends TestBase {

    @FindBy(css=".main-content")
    public WebElement workstreamList;

    @FindBy(css=".contract-activity")
    List<WebElement> monthlyContracts;
    @FindBy(css=".monthly-contract .header-w .btn-view-workstream")
    List<WebElement> viewWorkstreamButton;

    public WorkstreamListPage(){PageFactory.initElements(driver,this);}

    public void openContract(){
        int totalContracts = monthlyContracts.size();
            for(int i=0;i<totalContracts;i++) {
                if(monthlyContracts.get(i).isDisplayed()){
                    viewWorkstreamButton.get(i).click();
                    System.out.println("opened workstream"+i);

                    driver.navigate().back();
                    wait.until(ExpectedConditions.visibilityOf(workstreamList));
                }
                scrollToElement(monthlyContracts.get(i));
                System.out.println("scrolled");
//                viewWorkstreamButton.get(i).click();
//                System.out.println("opened workstream"+i);
//                driver.navigate().back();
//                wait.until(ExpectedConditions.visibilityOf(workstreamList));
            }
    }
}
