package base;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.HomePage;

import java.util.Properties;

public class BaseTest {
    PlaywrightFactory playwrightFactory;
    Properties properties;
    Page page;
    protected HomePage homePage;

    @BeforeTest
    public void setUp() {
        playwrightFactory = new PlaywrightFactory();
        properties = playwrightFactory.initializePropertyFromConfigurationFile();
        page = playwrightFactory.initialiseBrowser(properties);
        homePage = new HomePage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }

}
