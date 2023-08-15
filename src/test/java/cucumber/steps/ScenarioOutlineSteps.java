package cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScenarioOutlineSteps {
    private int initialBillAmount;
    private double taxRate;
    @Given("I have a Customer")
    public void i_have_a_customer() {
    }
    @Given("user enters initial bill amount as {int}")
    public void user_enters_initial_bill_amount_as(Integer initialBillAmount) {
        this.initialBillAmount = initialBillAmount;
    }
    @Given("Sales Tax Rate is {double} percent")
    public void sales_tax_rate_is_percent(Double taxRate) {
        this.taxRate = taxRate;
    }
    private void invokeWebPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");

        ChromeDriver driver = new ChromeDriver();
        String url = null;
        try {
            driver.get("https://www.baidu.com");

            WebElement searchBox = driver.findElement(By.id("kw"));

            WebElement searchButton = driver.findElement(By.id("su"));

            searchBox.sendKeys("Hello kitty");
            searchButton.click();

            url = driver.getCurrentUrl();
            Thread.sleep(3000);
        } catch (Exception e) {
            driver.quit();
        }
        assertTrue(url.contains("Hello%20kitty"));

    }

    @Then("final bill amount calculate is {double}")
    public void final_bill_amount_calculate_is(Double expectedTotalAmount) throws InterruptedException {
        BigDecimal taxRateDecimal = BigDecimal.valueOf(taxRate);
        BigDecimal initialAmountDecimal = BigDecimal.valueOf(initialBillAmount);
        BigDecimal expectedTotalAmountDecimal = new BigDecimal(expectedTotalAmount);
        BigDecimal actualTotalAmountDecimal = taxRateDecimal.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_EVEN).multiply(initialAmountDecimal).add(initialAmountDecimal);
        System.out.println("expectedTotalAmount: " + expectedTotalAmount);
        System.out.println("expected: " + expectedTotalAmountDecimal.toString());
        System.out.println("actual: " + actualTotalAmountDecimal.toString());
        assertEquals(0, expectedTotalAmountDecimal.compareTo(actualTotalAmountDecimal));
    }

}
