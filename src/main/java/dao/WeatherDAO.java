package dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import model.Weather;

import java.util.List;

public class WeatherDAO {

    private static WeatherDAO instance;
    private static EntityManagerFactory emf;

    public static WeatherDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new WeartherDAO();
        }
        return instance;
    }

    //CREATE

    //READ

    //UPDATE

    //DELETE
}
