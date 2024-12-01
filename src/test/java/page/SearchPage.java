package page;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {

    public final SelenideElement searchInput = $x(".//input[@name='pcode']");

    public final SelenideElement searchSubmitButton = $x(".//input[@class='search_code_submit']");

    public List<SelenideElement> foundItems = $$x(".//tr[@class='startSearching']");

    public final SelenideElement searchAlert = $x(".//div[@class='fr-alert fr-alert-warning']");

}
