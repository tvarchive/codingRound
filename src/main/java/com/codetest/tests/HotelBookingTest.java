package com.codetest.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.codetest.helper.StartWebDriver;
import com.codetest.pages.BasePage;
import com.codetest.pages.HotelPage;
import com.codetest.pages.SearchResutPage;

public class HotelBookingTest extends StartWebDriver{

    

    @Test
    public void shouldBeAbleToSearchForHotels()  {
    	
    	//capturing logs from reporter class of testNg        
        driver.get("https://www.cleartrip.com/");
        Reporter.log("launching website",true);
        BasePage bpage= new BasePage(driver);
        Reporter.log("Homepage",true);
        HotelPage hpage = bpage.clickOnHotel();
        Reporter.log("Hotels",true);
        hpage.entertext("Indiranagar, Bangalore");
        Reporter.log("entered text",true);
        hpage.selectdropdown("1 room, 2 adults");
        Reporter.log("selected dropdown",true);
        SearchResutPage srpage = hpage.clickonsearch();
        Assert.assertTrue(srpage.checktitle("Bangalore"));

    }

}
