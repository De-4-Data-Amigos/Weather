package dao;

import jakarta.persistence.EntityManagerFactory;

public class WeatherDAOImpl implements IWeatherDAO {

    private static IWeatherDAO instance;
    private static EntityManagerFactory emf;

    public static IWeatherDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new WeatherDAOImpl();
        }
        return instance;
    }

    //CREATE

    //READ

    //UPDATE

    //DELETE
}
