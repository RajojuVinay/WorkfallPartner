package example.testutil;

import example.testbase.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.security.Key;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class TestUtils extends TestBase {

    public static boolean isWeekday;
    public static String currentDay;
    public static String currentMonth;

    public void randomSkills(){
//        for (int k = 0; k < 3; k++) {
//            WebElement searchField = null;
//            searchField.click();
//            HashMap<Object, Object> availableSkills = null;
//            int framSize = availableSkills.size();
//            int randomFrameworks = ThreadLocalRandom.current().nextInt(0, framSize);
//            String fram=availableSkills.get(randomFrameworks).
//            searchField.sendKeys(fram + Keys.ENTER, Keys.TAB);
//        }
    }

    public static boolean weekDayVerifier() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Get the day of the week for the current date
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        // Print the day of the week
        System.out.println("Current day of the week: " + dayOfWeek);

        // Check if it's a weekday (Monday to Friday)
        boolean isWeekday = (dayOfWeek != DayOfWeek.SATURDAY) && (dayOfWeek != DayOfWeek.SUNDAY);

        // Print whether it's a weekday or not
        if (isWeekday) {
            System.out.println("It's a weekday.");
        } else {
            System.out.println("It's a weekend day.");
        }

        return isWeekday;
    }

    public static String currentDay(){
        LocalDate day = LocalDate.now();
        String  currentDay = day.format(DateTimeFormatter.ofPattern("DD"));
      return currentDay;
    }
    public static String currentMonth(){
        LocalDate day = LocalDate.now();
        String  currentMonth = day.format(DateTimeFormatter.ofPattern("MMM"));
        return currentMonth;
    }

    public static void searchAndSelectValue(WebElement element,String value) throws InterruptedException {
        element.click();
        Thread.sleep(1000);
        element.sendKeys(value+ Keys.ENTER);
    }

    public static void enterTextField(WebElement element,String value) throws InterruptedException {
        waitForElement(element);
        element.click();
        // Clear the element only if it's not already empty
        element.clear();
        Thread.sleep(3000);
        element.sendKeys(value);
    }
}
