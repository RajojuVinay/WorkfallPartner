package example;

import example.pages.LandingPage;
import example.testbase.TestBase;
import example.testutil.XLUtils;
import org.testng.annotations.*;

import java.io.IOException;

public class TestCase extends TestBase {
    LandingPage signupPage;

    @BeforeMethod
    public void setup() throws IOException {
        Initialize();
        signupPage = new LandingPage();

    }
    @Test(dataProvider = "signup")
    public void Testcase(String firstName,String lastName,String email,String password) throws InterruptedException {
        signupPage.applicableToApply();
        signupPage.signup(firstName,lastName,email,password);

    }
    @AfterMethod
    public void teardown(){
        driver.close();
    }
    @DataProvider(name = "signup")
    String[][] getData() throws IOException {
        String path = System.getProperty("user.dir")+"/src/main/java/example/testdata/TestData.xlsx";
        int rownum= XLUtils.getRowCount(path,"Sheet1");
        int colnum=XLUtils.getCellCount(path,"Sheet1",1);
        String signupData[][] = new String[rownum][colnum];
        for(int i=1;i<=rownum;i++){
            for(int j=0;j<colnum;j++) {
                signupData[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }
        return signupData;
    }
}
