package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherApiReader {

    private static WeatherApiReader instance;
    private HttpClient httpClient;

    public static WeatherApiReader getInstance(){
         if(instance == null){
             instance = new WeatherApiReader();
         }
        return instance;
    }
    private WeatherApiReader(){
        httpClient = HttpClient.newHttpClient();
    }

    public JsonObject getWeatherData(String location) throws URISyntaxException, IOException, InterruptedException {
        String url = "http://vejr.eu/api.php";
        String locationParameter = "location="+location;
        URI uri = appendUri(url, locationParameter);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject jObject = new Gson().fromJson(response.body(), JsonObject.class);

        return jObject;
    }


    private URI appendUri(String uri, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(uri);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        return new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment());
    }

}
