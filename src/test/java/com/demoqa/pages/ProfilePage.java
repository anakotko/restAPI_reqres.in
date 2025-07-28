package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ProfilePage {

    private SelenideElement userNameValue = $("#userName-value"),
                            deleteFirstBookBtn = $("#delete-record-undefined"),
                            pressOKInModal = $("#closeSmallModal-ok"),
                            noRowsFound = $(".rt-noData");


    @Step("Открываем страницу профиля")
    public ProfilePage openPage(){
        open("/profile");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    @Step("Проверяем значение в User Name")
    public ProfilePage checkUserNameValue(String login){
        userNameValue.shouldHave(text(login));

        return this;
    }

    @Step("Удаляем первую книгу в коллекции")
    public ProfilePage clickOnDeleteFirstBookBtn(){
        deleteFirstBookBtn.click();

        return this;
    }

    @Step("Подтверждаем удаление в модальном окне")
    public ProfilePage clickBtnOkInModal(){
        pressOKInModal.click();

        return this;
    }

    @Step("Проверяем, что список книг пуст")
    public ProfilePage checkListOfBooksIsEmpty() {
        noRowsFound.isDisplayed();
        noRowsFound.shouldHave(text("No rows found"));

        return this;
    }
}
