package dat;

import com.google.gson.JsonObject;
import config.HibernateConfig;
import model.Weather;
import utils.Scraper;
import utils.WeatherApiReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        HibernateConfig.addAnnotatedClasses(Weather.class);
//        var emf = HibernateConfig.getEntityManagerFactoryConfig("weather");
//        WeatherDAO weatherDAO = WeatherDAO.getInstance(emf);
        var apiReader = WeatherApiReader.getInstance();

        try {
            List<Weather> weatherList = Scraper.fetchWeatherData();
            JsonObject enrichedData = apiReader.getWeatherData("København");
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}