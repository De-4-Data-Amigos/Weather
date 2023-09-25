package utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import model.Weather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static javax.management.Query.attr;
public class Scraper {

    public static List<Weather> fetchWeatherData() throws IOException, InterruptedException {
        String url = "https://www.dmi.dk/lokation/show/DK/2618425/København/#9";

        List<Weather> WeatherList = new ArrayList<>();
        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                        "(KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36")
                .get();
        Thread.sleep(1000); // 1 second

        // vi starter med at finde den helt store container, som er en fællesklasse for alt vejrdataen.
        // Vi søger efter id'et, da det er bedst at søge efter id da der kun et et id.
        Elements biggestContainer = doc.select("c18114");

        //Herefter finder vi den mindre kasse som hver data ligger i:
        Elements mediumContainer = doc.select("jss91");

        //Vi løber mediumcontainer igennem som indeholder alt vi skal bruge ifht vejr
        for(Element WeatherContainer : mediumContainer) {
            //Dato

            //Temperatur (min/max)

            // Nedbør

            // Vind

            // UV

            // Luftfugtighed
            
        }


        return WeatherList;
    }
}
