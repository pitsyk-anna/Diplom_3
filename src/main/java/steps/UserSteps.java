package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.UserModel;

import static data.ApiEndpoints.*;
import static io.restassured.RestAssured.given;

public class UserSteps {

    @Step("Создание нового пользователя")
    public Response createUser(UserModel user) {
        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(USER_CREATE)
                .then()
                .log().all()
                .extract().response();
    }
    @Step("Получение токена пользователя")
    public String getAccessToken(UserModel user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(USER_LOGIN)
                .then()
                .extract().path("accessToken");
    }

    @Step("Удаление пользователя по токену")
    public void deleteUser(String accessToken) {
        given()
                .log().all()
                .header("Authorization", accessToken)
                .when()
                .delete(USER_DELETE)
                .then()
                .log().all()
                .extract().response();
    }
}
