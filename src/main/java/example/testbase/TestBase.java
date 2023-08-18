package example.testbase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestBase {
    public static WebDriver driver;
    public static Properties prop;
    public static WebDriverWait wait;

    public TestBase() {

        prop = new Properties();
        FileInputStream fs;
        try {
            fs = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/example/config/config.properties");
            prop.load(fs);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        //FileInputStream fs = new FileInputStream("C:\\Users\\MAHESH\\OneDrive\\Desktop\\Automation\\Smoketest\\src\\test\\java\\workfall\\data.properties");
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void Initialize() throws IOException {

        String browserName = prop.getProperty("browser");
        if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            //log.debug("Opening Edge browser");
        } else if (browserName.equalsIgnoreCase("chrome")) {


            WebDriverManager.edgedriver().setup();
            //System.setProperty("webdriver.chrome.driver","/home/thrymr/Desktop/Automation/Workfall/Drivers/chromedriver");
            //System.setProperty("webdriver.chrome.driver","C:\\Users\\MAHESH\\OneDrive\\Desktop\\Automation\\Workfall\\Drivers\\chromedriver.exe");
            driver = new EdgeDriver();
            //log.debug("Opening Chrome browser");
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            //System.setProperty("webdriver.gecko.driver", "/home/thrymr/Desktop/Hegde Automation/geckodriver");
            driver = new FirefoxDriver();
            //log.debug("Opening Firefox browser");
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,35);

    }
    protected Object executeJavaScript(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script, args);
    }

    protected void waitForElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element)).click();
    }

    protected void waitForElements(List<WebElement> elements,int i){
        wait.until(ExpectedConditions.visibilityOf(elements.get(i)));
    }

    protected void scrollToElement(WebElement element) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        int elementY = element.getLocation().getY();
        int elementHeight = element.getSize().getHeight();
        int centerY = elementY + elementHeight / 2;
        // Get the viewport height
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        long viewportHeight = (long) jsExecutor.executeScript("return window.innerHeight");

        // Calculate the scroll amount to center the element
        long scrollY = centerY - viewportHeight / 2;

        // Scroll to the calculated position
        jsExecutor.executeScript("window.scrollTo(0,arguments[0]);", scrollY);
    }


    public void close() {
        driver.close();
    }


}
