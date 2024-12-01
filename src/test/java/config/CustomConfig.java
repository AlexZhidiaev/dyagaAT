package config;
import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:test.properties"
})
public interface CustomConfig extends Config{

    @Key("api.baseUrl")
    String getBaseAPIUrl();

    @Key("api.login")
    String getLoginApi();

    @Key("api.password")
    String getPasswordApi();

    @Key("ui.baseUrl")
    String getBaseUIUrl();

    @Key("ui.login")
    String getLoginUI();

    @Key("ui.password")
    String getPasswordUI();

    @Key("webdriver.browser")
    @DefaultValue("FIREFOX")
    String getBrowser();

    @Key("selenide.timeout")
    Integer getTimeout();

    @Key("webdriver.browserSize")
    String getBrowserSize();

    @Key("warehouse")
    String getWarehouse();

}
