package vhAssignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.text.DecimalFormat;

public class GooglExchangeRates {

    private WebDriver driver;

    private double rateForUSD;
    private double rateForCAD;

    private By input_search= By.xpath("//input[@title='Search']");
    private By cite_link = By.xpath("//cite[contains(normalize-space(),'https://www.x-rates.com › table')][1]");
    private By img_xrates_logo = By.xpath("//img[@class='main-logo' and @src='/themes/x-rates/images/xrates_lg_tm.png']");
    private By span_google_ex_rate = By.xpath("//span[@class='DFlfde SwHCTb']");

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.google.com/");

        Assert.assertEquals(driver.getTitle(), "Google", "Title not matching");
    }

    @Test
    public void test1(){

        driver.findElement(input_search).sendKeys("exchange rates table");
        driver.findElement(input_search).sendKeys(Keys.ENTER);

        explicitWaitMethod(cite_link);
        Assert.assertTrue(driver.findElement(cite_link).isDisplayed());

        driver.findElement(cite_link).click();

        explicitWaitMethod(img_xrates_logo);
        Assert.assertTrue(driver.findElement(img_xrates_logo).isDisplayed());

        rateForUSD = Double.valueOf(getExchangeRateForCurrency("US Dollar"));
        rateForCAD = Double.valueOf(getExchangeRateForCurrency("Canadian Dollar"));

        System.out.println("LKR exchange rate for US Dollar       : " + rateForUSD);
        System.out.println("LKR exchange rate for Canadian Dollar : " + rateForCAD);
    }

    @Test(dependsOnMethods={"test1"})
    public void test2(){

        driver.findElement(input_search).sendKeys("LKR to USD");
        driver.findElement(input_search).sendKeys(Keys.ENTER);

        explicitWaitMethod(span_google_ex_rate);
        double googleExRate = Double.valueOf(driver.findElement(span_google_ex_rate).getText());
        System.out.println("Exchange Rate From Google search For USD : " + googleExRate);

        DecimalFormat df = new DecimalFormat("#.###");
        Assert.assertEquals(df.format(googleExRate), df.format(rateForUSD));
    }

    @Test(dependsOnMethods={"test1"})
    public void test3(){

        driver.findElement(input_search).sendKeys("LKR to CAD");
        driver.findElement(input_search).sendKeys(Keys.ENTER);

        explicitWaitMethod(span_google_ex_rate);
        double googleExRate = Double.valueOf(driver.findElement(span_google_ex_rate).getText());
        System.out.println("Exchange Rate From Google search For CAD : " + googleExRate);

        DecimalFormat df = new DecimalFormat("#.###");
        Assert.assertEquals(df.format(googleExRate), df.format(rateForCAD));
    }

    private String getExchangeRateForCurrency(String currency){
        String xpath = "(//table[@class='ratesTable']/tbody/tr/td[contains(text(),'" + currency + "')]/following-sibling::td/a)[1]";
        WebElement element = driver.findElement(By.xpath(xpath));
        return element.getText();
    }

    public void explicitWaitMethod(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
