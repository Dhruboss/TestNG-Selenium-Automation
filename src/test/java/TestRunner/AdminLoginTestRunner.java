package TestRunner;

import Pages.DashboardPage;
import Pages.LoginPage;
import Setup.Setup;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminLoginTestRunner extends Setup {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    @Test(priority = 1, description = "Admin can not login with wrong creds")
    public void doLoginWithWrongCreds(){
        driver.get("https://opensource-demo.orangehrmlive.com");
        loginPage=new LoginPage(driver);
        loginPage.doLogin("sadmin","123456");
        String validationErrorActual= driver.findElement(By.className("oxd-alert-content-text")).getText();
        String validationErrorExpected="Invalid credentials";

        Assert.assertEquals(validationErrorActual,validationErrorExpected);
        //Allure.description("Admin can not login with wrong creds");

    }
    @Test(priority = 2, description = "Admin can login successfully")
    public void doLogin(){
        driver.get("https://opensource-demo.orangehrmlive.com");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("admin","admin123");
        String urlActual=driver.getCurrentUrl();
        String urlExpected="dashboard/index";
        Assert.assertTrue(urlActual.contains(urlExpected));
    }
    @Test(priority = 3, description = "Checking Profile image exist or not")
    public void isProfileImageExists(){
        dashboardPage=new DashboardPage(driver);
        driver.findElement(By.partialLinkText("PIM")).click();
        Assert.assertTrue(dashboardPage.imgProfile.isDisplayed());
    }
}
