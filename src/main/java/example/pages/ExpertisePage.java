package example.pages;

import example.testbase.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ExpertisePage extends TestBase {
    @FindBy(css = ".contentCard h4")
    List<WebElement> technologies;

    @FindBy(css = "ng-select[formcontrolname='languageKeys'] input[type='text']")
    WebElement searchLanguages;

    @FindBy(xpath = "(//div[@class='column-12'])[1]")
    WebElement viewLanguage;

    @FindBy(css = "ng-select[formcontrolname='languageKeys'] .ng-arrow-wrapper")
    WebElement language;

    @FindBy(css = ".ng-option p[placement='top']")
    List<WebElement> availableLanguages;

    @FindBy(css = "ng-select[formcontrolname='frameWorkKeys'] input[type='text']")
    WebElement searchFrameworks;

    @FindBy(css = ".ng-option p[placement='top']")
    List<WebElement> availableFrameworks;

    @FindBy(css = "ng-select[formcontrolname='techAssessmentId'] input[type='text']")
    WebElement searchTechAssessment;
    @FindBy(css = "ng-select[formcontrolname='techAssessmentId'] .ng-option span")
    List<WebElement> availableTechAsmt;
    @FindBy(css = ".main-title")
    WebElement mainTitle;
    @FindBy(css = ".step-content-left-side button[type='submit']")
    WebElement continueButton;

    public ExpertisePage() {
        PageFactory.initElements(driver, this);
    }

    public void fillingExpertisePage() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.visibilityOfAllElements(technologies));
        js.executeScript("arguments[0].scrollIntoView(true);", mainTitle);
        for (int i = 0; i < 6; i++) {
            int availableTechnologies = technologies.size();
            int randomTech = ThreadLocalRandom.current().nextInt(0, availableTechnologies);
            String techSpheres = technologies.get(randomTech).getText();
            Thread.sleep(3000);
            System.out.println(techSpheres);
            technologies.get(randomTech).click();
            break;
        }
        Thread.sleep(2500);
        js.executeScript("scrollTo(0,270)");
        //js.executeScript("arguments[0].scrollIntoView(true);",viewLanguage);
        searchLanguages.click();
  //      getRandomWebElement(availableLanguages,2);
        for (int j = 0; j < 2; j++) {
            searchLanguages.click();
            int langSize = availableLanguages.size();
            int randomLanguages = ThreadLocalRandom.current().nextInt(0, langSize);
            String lang = availableLanguages.get(randomLanguages).getText();
            System.out.println(lang);
            searchLanguages.sendKeys(lang + Keys.ENTER, Keys.TAB);
        }
        js.executeScript("arguments[0].scrollIntoView(true);",searchFrameworks);
        searchFrameworks.click();
        for (int k = 0; k < 3; k++) {
            searchFrameworks.click();
            int framSize = availableFrameworks.size();
            int randomFrameworks = ThreadLocalRandom.current().nextInt(0, framSize);
            String fram = availableFrameworks.get(randomFrameworks).getText();
            System.out.println(fram);
            searchFrameworks.sendKeys(fram + Keys.ENTER, Keys.TAB);
        }
        searchTechAssessment.click();
        Thread.sleep(2500);
        int techAssmtSize = availableTechAsmt.size();
        int randmNumber = ThreadLocalRandom.current().nextInt(0, techAssmtSize);
        availableTechAsmt.get(randmNumber).click();
        Thread.sleep(2500);
        continueButton.click();
        continueButton.click();
    }

//    private List<WebElement> getRandomWebElement(List<WebElement> webElements, Integer size) {
//        List<WebElement> givenList = webElements;
//        Collections.shuffle(givenList);
//        List<WebElement> randomSeries = givenList.subList(0, size);
//        return randomSeries;
//    }
//
//
//    public static void main(String[] args) {
//        List<Integer> givenList = Arrays.asList(1, 2, 3, 4, 5, 6);
//        Collections.shuffle(givenList);
//
//        int randomSeriesLength = 3;
//
//       List<Integer> randomSeries = givenList.subList(0, randomSeriesLength);
//        System.out.println(randomSeries);
//    }


}
