package example;

import example.database.DataBase;
import example.pages.*;
import example.testbase.TestBase;
import org.testng.annotations.BeforeClass;
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
    @Test
    public void signUp() throws InterruptedException, SQLException, ClassNotFoundException {
        boolean newUser = Boolean.parseBoolean(prop.getProperty("NewUser"));
        if (newUser == true)
        {
            landingPage.applicableToApply();
            landingPage.submitBasicDetails("vinaym11", "paartner", "vinaypartnerm11@mailinator.com", "Qwerty@123");
        }
        else
        {
        //    loginPage.login("vinaypartnerm9@mailinator.com","Qwerty@123");
        }
        aboutPage.fillingAboutData(prop.getProperty("Gender"), prop.getProperty("jobTitle"), prop.getProperty("aboutMe"),prop.getProperty("country"),prop.getProperty("city"), prop.getProperty("mobileNumber"));
        experiencePage.fillingEmploymentDetails(prop.getProperty("expDescp"));
        expertisePage.fillingExpertisePage();
        System.out.println(dataBase.getTestDetails());
    }
}
