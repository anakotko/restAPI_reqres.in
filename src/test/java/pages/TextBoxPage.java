package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CheckResultComponent;

import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {

    CheckResultComponent checkResultComponent = new CheckResultComponent();

    private SelenideElement nameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitBtn = $("#submit");

public TextBoxPage openPage(){
    open("/text-box");

    return this;
}

    public TextBoxPage setName(String value){
        nameInput.setValue(value);

        return this;
    }

    public TextBoxPage setEmail(String value){
        userEmailInput.setValue(value);

        return this;
    }

    public TextBoxPage setCurrentAddress(String value){
        currentAddressInput.setValue(value);

        return this;
    }
    public TextBoxPage setPermanentAddress(String value){
        permanentAddressInput.setValue(value);

        return this;
    }

    public void clickSubmitBtn() {
        submitBtn.click();
    }

    public TextBoxPage checkResult(String key, String value){

        checkResultComponent.checkResultSimpleForm(key, value);
        return this;
    }
}
