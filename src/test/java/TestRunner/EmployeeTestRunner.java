package TestRunner;

import Pages.DashboardPage;
import Pages.EmployeePage;
import Pages.LoginPage;
import Setup.Setup;
import Utils.Utils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

public class EmployeeTestRunner extends Setup {
    @BeforeTest
    public void doLogin(){
        driver.get("https://opensource-demo.orangehrmlive.com");
        LoginPage loginPage=new LoginPage(driver);
        String adminUser="admin";
        String adminPass="admin123";
        loginPage.doLogin(adminUser,adminPass);
    }
    @Test (priority = 1, description = "Create new employee 1")
    public void createEmployee1() throws InterruptedException, IOException, ParseException, UnsupportedFlavorException {
        EmployeePage employeePage=new EmployeePage(driver);
        driver.findElement(By.partialLinkText("PIM")).click();
        Utils utils = new Utils();
        utils.geneateRandomData();
        String firstName = utils.getFirstname();
        String lastName = utils.getLastname();
        int randomId = Utils.generateRandomNumber(1000,9999);
        String userName = utils.getFirstname()+randomId;
        String password = "Salman*"+randomId;
        String confirmPassword = password;
        employeePage.createEmployee(firstName,lastName,userName,password,confirmPassword);
        Thread.sleep(5000);
        List<WebElement> headerTitle= driver.findElements(By.className("orangehrm-main-title"));
        Assert.assertTrue(headerTitle.get(0).isDisplayed());

//        Thread.sleep(3000);
//        List<WebElement> getId = driver.findElements(By.className("oxd-input.oxd-input--active"));
//        String id= String.valueOf(getId.get(4));

        Utils.waitForElement(driver,headerTitle.get(0),50);
        if(headerTitle.get(0).isDisplayed()){
            utils.saveJsonList(userName,password);
        }
    }

    @Test (priority = 2, description = "Create new employee 2")
    public void createEmployee2() throws InterruptedException, IOException, ParseException, UnsupportedFlavorException {
        EmployeePage employeePage=new EmployeePage(driver);
        driver.findElement(By.partialLinkText("PIM")).click();
        Utils utils = new Utils();
        utils.geneateRandomData();
        String firstName = utils.getFirstname();
        String lastName = utils.getLastname();
        String id= Utils.pasteValue();
        int randomId = Utils.generateRandomNumber(1000,9999);
        String userName = utils.getFirstname()+randomId;
        String password = "Salman*"+randomId;
        String confirmPassword = password;
        employeePage.createEmployee(firstName,lastName,userName,password,confirmPassword);
        Thread.sleep(5000);
        List<WebElement> headerTitle= driver.findElements(By.className("orangehrm-main-title"));
        Assert.assertTrue(headerTitle.get(0).isDisplayed());

        Utils.waitForElement(driver,headerTitle.get(0),50);
        if(headerTitle.get(0).isDisplayed()){
            utils.saveJsonList(userName,password);
        }
    }

    @AfterTest
    public void logout() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.btnProfileIcon.click();
        driver.findElement(By.partialLinkText("Logout")).click();
    }

}
