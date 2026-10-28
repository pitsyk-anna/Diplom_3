package pages;

import io.qameta.allure.Step;
import model.UserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //локаторы
    private final By nameInput = By.xpath("//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath("//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");

    private final By errorMessage = By.xpath("//p[text() = 'Некорректный пароль']");

    private final By loginLink = By.xpath("//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Step("Регистрация. Ввод данных. Клик по кнопке Зарегистрироваться")
    public void registrationInput(UserModel user) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(user.getName());
        driver.findElement(emailInput).sendKeys(user.getEmail());
        driver.findElement(passwordInput).sendKeys(user.getPassword());

        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Проверка отображения сообщения об ошибке")
    public boolean errorMessageCheck() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();
    }

    @Step("Клик по ссылке Войти")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}