package dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import model.Weather;

import java.util.List;

public class MovieDAO {

    private static WeatherDAO instance;
    private static EntityManagerFactory emf;

    public static MovieDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieDAO();
        }
        return instance;
    }

    //CREATE

    //READ

    //UPDATE

    //DELETE
}
