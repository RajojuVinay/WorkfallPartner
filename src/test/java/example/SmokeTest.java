package example;

import example.pages.ContractPage;
import example.pages.HomePage;
import example.pages.LoginPage;
import example.pages.WorkstreamListPage;
import example.testbase.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class  SmokeTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    WorkstreamListPage workstreamListPage;
    ContractPage contractPage;
    public SmokeTest(){
        super();
    }

    @BeforeClass
    public void setUp() throws IOException {
        Initialize();
        loginPage = new LoginPage();
        homePage = new HomePage();
        workstreamListPage = new WorkstreamListPage();
        contractPage = new ContractPage();

    }

    @Test(priority = 1)
    public void submitLogs() throws InterruptedException {
    loginPage.login(prop.getProperty("email"),prop.getProperty("password"));
    Thread.sleep(3000);
    homePage.workstreams.click();
    Thread.sleep(5000);
    wait.until(ExpectedConditions.visibilityOf(workstreamListPage.workstreamList));
    workstreamListPage.openContract();
    System.out.println("opened successfully");
    driver.close();
    }
}
