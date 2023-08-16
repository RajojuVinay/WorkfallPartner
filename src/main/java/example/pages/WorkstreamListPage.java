package example.pages;

import example.testbase.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class WorkstreamListPage extends TestBase {

    @FindBy(css=".main-content")
    public WebElement workstreamList;

    @FindBy(css=".calender")
    List<WebElement> monthlyContracts;
    @FindBy(css=".monthly-contract .header-w .btn-view-workstream")
    List<WebElement> viewWorkstreamButton;

    @FindBy(css="select[name='contracts_filter']")
    WebElement filterStatus;

    public WorkstreamListPage(){PageFactory.initElements(driver,this);}

    public void openContract(){
        Select select = new Select(filterStatus);
        select.selectByValue(prop.getProperty("Contract_status"));
        int totalContracts = monthlyContracts.size();
            for(int i=0;i<totalContracts;i++) {
                scrollToElement(monthlyContracts.get(i));
                if(monthlyContracts.get(i).isDisplayed()){
                    viewWorkstreamButton.get(i).click();
                    System.out.println("opened workstream"+i);
                    ContractPage contractPage = new ContractPage();
                    contractPage.yetToSubmit();
                    driver.navigate().back();
                    wait.until(ExpectedConditions.visibilityOf(workstreamList));
                }
                scrollToElement(monthlyContracts.get(i));
                System.out.println("scrolled to"+ i);
//                viewWorkstreamButton.get(i).click();
//                System.out.println("opened workstream"+i);
//                driver.navigate().back();
//                wait.until(ExpectedConditions.visibilityOf(workstreamList));
            }
    }
}
