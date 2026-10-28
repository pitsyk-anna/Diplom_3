package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //локаторы
    private final By profileHeader = By.xpath("//h2[text()='Профиль']"); //заголовок "Профиль" в личном кабинете


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //ожидание
    }


    @Step("Проверка отображения заголовка")
    public boolean isProfileHeaderDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(profileHeader)).isDisplayed();
    }
}
