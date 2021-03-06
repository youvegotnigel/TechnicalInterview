package appium;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
//import org.kushan.appium.locator.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.AllureListener;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({AllureListener.class})
public class Calculator {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;
    //create a thread driver
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

    @BeforeMethod
    public WebDriver setup() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "31f89655");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "10.0.0");
        //How to find "appPackage" and "appActivity" - check read.me file
        caps.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        caps.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver<MobileElement>(url, caps);
        wait = new WebDriverWait(driver, 10);

        //initialize the driver to create a tread
        tdriver.set(driver);
        return getDriver();
    }

    @Test
    public void verifyAddition(){

        //locators
        By no7button = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.Button[5]");
        By no5button = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.Button[10]");
        By addButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.Button[16]");
        //By equalButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout[2]/android.widget.Button[20]");
        By resultButton = By.id("com.sec.android.app.popupcalculator:id/calc_tv_result");


        //click on 7
        driver.findElement(no7button).click();

        //click on add button
        driver.findElement(addButton).click();

        //click on 5
        driver.findElement(no5button).click();

        //get result
        String sum = driver.findElement(resultButton).getText();
        System.out.println(sum);

        //assert equal to 12
        Assert.assertEquals(sum,"12");
    }


    @Test
    public void test()
    {
        //find locators
        //AppiumLocator.GetAppiumLocators();
    }


    @AfterMethod
    public void teardown() {
        //quit the driver
        driver.quit();
    }

    //pass the driver in thread
    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }
}
