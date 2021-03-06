package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SeleniumTest {

    private WebDriver driver;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
        driver = new ChromeDriver(getChromeOptions()); //pass chrome options to the driver.
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("disable-infobars"); // this is now disable by chrome latest versions
        options.setHeadless(true);
        return options;
    }

    @Test
    public void test01() {
        driver.get("http://the-internet.herokuapp.com/tables");
        String title = driver.getTitle();
        //System.out.println(title);
        Assert.assertEquals(title,"The Internet");
        System.out.println("This is test01");

        //Get row count
        int rowCount = driver.findElements(By.xpath("//*[@id='table1']/tbody/tr")).size();
        //System.out.println(rowCount);

        //Get coloum count
        int colCount = driver.findElements(By.xpath("//*[@id='table1']/tbody/tr[1]/*")).size();
        //System.out.println(colCount);


        // Print the contents of each cell in a for loop
        for(int i=1; i<=rowCount; i++){
            for(int j=1; j<colCount; j++){
                System.out.println(driver.findElement(By.xpath("//*[@id='table1']/tbody/tr["+i+"]/td["+j+"]")).getText());
            }
        }

    }


    @Test
    //do test01() in a different method
    public void test01_With_List_01(){

        driver.get("http://the-internet.herokuapp.com/tables");
        String title = driver.getTitle();
        //System.out.println(title);
        Assert.assertEquals(title,"The Internet");
        System.out.println("This is test01_With_List_01");

        List <WebElement> rows = driver.findElements(By.xpath("//*[@id='table1']/tbody/tr"));

        //use for loop to print list
        for(int i=0; i<rows.size(); i++){
            System.out.println(rows.get(i).getText());
        }

    }

    @Test
    //do test01() in a different method
    public void test01_With_List_02(){

        driver.get("http://the-internet.herokuapp.com/tables");
        String title = driver.getTitle();
        //System.out.println(title);
        Assert.assertEquals(title,"The Internet");
        System.out.println("This is test01_With_List_02");

        List <WebElement> tableRowsList = driver.findElements(By.xpath("//*[@id='table1']/tbody/tr"));

        //use enhanced for loop to print list
        for(WebElement temp : tableRowsList){
            System.out.println(temp.getText());
        }

    }

    @Test
    //do test01() in a different method
    public void test01_With_List_03(){

        driver.get("http://the-internet.herokuapp.com/tables");
        String title = driver.getTitle();
        //System.out.println(title);
        Assert.assertEquals(title,"The Internet");
        System.out.println("This is test01_With_List_03");

        List <WebElement> tableRowsList = driver.findElements(By.xpath("//*[@id='table1']/tbody/tr"));

        Iterator <WebElement> tableRowIterator = tableRowsList.iterator();
        // use while loop to print list
        while (tableRowIterator.hasNext()){
            System.out.println(tableRowIterator.next().getText());
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


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
