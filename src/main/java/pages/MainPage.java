package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //локаторы
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']"); //Войти в аккаунт
    private final By accountButton = By.xpath("//a[@href='/account']"); //Личный кабинет

    private final By bunsTab = By.xpath("//span[contains(text(),'Булки')]"); //таб Булки
    private final By saucesTab = By.xpath("//span[contains(text(),'Соусы')]");//таб Соусы
    private final By fillingTab = By.xpath("//span[contains(text(),'Начинки')]");//таб Начинки

    private final By bunsMenu= By.xpath("//h2[text()='Булки']"); //заголовок Булки в меню
    private final By saucesMenu = By.xpath("//h2[text()='Соусы']"); //заголовок Соусы в меню
    private final By fillingsMenu = By.xpath("//h2[text()='Начинки']"); //заголовок Начинки в меню
    private final By orderButton = By.xpath("//button[text()='Оформить заказ']"); //кнопка Оформить заказ при залогине


    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //ожидание
    }
    @Step("Открыть главную страницу")
    public void openSite() {
        driver.get("https://qa-stellarburgers.education-services.ru/");
    }

    @Step("Клик по кнопке Войти в аккаунт")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Клик по кнопке Личный кабинет")
    public void clickAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(accountButton)).click();
    }

    @Step("Клик по табу Булки")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Клик по табу Соусы")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Клик по табу Начинки")
    public void clickFillingTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingTab)).click();
    }

    @Step("Проверка отображения заголовка Булки")
    public boolean isBunsMenuDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bunsMenu)).isDisplayed();
    }

    @Step("Проверка отображения заголовка Соусы")
    public boolean isSaucesMenuDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(saucesMenu)).isDisplayed();
}
    @Step("Проверка отображения заголовка Начинки")
    public boolean isFillingsMenuDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsMenu)).isDisplayed();
    }
    @Step("Проверка отображения кнопки 'Оформить заказ'")
    public boolean isOrderButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
    }
}





