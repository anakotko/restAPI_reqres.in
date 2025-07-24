package in.reqres.tests;

import in.reqres.models.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static in.reqres.specs.LoginSpec.requestSpec;
import static in.reqres.specs.LoginSpec.responceSpec;

@Tag("reqres_api")
public class RestAPIReqresTests extends TestBase {

    @Test
    @DisplayName("Успешный вход в систему")
    void successfulLoginTest() {
        LoginBodyModel authData = new LoginBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginResponseModel responce = step("Make request: Successful login", ()->
        given(requestSpec)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .spec(responceSpec(200))
                .extract().as(LoginResponseModel.class));

        step("Check token", ()->
                assertEquals("QpwL5tke4Pnpja7X4", responce.getToken()));
    }

    @Test
    @DisplayName("Успешная регистрация")
    void successfulRegisterTest() {
        LoginBodyModel authData = new LoginBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginResponseModel responce = step("Make request: Register new user", ()->
        given(requestSpec)
                .body(authData)
                .when()
                .post("/register")
                .then()
                .spec(responceSpec(200))
                .extract().as(LoginResponseModel.class));

        step("Check token", ()->
                assertEquals("QpwL5tke4Pnpja7X4", responce.getToken()));

        step("Check ID", () ->
                assertThat(responce.getId(),not(emptyOrNullString())));
    }

    @Test
    @DisplayName("Пользователь не найден (неверный email)")
    void userNotFoundTest() {
        LoginBodyModel authData = new LoginBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginErrorResponseModel responce = step("Make request: User not found", ()->
        given(requestSpec)
                .body(authData)
                .when()
                .post("/login")
                .then()
                .spec(responceSpec(400))
                .extract().as(LoginErrorResponseModel.class));

        step("Check error message", () ->
                assertEquals("user not found", responce.getError()));
    }

    @Test
    @DisplayName("Успешное создание нового пользователя")
    void createUserTest() {
        UserBodyModel userData = new UserBodyModel("morpheus", "leader");

        UserResponceModel responce = step("Make request: Create User", ()->
        given(requestSpec)
                .body(userData)
                .when()
                .post("/users")
                .then()
                .spec(responceSpec(201))
                .extract().as(UserResponceModel.class));

        step("Check name", ()->
                assertEquals("morpheus", responce.getName()));

        step("Check job", ()->
                assertEquals("leader", responce.getJob()));

        step("Check ID", () ->
                assertThat(responce.getId(),not(emptyOrNullString())));

        step("Check createdAt", () ->
                assertThat(responce.getCreatedAt(),not(emptyOrNullString())));
    }

    @Test
    @DisplayName("Конкретный пользователь не найден")
    void singleUserNotFoundTest() {
        step("Make request: Single User Not Found", ()->
        given(requestSpec)
                .when()
                .get("/users/23")
                .then()
                .spec(responceSpec(404)));

    }

    @Test
    @DisplayName("Успешное получение данных конкретного пользователя")
    void singleUserTest() {
        SingleUserResponseModel responce = step("Make request: Get single user data", ()->
        given(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(responceSpec(200))
                .extract().as(SingleUserResponseModel.class));

        step("Check ID", ()->
        assertEquals(2, responce.getData().getId()));

        step("Check name", ()->
        assertEquals("Janet", responce.getData().getFirstName()));

    }

    @Test
    @DisplayName("Успешное удаление пользователя")
    void deleteUserTest() {
        step("Make request: Delete user and check status code", ()->
        given(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(responceSpec(204)));
    }

    @Test
    @DisplayName("Полное обновление пользователя (PUT)")
    void updateInfoPutTest() {
        UserBodyModel userPutData = new UserBodyModel("morpheus", "zion resident");

        UserResponceModel responce = step("Make request: Update user information, PUT", ()->
        given(requestSpec)
                .body(userPutData)
                .when()
                .put("/users/2")
                .then()
                .spec(responceSpec(200))
                .extract().as(UserResponceModel.class));

        step("Check name", ()->
                assertEquals("morpheus", responce.getName()));

        step("Check job", ()->
                assertEquals("zion resident", responce.getJob()));

        step("Check updatedAt", () ->
                assertThat(responce.getUpdatedAt(),not(emptyOrNullString())));
    }

    @Test
    @DisplayName("Частичное обновление пользователя (PATCH)")
    void updateInfoPatchTest() {
        UserBodyModel userPatchData = new UserBodyModel("morpheus", "zion resident");

        UserResponceModel responce = step("Make request: Update user information, Patch", ()->
        given(requestSpec)
                .body(userPatchData)
                .when()
                .patch("/users/2")
                .then()
                .spec(responceSpec(200))
                .extract().as(UserResponceModel.class));

        step("Check name", ()->
                assertEquals("morpheus", responce.getName()));

        step("Check job", ()->
                assertEquals("zion resident", responce.getJob()));

        step("Check updatedAt", () ->
                assertThat(responce.getUpdatedAt(),not(emptyOrNullString())));
    }
}
