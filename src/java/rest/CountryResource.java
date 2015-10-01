/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import db.dbFacade;
import entity.Country;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.JSONConverter;

/**
 * REST Web Service
 *
 * @author AlexanderSteen
 */
@Path("country")
public class CountryResource {

    private dbFacade dbFacade;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CountyResource
     */
    public CountryResource() {
        this.dbFacade = new dbFacade();
    }

    /**
     * Retrieves representation of an instance of rest.CountyResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public Response get() {
        List<Country> countries = dbFacade.getCountries();
        String json = JSONConverter.GetJSONFromCountry(countries);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @GET
    @Produces("application/json")
    @Path("{pop}")
    public Response get(@PathParam("pop") int pop){
        List<Country> countries = dbFacade.getCountriesWithGreaterPop(pop);
        String json = JSONConverter.GetJSONFromCountry(countries);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    /**
     * PUT method for updating or creating an instance of CountyResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
