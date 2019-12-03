package pom;

import org.openqa.selenium.By;

public class HotelsPOM {

    private static final String LOCATION_INPUT_FIELD = "Tags";
    private static final String SEARCH_BUTTON = "SearchHotelsButton";
    private static final String TRAVELLERS_SECTION = "travellersOnhome";
    private static final String FIRST_SEARCH_RESULT = "#ui-id-1 .list:nth-child(2) >a";
    private static final String START_DATE = ".ui-datepicker-days-cell-over";
    private static final String END_DATE = ".ui-state-active";

    public static By start_date() {
        return By.cssSelector(START_DATE);
    }
    public static By end_date() {
        return By.cssSelector(END_DATE);
    }
    public static By first_search_result() {
        return By.cssSelector(FIRST_SEARCH_RESULT);
    }
    public static By travellers_section() {
        return By.id(TRAVELLERS_SECTION);
    }
    public static By location_inputfield() {
        return By.id(LOCATION_INPUT_FIELD);
    }

    public static By search_button() {
        return By.id(SEARCH_BUTTON);
    }
}
