package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeePage {
    @FindBy(className = "oxd-button")
    public List<WebElement> btnAddEmployee;
    @FindBy(css = "[type=submit]")
    public WebElement btnSubmit;
    @FindBy(name = "firstName")
    WebElement txtFirstName;
    @FindBy(name="lastName")
    WebElement txtLastName;
    @FindBy(className = "oxd-switch-input")
//    @FindBy(css = "[type=checkbox]")
    public WebElement toggleButton;

    @FindBy(className = "oxd-input")
    public List<WebElement> txtUserCreds;
    @FindBy(className = "oxd-select-text-input")
    public List<WebElement> dropdownBox;

    public EmployeePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createEmployee(String firstName, String lastName, String userName, String password, String confirmPassword) throws InterruptedException {
        btnAddEmployee.get(2).click();
        txtFirstName.sendKeys(firstName);
        txtLastName.sendKeys(lastName);
        Thread.sleep(5000);
        toggleButton.click();
        txtUserCreds.get(5).sendKeys(userName);
        txtUserCreds.get(6).sendKeys(password);
        txtUserCreds.get(7).sendKeys(confirmPassword);
        btnSubmit.click();
    }
}
