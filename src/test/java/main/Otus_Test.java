package main;

import components.*;
import data.AuthData;
import data.InputFieldData;
import data.MainRightMenuItemsData;
import exceptions.BrowserNotSupportedException;
import driver.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Otus_Test {

    public WebDriver driver;

    @BeforeAll
    public static void  init() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach

    public void initDriver() throws BrowserNotSupportedException {
        this.driver = new WebDriverFactory().create(DriverManagerType.CHROME, null);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterEach
    public void close() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void testOtusClickSingInButton() {
        new MainPage(driver)
                .open("/");

        new Header2Right(driver)
                .clickSingInButton();

        new ModalWindow(driver)
                .addLogin(AuthData.Login)
                .addPassword(AuthData.Password)
                .clickLogInButton();

        new MainScreen(driver)
                .moveCursorToItem(MainRightMenuItemsData.PersonalAerea);

        new DropdownHeader2(driver)
                .clickDropdownPersonalArea();

        new PersonalArea(driver)
                .setUserDataToInputField(InputFieldData.FNAME, "����")
                .setUserDataToInputField(InputFieldData.FNAMELATIN, "Egor")
                .setUserDataToInputField(InputFieldData.LNAME, "��������")
                .setUserDataToInputField(InputFieldData.LNAMELATIN, "Stepunov")
                .setUserDataToInputField(InputFieldData.BLOGNAME, "stup.eg")
                .setUserDataToInputField(InputFieldData.DATAOFBIRTH, "18.04.1995");

        new BasicInformation(driver)
                .country()
                .city();






    }
}
