package main;

import components.*;
import components.dropdown.ModalWindow;
import data.*;
import exceptions.BrowserNotSupportedException;
import driver.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.util.concurrent.TimeUnit;


public class Otus_Test {

    public WebDriver driver;

    @BeforeAll
    public static void init() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach

    public void initDriver() throws BrowserNotSupportedException {
        this.driver = new WebDriverFactory().create(DriverManagerType.CHROME, null);
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
    public void testOtusClickSingInButton() throws BrowserNotSupportedException {
        new MainPage(driver)
                .open("/");

        ModalWindow modalWindow = new ModalWindow(driver);
        modalWindow.modalShouldNotBePresent();

        new Header2Right(driver)
                .clickSingInButton();

        modalWindow.modalShouldBePresent();

        new ModalWindow(driver)
                .addLogin(AuthData.Login)
                .addPassword(AuthData.Password)
                .clickLogInButton();

        new MainScreen(driver)
                .moveCursorToItem(MainRightMenuItemsData.PersonalAerea);

        new DropdownHeader2(driver)
                .clickDropdownPersonalArea();

        new PersonalArea(driver)
                .clearUserDataToInputField(InputFieldData.FNAME)
                .clearUserDataToInputField(InputFieldData.FNAMELATIN)
                .clearUserDataToInputField(InputFieldData.LNAME)
                .clearUserDataToInputField(InputFieldData.LNAMELATIN)
                .clearUserDataToInputField(InputFieldData.BLOGNAME)
                .clearUserDataToInputField(InputFieldData.DATAOFBIRTH)
                .setUserDataToInputField(InputFieldData.FNAME, "????????")
                .setUserDataToInputField(InputFieldData.FNAMELATIN, "Egor")
                .setUserDataToInputField(InputFieldData.LNAME, "????????????????")
                .setUserDataToInputField(InputFieldData.LNAMELATIN, "Stepanov")
                .setUserDataToInputField(InputFieldData.BLOGNAME, "stup.eg")
                .setUserDataToInputField(InputFieldData.DATAOFBIRTH, "18.04.1995");

        new BasicInformation(driver)
                .country()
                .city()
                .englishLevel();

        new ContaintInformation(driver)
                .deletedContact()
                .contactOne(ContactData.TELEGRAM)
                .contactTwo(ContactData.VK);

        new Other(driver)
                .gender(GenderData.MAN)
                .cleanSetCompany(InputOtherData.COMPANY)
                .cleanJobTitle(InputOtherData.WORK)
                .setCompany(InputOtherData.COMPANY, "?????? ????????????????")
                .jobTitle(InputOtherData.WORK, "?????????????? ???? ???????????????????????? 2 ??????????????????");

        new SaveAndContinue(driver)
                .clickSaveAndContinue();

        close();
        initDriver();

        new MainPage(driver)
                .open("/");

        ModalWindow modalWindowReturn = new ModalWindow(driver);
        modalWindowReturn.modalShouldNotBePresent();

        new Header2Right(driver)
                .clickSingInButton();

        modalWindowReturn.modalShouldBePresent();

        new ModalWindow(driver)
                .addLogin(AuthData.Login)
                .addPassword(AuthData.Password)
                .clickLogInButton();

        new MainScreen(driver)
                .moveCursorToItem(MainRightMenuItemsData.PersonalAerea);

        new DropdownHeader2(driver)
                .clickDropdownPersonalArea();

        new PersonalArea(driver)
                .checkUserDataInputField(InputFieldData.FNAME, "????????")
                .checkUserDataInputField(InputFieldData.FNAMELATIN, "Egor")
                .checkUserDataInputField(InputFieldData.LNAME, "????????????????")
                .checkUserDataInputField(InputFieldData.LNAMELATIN, "Stepanov")
                .checkUserDataInputField(InputFieldData.BLOGNAME, "stup.eg")
                .checkUserDataInputField(InputFieldData.DATAOFBIRTH, "18.04.1995");

        new BasicInformation(driver)
                .checkCountry("????????????")
                .checkCity("????????")
                .checkEnglishLevel("?????????????????? ?????????????? (Beginner)");

        new ContaintInformation(driver)
                .checkContactOne("@stupeg")
                .checkContactTwo("vk.com/stuprg");

        new Other(driver)
                .checkGender("??????????????")
                .checkCompany(InputOtherData.COMPANY, "?????? ????????????????")
                .checkJobTitle(InputOtherData.WORK, "?????????????? ???? ???????????????????????? 2 ??????????????????");
    }
}
