package com.qa.interoperability;

import javax.inject.Inject;
import javax.ws.rs.*;
import com.qa.service.ShowingServiceInterface;

@Path("/showing")
public class ShowingEndpoint {

	@Inject
	private ShowingServiceInterface service;
	
	@GET
	@Path("/json")
	@Produces({"application/json"})
	public String getAllShowings() {
		return service.getAllShowings();
	}

	@GET
	@Path("/json/{id}")
	@Produces({"application/json"})
	public String findAShowing(@PathParam("id") Long id) {
		return service.findAShowing(id);
	}
	
	@POST
	@Path("/json")
	@Produces({"application/json"})
	public String createShowing(String showing) {
		return service.createShowing(showing);
	}

	@PUT
	@Path("/json")
	@Produces({"application/json"})
	public String updateShowing(String showing) {
		return service.updateShowing(showing);
	}
	
	@PUT
	@Path("/json/book/{id}")
	@Produces({"application/json"})
	public String bookSeats(@PathParam("id") Long id, String seats) {
		return service.bookSeats(id, seats);
	}
	
	
	@DELETE
	@Path("/json/{id}")
	@Produces({"application/json"})
	public String deleteShowing(@PathParam("id") Long id) {
		return service.deleteShowing(id);
	}
}
