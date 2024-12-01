package common;

import config.WebDriverProviderSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import step.LoginSteps;
import util.ExcelParser;

import static com.codeborne.selenide.Selenide.$x;
import static config.ConfigValue.LOGIN_UI;
import static config.ConfigValue.PASSWORD_UI;

public class BaseTest extends Provider {

    protected final ExcelParser excelParser = new ExcelParser();

    private final LoginSteps loginSteps = new LoginSteps();

    @BeforeAll
    static void preConditions(){
        System.out.println("------START------");
        new WebDriverProviderSelenide().get();
        System.out.println("------LOGIN------");
/*        $x(".//input[@id='login']").sendKeys(LOGIN_UI.getValue());
        $x(".//input[@id='pass']").sendKeys(PASSWORD_UI.getValue());
        $x(".//input[@id='go']").click();*/
    }

    @AfterAll
    static void postConditions(){
        System.out.println("------FINISH------");
    }

}
