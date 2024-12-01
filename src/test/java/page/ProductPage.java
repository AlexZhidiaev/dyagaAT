package page;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

public class ProductPage {

    public List<SelenideElement> productList = $$x(".//div[@id='wFoundGoods']/div[@class='distrInfoBlockWrapper']");

}
