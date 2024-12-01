package step;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import page.ProductPage;
import page.SearchPage;
import pojo.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class SearchSteps {
    public final SearchPage searchPage = new SearchPage();
    public final ProductPage productPage = new ProductPage();
    public final ProductSteps productSteps = new ProductSteps();

    @Step("Вводим в поле поиска артикул {0}, нажимаем кнопку поиска")
    public void performSearch(String articleNum) {
        searchPage.searchInput.clear();
        searchPage.searchInput.sendKeys(articleNum);
        searchPage.searchSubmitButton.click();
    }

    @Step("Кликаем по элементу в списке найденных товаров")
    public void clickFoundItem(int foundProductIndex) {
        searchPage.foundItems.get(foundProductIndex).$x(".//div[@class='searchInfoImage']").click();
    }

    @Step("Получаем информацию обо всех продуктах в поиске")
    public List<ProductModel> getAllProducts(String articleNum) {
        performSearch(articleNum);
        if (searchPage.searchAlert.isDisplayed()) {
            return List.of(ProductModel.builder().article(articleNum).price("INVALID ARTICLE").build());
        } else {
            List<ProductModel> allProducts = new ArrayList<>();
            SelenideElement item;
            for (int i = 0; i < searchPage.foundItems.size(); i++) {
                item = searchPage.foundItems.get(i);
                ProductModel productModel = new ProductModel();
                productModel.setName(item.$x(".//td[@class=' caseDescription']").getText());
                productModel.setArticle(articleNum);
                productModel.setManufacturer(item.$x(".//td[@class='caseBrand ']").getText());
                clickFoundItem(i);

                Selenide.switchTo().window(1);
                ProductModel fullInfo = productSteps.getProduct();
                productModel.setWarehouse(fullInfo.getWarehouse());
                productModel.setPrice(fullInfo.getPrice());
                allProducts.add(productModel);
                Selenide.closeWindow();
                Selenide.switchTo().window(0);
            }
            return allProducts;
        }
    }

}
