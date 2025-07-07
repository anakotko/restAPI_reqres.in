package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RestAPIReqresTests {

    @Test
    void successfulLoginTest(){
        String authData = "{\"email\": \"eve.holt@reqres.in\",\"password\": \"cityslicka\"}";
        given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

        .when()
                .post("https://reqres.in/api/login")

        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void successfulRegisterTest(){
        String authData = "{\"email\":\"eve.holt@reqres.in\",\"password\": \"pistol\"}";
        given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

        .when()
                .post("https://reqres.in/api/register")

        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void userNotFoundTest(){
        String authData = "{\"email\": \"every78.holt@reqres.in\",\"password\": \"cityslicka\"}";
        given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

        .when()
                .post("https://reqres.in/api/login")

        .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("user not found"));
    }

    @Test
    void createUserTest(){
        String authData = "{\"name\": \"morpheus\",\"job\": \"leader\"}";
        given()
                .body(authData)
                .contentType(JSON)
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

        .when()
                .post("https://reqres.in/api/users")

        .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void singleUserNotFoundTest(){
        given()
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

                .when()
                .get("https://reqres.in/api/users/23")

                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void singleUserTest(){
        given()
                .header("x-api-key", "reqres-free-v1")
                .log().uri()

                .when()
                .get("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.first_name", is("Janet"));
    }
}
