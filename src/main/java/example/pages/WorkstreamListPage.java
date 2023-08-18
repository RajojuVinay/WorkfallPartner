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
    @FindBy(css=".client-contract span")
    List<WebElement> contractId;

    public WorkstreamListPage(){PageFactory.initElements(driver,this);}

    public void openContract() throws InterruptedException {
        Select select = new Select(filterStatus);
        int totalContracts = monthlyContracts.size();
        System.out.println(totalContracts);
        for(int i=1;i<=totalContracts;i++) {
            select.selectByValue(prop.getProperty("Contract_status"));
            Thread.sleep(3000);
            scrollToElement(monthlyContracts.get(i));
            System.out.println("Opening contract Id:"+contractId.get(i).getText());
            if(monthlyContracts.get(i).isDisplayed()){
                viewWorkstreamButton.get(i).click();
               // System.out.println("Opened contract Id:"+contractId.get(i).getText());
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
