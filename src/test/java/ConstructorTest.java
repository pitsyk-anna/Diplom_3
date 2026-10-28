import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {
    private MainPage mainPage;

    @Before
    public void setUpPages() {
        mainPage = new MainPage(getDriver());
        mainPage.openSite();
    }

    @Test
    @DisplayName("Переключение на вкладку «Булки»")
    @Description("Проверка, что при клике на вкладку 'Булки' открывается соответствующий раздел")
    public void switchToBunsTabTest() {
        //вкладка Булки открыта по умолчанию. Чтобы кликнуть по ней, сначала переключимся на Начинки, чтобы сделать её неактивной
        mainPage.clickFillingTab();

        mainPage.clickBunsTab();
        assertTrue(mainPage.isBunsMenuDisplayed());
    }

    @Test
    @DisplayName("Переключение на вкладку «Соусы»")
    @Description("Проверка, что при клике на вкладку 'Соусы' открывается соответствующий раздел")
    public void switchToSaucesTabTest() {
        //переключаемся на Начинки, чтобы уйти с вкладки Булки
        mainPage.clickFillingTab();

        mainPage.clickSaucesTab();
        assertTrue(mainPage.isSaucesMenuDisplayed());
    }

    @Test
    @DisplayName("Переключение на вкладку «Начинки»")
    @Description("Проверка, что при клике на вкладку 'Начинки' открывается соответствующий раздел")
    public void switchToFillingsTabTest() {
        mainPage.clickFillingTab();
        assertTrue(mainPage.isFillingsMenuDisplayed());
    }
}