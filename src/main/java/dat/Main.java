package dat;

import config.HibernateConfig;
import model.Weather;
import utils.Scraper;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        HibernateConfig.addAnnotatedClasses(Weather.class);
//        var emf = HibernateConfig.getEntityManagerFactoryConfig("weather");
//        WeatherDAO weatherDAO = WeatherDAO.getInstance(emf);

        try {
            List<Weather> weatherList = Scraper.fetchWeatherData();
            for (Weather weather : weatherList) {
                //System.out.println("Tidspunkt: " + weather.getTime());
                System.out.println("Temperatur: " + weather.getTemperature());
                System.out.println("Nedbør: " + weather.getDownpour());
                System.out.println("Vejr: " + weather.getWeatherType());

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();


        }
    }
}