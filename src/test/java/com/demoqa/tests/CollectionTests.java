package com.demoqa.tests;


import com.codeborne.selenide.Selenide;
import com.demoqa.models.AddBookBodyModel;
import com.demoqa.models.LoginBodyModel;
import com.demoqa.models.LoginResponseModel;
import com.demoqa.models.UserResponseModel;
import com.demoqa.pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.demoqa.spec.BaseSpec.requestSpec;
import static com.demoqa.spec.BaseSpec.responceSpec;
import static com.demoqa.tests.TestData.login;
import static com.demoqa.tests.TestData.password;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("demoqa_api")
public class CollectionTests extends TestBase {

    @DisplayName("Успешное удаление добавленной книги из коллекции")
    @Test
    void addAndDeleteBookTest() {
        ProfilePage profilePage = new ProfilePage();

       LoginBodyModel loginData = new LoginBodyModel(login, password);
//        loginData.setUserName(login);
//        loginData.setPassword(password);
        LoginResponseModel authResponse = step ("Login request", () ->
                given(requestSpec)
                .body(loginData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responceSpec(200))
                .extract().as(LoginResponseModel.class));

        step ("Delete all books from User Collections", () -> {
                    given(requestSpec)
                            .header("authorization", "Bearer " + authResponse.getToken())
                            .queryParams("UserId", authResponse.getUserId())
                            .when()
                            .delete("/BookStore/v1/Books")
                            .then()
                            .spec(responceSpec(204));
        });

        AddBookBodyModel addBookData = new AddBookBodyModel(
                authResponse.getUserId(),
                List.of(new AddBookBodyModel.IsbnItem("9781449325862")));

        step("Add book to UserCollection", () -> {
                    given(requestSpec)
                            .header("authorization", "Bearer " + authResponse.getToken())
                            .body(addBookData)
                            .when()
                            .post("/BookStore/v1/Books")
                            .then()
                            .spec(responceSpec(201));
        });

        step("Set loginCookies on UI", () -> {
                    open("/favicon.ico");
                    getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
                    getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
                    getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        });

        step("Delete book from User Collection", () -> {
            profilePage.openPage()
                    .checkUserNameValue(login)
                    .clickOnDeleteFirstBookBtn()
                    .clickBtnOkInModal();
            Selenide.confirm();
            profilePage.checkListOfBooksIsEmpty();
        });

        UserResponseModel userResponse = step("Make request - user collection", () ->
                given(requestSpec)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .when()
                .get("/Account/v1/User/" + authResponse.getUserId())
                .then()
                .spec(responceSpec(200))
                .extract().as(UserResponseModel.class));

        step("Verify books collection is empty", () -> {
            assertThat(userResponse.getBooks())
                    .as("Проверка, что коллекция книг пользователя пуста")
                    .isEmpty();
        });
    }
}
