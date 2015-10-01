/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import db.dbFacade;
import entity.City;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.JSONConverter;

/**
 * REST Web Service
 *
 * @author AlexanderSteen
 */
@Path("city")
public class CityResource {
    
    private dbFacade dbFacade;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CityResource
     */
    public CityResource() {
        dbFacade = new dbFacade();
    }


    @GET
    @Produces("application/json")
    @Path("{country}")
    public Response getJson(@PathParam("country") String country) {
        List<City> cities = dbFacade.getCitiesByCountryCode(country);
        String json = JSONConverter.GetJSONFromCity(cities);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{country}")
    public Response addCity(@PathParam("country") String country, String json){
        City city = JSONConverter.GetCityFromJSON(json);
        dbFacade.addCityToCountry(city, country);
        String jsonOut = JSONConverter.GetJSONFromCity(dbFacade.getCitiesByCountryCode(country));
        return Response.ok(jsonOut, MediaType.APPLICATION_JSON).build();
    }
}
