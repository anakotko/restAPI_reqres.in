package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CheckResultComponent {

    private ElementsCollection tableResult = $$("table tr");
    private SelenideElement outputSimpleResult = $("#output");

    public void checkResultFullForm(String key, String value){
        tableResult.findBy(text(key)).shouldHave(text(value));
    }

    public void checkResultSimpleForm(String key, String value){
        outputSimpleResult.$(key).shouldHave(text(value));
    }
}
