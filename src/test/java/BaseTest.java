import data.ApiEndpoints;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import lombok.Getter;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Getter
public class BaseTest {
    private WebDriver driver;

    @Before
    public void setUp() {

        RestAssured.baseURI = ApiEndpoints.BASE_URI;

        String browser = System.getProperty("browser", "chrome");
        System.out.println("Запуск теста для браузера: " + browser);

        if (browser.equalsIgnoreCase("yandex")) {
            startBrowserYandex();
        } else {
            startBrowserChrome();
        }

        driver.manage().window().maximize();
    }

    public void startBrowserChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println("Chrome успешно запущен");
    }

    public void startBrowserYandex() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\annsu\\Downloads\\yandexdriver-26.10.0.1796-win64\\yandexdriver.exe");

        ChromeOptions yandexOptions = new ChromeOptions();

        yandexOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage",
                "--disable-gpu", "--remote-allow-origins=*");

        driver = new ChromeDriver(yandexOptions);
        System.out.println("Яндекс Браузер успешно запущен");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}