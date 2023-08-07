package example.testutil;

import example.testbase.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class TestUtils extends TestBase {

    public static boolean isWeekday;

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

    public boolean weekDayVerifier() {
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
}
