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

            Weather todayWeather = weatherList.get(0);

            todayWeather.setHumidity(Integer.parseInt(enrichedData.get("humidity").getAsString()));
            todayWeather.setWeatherType(enrichedData.get("skyText").getAsString());
            todayWeather.setWind(enrichedData.get("windText").getAsString().replace("\\",""));

            weatherList.set(0, todayWeather);

        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}