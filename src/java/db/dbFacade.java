package db;

import entity.City;
import entity.Country;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;

public class dbFacade {

    private EntityManagerFactory emf;

    public dbFacade() {
        emf = Persistence.createEntityManagerFactory("ExamPreparation_JPA_REST_APIPU");
    }

    public List<Country> getCountries() {
        EntityManager em = emf.createEntityManager();

        List<Country> countries;

        try {
            em.getTransaction().begin();
            
            countries = em.createNamedQuery("Country.findAll").getResultList();
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return countries;
    }
    
    public List<Country> getCountriesWithGreaterPop(int pop){
        EntityManager em = emf.createEntityManager();

        List<Country> countries;

        try {
            em.getTransaction().begin();
            
            countries = em.createNamedQuery("Country.findByGreaterPop").setParameter("population", pop).getResultList();
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return countries;
    }
    
    public List<City> getCitiesByCountryCode(String code){
        EntityManager em = emf.createEntityManager();

        List<City> cities;

        try {
            em.getTransaction().begin();
            
            Country country = (Country)em.createNamedQuery("Country.findByCode").setParameter("code", code).getSingleResult();
            
            cities = new ArrayList(country.getCityCollection());
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cities;
    }
}
