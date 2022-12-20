package pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private final Page page;
    private static final String searchField = "input[name='search']";
    private static final String searchButton = "div#search button";
    private static final String searchPageHeader = "div#content h1";

    public HomePage(Page page) {
        this.page = page;
    }

    public String getHomePageTitle() {
        return page.title();
    }

    public String doSearch(String productName) {
        page.click(searchField);
        page.fill(searchField, productName);
        page.click(searchButton);
        return page.textContent(searchPageHeader);
    }
}
