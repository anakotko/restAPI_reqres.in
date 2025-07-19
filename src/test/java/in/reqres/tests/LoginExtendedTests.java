package in.reqres.tests;

import in.reqres.models.LoginBodyLombokModel;
import in.reqres.models.LoginResponceLombokModel;
import in.reqres.models.LoginBodyModel;
import in.reqres.models.LoginResponceModel;
import org.junit.jupiter.api.Test;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static in.reqres.specs.LoginSpec.*;

public class LoginExtendedTests  {

    @Test
    void successfulLoginPojoTest(){
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponceModel responce = given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

                .when()
                .post("/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponceModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", responce.getToken());
    }

    @Test
    void successfulLoginLombokTest(){
        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponceLombokModel responce = given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()
                .log().body()
                .log().headers()

                .when()
                .post("/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponceLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", responce.getToken());
    }

    @Test
    void successfulLoginCustomAllureTest(){
        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponceLombokModel responce = given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")


                .when()
                .post("/login")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponceLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", responce.getToken());
    }

    @Test
    void successfulLoginWithStepsTest(){
        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponceLombokModel responce = step("Make request", ()->
           given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .log().headers()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")


           .when()
                .post("/login")

           .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponceLombokModel.class));

        step("Check response", ()->
        assertEquals("QpwL5tke4Pnpja7X4", responce.getToken()));
    }

    @Test
    void successfulLoginWithSpecsTest(){
        LoginBodyLombokModel authData = new LoginBodyLombokModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponceLombokModel responce = step("Make request", ()->
                given(requestSpec)
                        .body(authData)

                .when()
                        .post("/login")

                .then()
                        .spec(responceSpec(200))
                        .extract().as(LoginResponceLombokModel.class));

        step("Check response", ()->
                assertEquals("QpwL5tke4Pnpja7X4", responce.getToken()));
    }
}
