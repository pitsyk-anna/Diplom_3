import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.*;
import steps.UserSteps;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {
    private MainPage mainPage;
    private UserSteps userSteps;
    private String email;
    private String password;
    private String accessToken;

    @Before
    public void createUserAndInitPages() {
        userSteps = new UserSteps();
        email = "lexichka" + System.currentTimeMillis() + "@yandex.ru";
        password = "password123";

        UserModel user = new UserModel(email, password, "Lexichka");
        var response = userSteps.createUser(user);
        accessToken = response.path("accessToken");

        mainPage = new MainPage(getDriver());
        mainPage.openSite();
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Вход по кнопке Войти в аккаунт на главной")
    @Description("Проверка успешного входа с валидными данными через главную страницу")
    public void loginFromMainPageTest() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        //проверяем, что на главной появилась кнопка Оформить заказ
        assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку Личный кабинет")
    @Description("Проверка успешного входа через кнопку Личный кабинет")
    public void loginFromPersonalAccountTest() {
        mainPage.clickAccountButton();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через ссылку Войти на странице регистрации")
    public void loginFromRegisterPageTest() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.clickLoginLink();

        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через ссылку Войти на странице восстановления пароля")
    public void loginFromRecoverPasswordPageTest() {
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickRecoverPasswordLink(); //переходим на страницу восстановления

        RecoverPasswordPage recoverPage = new RecoverPasswordPage(getDriver());
        recoverPage.clickLoginLink(); //возвращаемся на форму входа

        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        assertTrue(mainPage.isOrderButtonDisplayed());
    }
}
