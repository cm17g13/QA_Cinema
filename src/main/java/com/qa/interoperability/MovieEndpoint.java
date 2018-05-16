package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.*;
import com.qa.service.MovieServiceInterface;

@Path("/movie")
public class MovieEndpoint {

	@Inject
	private MovieServiceInterface service;
	
	@GET
	@Path("/json")
	@Produces({"application/json"})
	public String getAllMovies() {
		return service.getAllMovies();
	}

	@GET
	@Path("/json/{id}")
	@Produces({"application/json"})
	public String findAMovie(@PathParam("id") Long id) {
		return service.findAMovie(id);
	}
	
	@POST
	@Path("/json")
	@Produces({"application/json"})
	public String createMovie(String movie) {
		return service.createMovie(movie);
	}

	@PUT
	@Path("/json")
	@Produces({"application/json"})
	public String updateMovie(String movie) {
		return service.updateMovie(movie);
	}
	
	@PUT
	@Path("/json/add/{id}")
	@Produces({"application/json"})
	public String addMovieShowing(@PathParam("id") Long id, String showing) {
		return service.addMovieShowing(id, showing);
	}
	
	@PUT
	@Path("/json/remove/{id}")
	@Produces({"application/json"})
	public String removeMovieShowing(@PathParam("id") Long id, String showing) {
		return service.removeMovieShowing(id, showing);
	}
	
	@DELETE
	@Path("/json/{id}")
	@Produces({"application/json"})
	public String deleteMovie(@PathParam("id") Long id) {
		return service.deleteMovie(id);
	}
}
