package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void test01() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/tables");
        String title = driver.getTitle();
        //System.out.println(title);
        Assert.assertEquals(title,"The Internet");

        //Get row count
        int rowCount = driver.findElements(By.xpath("//*[@id='table1']/tbody/tr")).size();
        //System.out.println(rowCount);

        //Get coloum count
        int colCount = driver.findElements(By.xpath("//*[@id='table1']/tbody/tr[1]/*")).size();
        //System.out.println(colCount);


        // Print the contents of each cell
        for(int i=1; i<=rowCount; i++){
            for(int j=1; j<colCount; j++){
                System.out.println(driver.findElement(By.xpath("//*[@id='table1']/tbody/tr["+i+"]/td["+j+"]")).getText());
            }
        }

    }

    private Select findDropDownElement() {
        By dropdown = By.id("dropdown");
        return new Select(driver.findElement(dropdown));
    }


    public List<String> getSelectedOptions() {
        List<WebElement> selectedElements = findDropDownElement().getAllSelectedOptions();
        return selectedElements.stream().map(e -> e.getText()).collect(Collectors.toList());
    }


    @Test
    public void test02(){
        driver.get("http://the-internet.herokuapp.com/dropdown");
        String title = driver.getTitle();
        //System.out.println(title);

        Assert.assertEquals(title,"The Internet");

        String option1 = "Option 1";
        String option2 = "Option 2";

        getSelectedOptions();
        findDropDownElement().selectByVisibleText(option1);
        findDropDownElement().selectByVisibleText(option2);
    }


    @Test
    public void test03(){
        driver.get("http://the-internet.herokuapp.com/hovers");
        String title = driver.getTitle();
        //System.out.println(title);

        Assert.assertEquals(title,"The Internet");
    }

    @Test
    public void test04(){
        driver.get("http://the-internet.herokuapp.com/windows");
        String title = driver.getTitle();
        //System.out.println(title);

        Assert.assertEquals(title,"The Internet");
    }


    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
