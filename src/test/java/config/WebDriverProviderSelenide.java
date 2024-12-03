package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.open;

public class WebDriverProviderSelenide {

    private final CustomConfig config;

    public WebDriverProviderSelenide() {
        this.config = ConfigFactory.create(CustomConfig.class, System.getProperties());
    }

    public void get() {
        Configuration.browser = config.getBrowser();
        Configuration.browserCapabilities = setChromeOptions();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = config.getTimeout();
        Configuration.baseUrl = config.getBaseUIUrl();
        //Configuration.headless=true;
        open(config.getBaseUIUrl());
    }

    /**
     * задаем ChromeOptions
     */
    private ChromeOptions setChromeOptions() {
        HashMap<String, Object> prefs = new HashMap<>();
/*        if (config.driverManagerEnabled()) {
            prefs.put("download.default_directory", System.getProperty("user.dir") + "/src/test/resources/download"
                    .replace("/", System.getProperty("os.name").toLowerCase().contains("windows") ? "\\" : "/"));
        } else {
            prefs.put("download.default_directory", "Downloads");
        }*/
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.automatic_downloads", 1);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--host-rules=MAP *.googleapis.com 127.0.0.1");
        options.addArguments("--force-device-scale-factor=1.0");
        options.setExperimentalOption("prefs", prefs);
        return options;
    }
}