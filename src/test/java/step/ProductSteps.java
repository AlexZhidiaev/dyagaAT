package step;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.platform.commons.util.StringUtils;
import page.ProductPage;
import pojo.ProductModel;

import static config.ConfigValue.WAREHOUSE;

public class ProductSteps {

    public final ProductPage productPage = new ProductPage();

    /***
     * Если первая запись - наш склад, смотрим следующую
     * @return
     */
    @Step("Получаем информацию о первом продукте списка")
    public ProductModel getProduct() {
        ProductModel productModel = new ProductModel();
        Selenide.sleep(2500);
        if (!productPage.productList.isEmpty()) {
            if (!productPage.productList.get(0).$x(".//div[@class='distrInfoRoute']/span[@class='fr-text-nowrap']").getText().equals(WAREHOUSE.getValue())
            || productPage.productList.size()<2) {
                productModel.setWarehouse(productPage.productList.get(0).$x(".//div[@class='distrInfoRoute']/span[@class='fr-text-nowrap']").getText());
                productModel.setPrice(
                        StringUtils.isBlank(productPage.productList.get(0).$x(".//div[contains(@class,'distrInfoPrice')]").getOwnText()) ? "NO RESULTS" :
                                productPage.productList.get(0).$x(".//div[contains(@class,'distrInfoPrice')]").getOwnText()
                );
            } else {
                productModel.setWarehouse(productPage.productList.get(1).$x(".//div[@class='distrInfoRoute']/span[@class='fr-text-nowrap']").getText());
                productModel.setPrice(productPage.productList.get(1).$x(".//div[contains(@class,'distrInfoPrice')]").getText());
            }
        }
        return productModel;
    }


}
