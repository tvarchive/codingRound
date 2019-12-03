package pom;

import org.openqa.selenium.By;

public class SearchSummaryPOM {

    private static final String SEARCH_SUMMARY = "searchSummary";

    public static By search_summary() {
        return By.className(SEARCH_SUMMARY);
    }
}
