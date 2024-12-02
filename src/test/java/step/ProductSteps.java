package step;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import page.ProductPage;
import pojo.ProductModel;

import java.util.ArrayList;
import java.util.List;

import static config.ConfigValue.WAREHOUSE;

public class ProductSteps {

    public final ProductPage productPage = new ProductPage();

    /***
     * Если первая запись - наш склад, смотрим следующую
     * @return
     */
    @Step("Получаем информацию о первом продукте списка")
    public ProductModel getProduct(){
        ProductModel productModel = new ProductModel();
        Selenide.sleep(4000);
        if(!productPage.productList.isEmpty()){
            if(!productPage.productList.get(0).$x(".//div[@class='distrInfoRoute']/span[@class='fr-text-nowrap']").getText().equals(WAREHOUSE.getValue())){
                productModel.setWarehouse(productPage.productList.get(0).$x(".//div[@class='distrInfoRoute']/span[@class='fr-text-nowrap']").getText());
                productModel.setPrice(
                        productPage.productList.get(0).$x(".//div[contains(@class,'distrInfoPrice')]").getOwnText()
                );
                ///TODO:
            }
            else{
                productModel.setWarehouse(productPage.productList.get(1).$x(".//div[@class='distrInfoRoute']/span[@class='fr-text-nowrap']").getText());
                productModel.setPrice(productPage.productList.get(1).$x(".//div[contains(@class,'distrInfoPrice')]").getText());
            }
        }
        return productModel;
    }


}
