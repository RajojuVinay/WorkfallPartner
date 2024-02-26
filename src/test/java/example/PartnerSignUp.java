package example;

import example.database.DataBase;
import example.pages.*;
import example.testbase.TestBase;
import example.testutil.XLUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class PartnerSignUp extends TestBase
{
    LandingPage landingPage;
    LoginPage loginPage;
    AboutPage aboutPage;
    ExperiencePage experiencePage;
    ExpertisePage expertisePage;
    DataBase dataBase;

    public PartnerSignUp(){
        super();
    }
    @BeforeClass
    public void setUp() throws IOException
    {
        Initialize();
        landingPage = new LandingPage();
        loginPage = new LoginPage();
        aboutPage = new AboutPage();
        experiencePage = new ExperiencePage();
        expertisePage = new ExpertisePage();
        dataBase = new DataBase();
    }
    @Test(dataProvider = "signup")
    public void signUp(Boolean NewUser,String firstName,String lastName,String email,String password) throws InterruptedException, SQLException, ClassNotFoundException {
        boolean newUser = Boolean.parseBoolean(prop.getProperty("NewUser"));
        if (NewUser)
        {
            landingPage.applicableToApply();
            landingPage.signup(firstName, lastName, email,password);
        }
        else
        {
            loginPage.login(email, password);
        }
        aboutPage.fillingAboutData(prop.getProperty("Gender"), prop.getProperty("jobTitle"), prop.getProperty("aboutMe"),prop.getProperty("country"),prop.getProperty("city"), prop.getProperty("mobileNumber"));
        experiencePage.fillingEmploymentDetails(prop.getProperty("expDescp"));
        expertisePage.fillingExpertisePage();
        System.out.println(dataBase.getTestDetails());
    }

    @DataProvider(name="signup")
    public Object[][] getData(){
    return new Object[][]{{true,"vinaym46","paartner","vinaypartnerm46@mailinator.com","Qwerty@123"}};
    }
}
