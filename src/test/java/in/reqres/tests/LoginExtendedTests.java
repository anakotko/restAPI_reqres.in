package in.reqres.tests;

import in.reqres.models.LoginBodyModel;
import in.reqres.models.LoginResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static in.reqres.specs.LoginSpec.*;

@Tag("reqres_api")
public class LoginExtendedTests extends TestBase {

    @Test
    void successfulLoginPojoTest(){
        LoginBodyModel authData = new LoginBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginResponseModel responce = given()
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
                .extract().as(LoginResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", responce.getToken());
    }

    @Test
    void successfulLoginLombokTest(){
        LoginBodyModel authData = new LoginBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginResponseModel responce = given()
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
                .extract().as(LoginResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", responce.getToken());
    }

    @Test
    void successfulLoginCustomAllureTest(){
        LoginBodyModel authData = new LoginBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginResponseModel responce = given()
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
                .extract().as(LoginResponseModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", responce.getToken());
    }

    @Test
    void successfulLoginWithStepsTest(){
        LoginBodyModel authData = new LoginBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginResponseModel responce = step("Make request", ()->
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
                .extract().as(LoginResponseModel.class));

        step("Check response", ()->
        assertEquals("QpwL5tke4Pnpja7X4", responce.getToken()));
    }

    @Test
    void successfulLoginWithSpecsTest(){
        LoginBodyModel authData = new LoginBodyModel("eve.holt@reqres.in", "cityslicka");

        LoginResponseModel responce = step("Make request", ()->
                given(requestSpec)
                        .body(authData)

                .when()
                        .post("/login")

                .then()
                        .spec(responceSpec(200))
                        .extract().as(LoginResponseModel.class));

        step("Check response", ()->
                assertEquals("QpwL5tke4Pnpja7X4", responce.getToken()));
    }
}
