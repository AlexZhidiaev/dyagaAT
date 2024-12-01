package config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

@Getter
@AllArgsConstructor
public enum ConfigValue {

    URL_API(ConfigFactory.create(CustomConfig.class, System.getProperties()).getBaseAPIUrl()),
    LOGIN_API(ConfigFactory.create(CustomConfig.class, System.getProperties()).getLoginApi()),
    PASSWORD_API(ConfigFactory.create(CustomConfig.class, System.getProperties()).getPasswordApi()),

    URL_UI(ConfigFactory.create(CustomConfig.class, System.getProperties()).getBaseUIUrl()),
    LOGIN_UI(ConfigFactory.create(CustomConfig.class, System.getProperties()).getLoginUI()),
    PASSWORD_UI(ConfigFactory.create(CustomConfig.class, System.getProperties()).getPasswordUI()),
    WAREHOUSE(ConfigFactory.create(CustomConfig.class, System.getProperties()).getWarehouse());
    private final String value;
}
