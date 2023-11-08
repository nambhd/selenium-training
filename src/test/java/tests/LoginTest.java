package tests;

import bases.BaseTest;
import dataprovider.LoginDataProvider;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest  extends BaseTest {

    @Test
    public void testLoginSuccess() {
        WebDriver driver = DriverManager.getDriver();
        driver.get(LoginPage.URL);

        LoginPage loginPage = new LoginPage();

        loginPage.inputEmail("user@lavalite.org");
        loginPage.inputPassword("user@lavalite");
        loginPage.submit();
        Assert.assertEquals(driver.getCurrentUrl(),"https://cms.testlery.com/user");
    }

    @Test (dataProvider = "login-success-data", dataProviderClass = LoginDataProvider.class)
    public void testLoginFailed(String username, String password, String errorMessage) {
        WebDriver driver = DriverManager.getDriver();
        driver.get(LoginPage.URL);

        LoginPage loginPage = new LoginPage();

        String message = loginPage.login(username, password);
        Assert.assertEquals(errorMessage, message);
    }
}
