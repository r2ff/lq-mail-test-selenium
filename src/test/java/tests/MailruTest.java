package tests;

import config.App;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPageBeforeLogin;

import java.time.Duration;

public class MailruTest {

    @Test
    void mailRuTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://mail.ru");
        MainPageBeforeLogin mainPageBeforeLogin = new MainPageBeforeLogin(driver);
        String mailSendMessage = mainPageBeforeLogin.loginAs(App.config.email(), App.config.password())
                .writeMessageTo("whocares2021@mail.ru", "text sub", "textbody");
        Assertions.assertEquals("Письмо отправлено", mailSendMessage);
        driver.quit();
    }
}
