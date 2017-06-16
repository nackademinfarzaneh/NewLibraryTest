
import static com.codeborne.selenide.Selenide.open;
import org.junit.After;
import org.junit.Before;

public class TestBase {

    @Before
    public void setupp() {

        System.setProperty("webdriver.chrome.driver", "/home/testautom-nack/testautom-nack/seleniumdrivers/chromedriver");
        System.setProperty("selenide.browser", "Chrome");
        open("http://localhost:8080/librarytest");
    }

    @After
    public void teardown() {

    }
}
