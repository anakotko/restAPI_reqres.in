package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.CheckResultComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {

    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesCheckBox = $("#hobbiesWrapper"),
            pictureButton = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            selectState = $("#state"),
            stateInput = $("#react-select-3-input"),
            selectCity = $("#city"),
            cityInput = $("#react-select-4-input"),
            submitBtn = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();
    CheckResultComponent checkResultComponent = new CheckResultComponent();

    @Step("Открываем главную страницу")
    public RegistrationPage openPage(){
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    @Step("Устанавливаем значение в поле First Name")
    public RegistrationPage setFirstName(String value){
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Устанавливаем значение в поле Last Name")
    public RegistrationPage setLastName(String value){
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Устанавливаем значение в поле Email")
    public RegistrationPage setEmail(String value){
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Устанавливаем значение в поле Gender")
    public RegistrationPage setGender(String value){
        genderWrapper.$(byText(value)).click();

        return this;
    }

    @Step("Устанавливаем значение в поле Mobile")
    public RegistrationPage setUserNumber(String value){
        userNumberInput.setValue(value);

        return this;
    }

    @Step("Устанавливаем дату в поле Date of Birth")
    public RegistrationPage setDateOfBirth(String day, String month, String year){
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    @Step("Выбираем предмет")
    public RegistrationPage setSubjects(String value){
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Выбираем хобби")
    public RegistrationPage setHobbies(String value){
        hobbiesCheckBox.scrollIntoView(true).$(byText(value)).click();

        return this;

    }

    @Step("Загружаем картинку")
    public RegistrationPage setPicture(){
        pictureButton.uploadFromClasspath("img.png");

        return this;
    }

    @Step("Устанавливаем значение в поле Address")
    public RegistrationPage setAddress(String value){
        addressInput.setValue(value);

        return this;
    }

    @Step("Выбираем штат")
    public RegistrationPage setState(String value){
        selectState.click();
        stateInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Выбираем город")
    public RegistrationPage setCity(String value){
        selectCity.click();
        cityInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Отправляем форму")
    public void clickSubmitBtn() {
        submitBtn.scrollIntoView(true).click();
    }

    @Step("Проверка таблицы")
    public RegistrationPage checkResult(String key, String value){

        checkResultComponent.checkResultFullForm(key, value);
        return this;
    }
}