package tests;

import org.junit.jupiter.api.DisplayName;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

@DisplayName("Неуспешная отправка формы регистрации с обязательными полями")
public class UnSuccessPracticeFormTest extends TestBase{

    RegistrationPage registrationPage = new RegistrationPage();

    @Tag("submit_form")
    @Feature("Форма регистрации студента")
    @Story("Неуспешная отправка формы")
    @Owner("kotko-an")
    @DisplayName("Неуспешная отправка формы регистрации с обязательными полями")
    @Test
    void studentRegistrationFormTests() {

        registrationPage.openPage()
                .setFirstName("Alex")
                .setLastName("Morozov")
                .setGender("Male")
                .setUserNumber("3456789876")
                .clickSubmitBtn();

        registrationPage.checkResult("Label", "Values")
                .checkResult("Student Name", "Alex Morozov")
                .checkResult("Gender", "Other")
                .checkResult("Mobile", "3456789876");
    }
}
