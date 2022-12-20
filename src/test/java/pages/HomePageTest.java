package pages;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HomePageTest extends BaseTest {

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

}