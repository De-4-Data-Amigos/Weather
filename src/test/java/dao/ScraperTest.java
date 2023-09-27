package dao;

import org.junit.jupiter.api.Test;
import utils.Scraper;
import model.Weather;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScraperTest {

    @Test
    public void testFetchWeatherData() {

        //Tester på fetchWeatherData fra Scraper-klassen med simple assertions, for at tjekke
        //om dataen bliver hentet korrekt.

        try {
            List<Weather> weatherList = Scraper.fetchWeatherData();

            //Holder dem op imod
            assertNotNull(weatherList);
            assertTrue(weatherList.size() > 0);

            // Løber listen igennem og tjekker på disse parametre
            for (Weather weather : weatherList) {
                assertNotNull(weather.getCity());
                assertNotNull(weather.getTemperature());

                assertEquals("København", weather.getCity());

            }
        } catch (IOException | InterruptedException e) {
            fail("Exception should not be thrown during the test.");
        }
    }
}