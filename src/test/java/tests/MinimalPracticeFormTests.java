package tests;

import org.junit.jupiter.api.DisplayName;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import static utils.RandomUtils.*;
import static utils.RandomUtils.getRandomNumberPhone;

@DisplayName("Успешная отправка формы регистрации с обязательными полями")
public class MinimalPracticeFormTests extends TestBase{

    String firstName = getRandomFirstName();
    String lastName = getRandomLastName();
    String gender = getRandomGender();
    String userNumber = getRandomNumberPhone();
    RegistrationPage registrationPage = new RegistrationPage();

    @Tag("submit_form")
    @Feature("Форма регистрации студента")
    @Story("Успешная отправка формы")
    @Owner("kotko-an")
    @DisplayName("Успешная отправка формы регистрации с обязательными полями")
    @Test
    void studentRegistrationFormTests() {

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(userNumber)
                .clickSubmitBtn();

        registrationPage.checkResult("Label", "Values")
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber);
    }
}
