package pages;

import com.microsoft.playwright.Page;
import factory.PlaywrightFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HomePageTest {

    PlaywrightFactory playwrightFactory;
    Page page;
    HomePage homePage;

    @BeforeTest
    public void setUp() {
        playwrightFactory = new PlaywrightFactory();
        page = playwrightFactory.initialiseBrowser("chromium");
        homePage = new HomePage(page);
    }

    @Test
    public void homePageTitle() {
        String actualTitle = homePage.getHomePageTitle();
        assertEquals(actualTitle, "Your Store");
    }

    @DataProvider
    public Object[][] getProductData() {
        return new Object[][]{
                {"Mac"},
                {"Lenovo"},
                {"Samsung"}
        };
    }

    @Test(dataProvider = "getProductData")
    public void searchProduct(String productName) {
        String actualSearchHeader = homePage.doSearch(productName);
        assertEquals(actualSearchHeader, "Search - " + productName);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }

}