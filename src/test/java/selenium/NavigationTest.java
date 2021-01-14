package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NavigationTest {

    private WebDriver driver;
    private By blogElement = By.linkText("Blog");

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void useDriverGetMethod(){

        driver.get("https://www.amazon.com/");
        //System.out.println(driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");

        //click on blog
        driver.findElement(blogElement).click();
        Assert.assertEquals(driver.getTitle(),"About Amazon");

        //Navigate back to amazon
        driver.navigate().back();
        Assert.assertEquals(driver.getTitle(),"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");

        //Navigate forward to blog
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(),"About Amazon");

    }

    @Test
    public void useDriverNavigateToMethod(){

        driver.navigate().to("https://www.amazon.com/");
        Assert.assertEquals(driver.getTitle(),"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");

        //click on blog
        driver.findElement(blogElement).click();
        Assert.assertEquals(driver.getTitle(),"About Amazon");

        //Navigate back to amazon
        driver.navigate().back();
        Assert.assertEquals(driver.getTitle(),"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");

        //Navigate forward to blog
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(),"About Amazon");

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
