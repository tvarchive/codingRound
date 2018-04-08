import org.testng.annotations.Test;
import pages.App;
import pages.HomePage;
import pages.HotelsPage;

public class HotelBookingTest {
    private App app;
    private HomePage homePage;
    private HotelsPage hotelsPage;

    public HotelBookingTest() {
        app = new App();
        this.homePage = new HomePage(app);
        this.hotelsPage = new HotelsPage(app);
    }

    @Test
    public void shouldBeAbleToSearchForHotels() {

        app.launch();
        homePage.openHotelsSearch();
        hotelsPage.waitForPageDisplay();
        hotelsPage.enterLocality("Indiranagar, Bangalore");
        hotelsPage.selectTravellers("1 room, 2 adults");
        hotelsPage.searchHotels();
        app.close();

    }
}
