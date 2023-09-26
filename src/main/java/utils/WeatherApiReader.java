package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

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
        String url = "https://vejr.eu/api.php";
        location = location.replace("ø", "%C3%B8");
        String locationParameter = "location="+location;
        String degreesParameter = "degree=C";
        URI uri = appendUri(url, locationParameter);
        uri = appendUri(uri.toString(), degreesParameter);
        System.out.println(uri);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("User-Agent", "Insomnia/2023.5.5")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        JsonObject jObject = new JsonParser().parse(response.body()).getAsJsonObject();


        return jObject;
    }


    private URI appendUri(String uri, String appendQuery) throws URISyntaxException, UnsupportedEncodingException {
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

    private String encodeValue(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
    }

}
