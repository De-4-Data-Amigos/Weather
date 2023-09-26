package dat;

import com.google.gson.JsonObject;
import config.HibernateConfig;
import dao.IWeatherDAO;
import dao.WeatherDAOImpl;
import model.Weather;
import utils.Scraper;
import utils.WeatherApiReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        HibernateConfig.addAnnotatedClasses(Weather.class);
        var emf = HibernateConfig.getEntityManagerFactoryConfig("weather");
        IWeatherDAO weatherDAO = WeatherDAOImpl.getInstance(emf);
        var apiReader = WeatherApiReader.getInstance();

        try {
            List<Weather> weatherList = Scraper.fetchWeatherData();
            JsonObject enrichedData = apiReader.getWeatherData("København");

            Weather todayWeather = weatherList.get(0);

            var jsonElement = enrichedData.get("CurrentData").getAsJsonObject();

            todayWeather.setHumidity(Integer.parseInt(jsonElement.get("humidity").getAsString()));
            todayWeather.setWeatherType(jsonElement.get("skyText").getAsString());
            todayWeather.setWind(jsonElement.get("windText").getAsString().replace("\\",""));

            weatherList.set(0, todayWeather);

            weatherDAO.create(weatherList.get(0));

        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}