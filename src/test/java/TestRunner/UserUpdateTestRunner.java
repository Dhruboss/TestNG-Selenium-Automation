package TestRunner;

import Pages.DashboardPage;
import Pages.EmployeePage;
import Pages.LoginPage;
import Setup.Setup;
import Utils.Utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class UserUpdateTestRunner extends Setup {
    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        driver.get("https://opensource-demo.orangehrmlive.com");
        List data = Utils.readJSONArray("./src/test/resources/Users.json");
        LoginPage loginPage = new LoginPage(driver);
        JSONObject userObj = (JSONObject) data.get(data.size() - 1);
        String username = (String) userObj.get("userName");
        String password = (String) userObj.get("password");
        loginPage.doLogin(username, password);
        String urlActual = driver.getCurrentUrl();
        String urlExpected = "dashboard/index";
        Assert.assertTrue(urlActual.contains(urlExpected));
    }
    @Test
    public void updateUserInfo() {
        driver.findElement(By.partialLinkText("My Info")).click();
        List<WebElement> headerTitle = driver.findElements(By.className("orangehrm-main-title"));
        Utils.waitForElement(driver, headerTitle.get(0), 50);
        if (headerTitle.get(0).isDisplayed()) {
            EmployeePage employeePage = new EmployeePage(driver);
            employeePage.dropdownBox.get(0).click();
            employeePage.dropdownBox.get(0).sendKeys("b");
            employeePage.dropdownBox.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.dropdownBox.get(0).sendKeys(Keys.ARROW_DOWN);
            employeePage.dropdownBox.get(0).sendKeys(Keys.ENTER);
            employeePage.dropdownBox.get(1).click();
            employeePage.dropdownBox.get(1).sendKeys("m");
            employeePage.dropdownBox.get(1).sendKeys(Keys.ENTER);

            Utils.doScroll(driver);
            List<WebElement> buttons = driver.findElements(By.cssSelector("[type=submit]"));
            buttons.get(0).click();
        }
    }

    @AfterTest
    public void logout() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.btnProfileIcon.click();
        driver.findElement(By.partialLinkText("Logout")).click();
    }
}
