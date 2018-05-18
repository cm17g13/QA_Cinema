package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.*;
import com.qa.service.ShowingServiceInterface;

@Path("/cinema")
public class ShowingEndpoint {

	@Inject
	private ShowingServiceInterface service;
	
	@GET
	@Path("/showing")
	@Produces({"application/json"})
	public String getAllShowings() {
		return service.getAllShowings();
	}
	
	@GET
	@Path("/movie")
	@Produces({"application/json"})
	public String getAllMovies() {
		return service.getAllMovies();
	}
	
	@GET
	@Path("/codes")
	@Produces({"application/json"})
	public String getAllCodes() {
		return service.getAllCodes();
	}
	
	@GET
	@Path("/movie/{title}")
	@Produces({"application/json"})
	public String getMovieShowings(@PathParam("title") String title) {
		return service.getMovieShowings(title);
	}

	@GET
	@Path("/showing/{id}")
	@Produces({"application/json"})
	public String findAShowing(@PathParam("id") Long id) {
		return service.findAShowing(id);
	}
	
	@POST
	@Path("/showing")
	@Produces({"application/json"})
	public String createShowing(String showing) {
		return service.createShowing(showing);
	}

	@PUT
	@Path("/showing")
	@Produces({"application/json"})
	public String updateShowing(String showing) {
		return service.updateShowing(showing);
	}
	
	@PUT
	@Path("/showing/book/{id}")
	@Produces({"application/json"})
	public String bookSeats(@PathParam("id") Long id, String seats) {
		return service.bookSeats(id, seats);
	}
	
	
	@DELETE
	@Path("/showing/{id}")
	@Produces({"application/json"})
	public String deleteShowing(@PathParam("id") Long id) {
		return service.deleteShowing(id);
	}
}
