package selenium;

import org.testng.annotations.*;

public class TestNG {

    @Test
    public void test1(){
        System.out.println("This is Test 1");
    }

    @Test(enabled = false)
    public void test2(){
        System.out.println("This is Test 2");
    }

    @Test
    public void test3(){
        System.out.println("This is Test 3");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("This is Before Test");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("This is After Test");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("This is Before Method");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("This is After Method");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("This is Before Class");
    }

    @AfterClass
    public void afterClas(){
        System.out.println("This is After Class");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("This is Before Suite");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("This is After Suite");
    }

    @BeforeGroups
    public void beforeGroups(){
        System.out.println("This is Before Groups");
    }

    @AfterGroups
    public void afterGroups(){
        System.out.println("This is After Groups");
    }




}
