package factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public class PlaywrightFactory {
    Properties properties;
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;

    public Page initialiseBrowser(Properties poperty) {
        playwright = Playwright.create();
        String browserName = poperty.getProperty("browser").trim();
        switch (browserName.toLowerCase(Locale.ROOT)) {
            case "chromium":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "safari":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                break;
            default:
                System.out.println("Pass the right browser name...");
                break;
        }

        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.navigate(poperty.getProperty("url").trim());
        return page;
    }

    public Properties initializePropertyFromConfigurationFile() {
        try {
            FileInputStream readConfigurationFile = new FileInputStream("./src/main/resources/configuration/configuration.properties");
            properties = new Properties();
            properties.load(readConfigurationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
