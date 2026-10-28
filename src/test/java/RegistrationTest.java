import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.UserModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import steps.UserSteps;

import java.time.Duration;

public class RegistrationTest extends BaseTest {
    private MainPage mainPage;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private UserSteps userSteps;

    private UserModel user;
    private boolean userCreated = false;

    @Before
    public void setUpPages() {
        mainPage = new MainPage(getDriver());
        mainPage.openSite();

        loginPage = new LoginPage(getDriver());
        registerPage = new RegisterPage(getDriver());
        userSteps = new UserSteps();

        String email = "lexichka" + System.currentTimeMillis() + "@yandex.ru";
        user = new UserModel(email, "123456789", "Lixy");
    }

    @Test
    @DisplayName("Проверка успешной регистрации")
    @Description("Проверка, что после успешной регистрации пользователь может авторизоваться")
    public void userRegistrationOkCheck() {
        // 1. Регистрация
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.registrationInput(user);

        userCreated = true;

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));

        LoginPage authPage = new LoginPage(getDriver());
        authPage.setEmail(user.getEmail());
        authPage.setPassword(user.getPassword());
        authPage.clickLogin();

        Assert.assertTrue(mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка появления ошибки при вводе пароля менее 6 символов")
    @Description("Проверка, что при пароле менее 6 символов появляется текст ошибки 'Некорректный пароль'")
    public void registrationWithShortPasswordFailCheck() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();

        UserModel shortPasswordUser = new UserModel("lyusa" + System.currentTimeMillis() + "@yandex.ru",
                "12345", "Testik");

        registerPage.registrationInput(shortPasswordUser);
        Assert.assertTrue(registerPage.errorMessageCheck());
    }

    @After
    public void deleteUser() {
        if (userCreated) {
            String token = userSteps.getAccessToken(user);
            if (token != null) {
                userSteps.deleteUser(token);
            }
        }
    }
}