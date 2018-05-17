package com.qa.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.database.ShowingDBRepo;
import com.qa.persistence.Showing;

public class ShowingService implements ShowingServiceInterface {

	@Inject
	private ShowingDBRepo repo;
	
	private String updatePass = "{\"message\": \"the showing has been updated\"}";
	private String updateFail = "{\"message\": \"showing doesn't exist, could not updated\"}";
	
	private String addShowingPass = "{\"message\": \"the showing was added to the showings times, or already existed\"}";
	private String addShowingFail = "{\"message\": \"the showing did not exist, so a new showing was not added\"}";
	
	private String removeShowingPass = "{\"message\": \"the showing was removed from the showings times, or never existed\"}";
	private String removeShowingFail = "{\"message\": \"the showing did not exist, so a new showing was not removed\"}";
	
	private String updateSeatsPass = "{\"message\": \"the seats have been booked\"}";
	private String updateSeatsFail = "{\"message\": \"the showing did not exist, so seats could not be booked\"}";
	private String updateSeatsFull = "{\"message\": \"Their was not enough seats available\"}";
	
	private static final Logger logger = Logger.getLogger(ShowingService.class);
	
	
	public String getAllShowings() {
		return repo.getAllShowings();
	}
	
	public String findAShowing(Long id) {
		return repo.findAShowing(id);
	}
	
	public String createShowing(String showing) {
		return repo.createShowing(showing);
	}
	
	public String updateShowing(String showing) {
		Showing updateShowing = repo.getConverter().getObjectForJSON(showing, Showing.class);
		Showing newShowing = updateFields(updateShowing);
		return repo.updateShowing(newShowing, updatePass, updateFail);
	}
	
	public String bookSeats(Long id, String jsonSeats) {
		int standardSeats = Integer.parseInt(repo.getConverter().getAttribute(jsonSeats, "standardSeats"));
		int disabledSeats = Integer.parseInt(repo.getConverter().getAttribute(jsonSeats, "disabledSeats"));
		logger.info("ShowingService: " + "ID: " + id + " , Stand: " + standardSeats + ", Disabled: " + disabledSeats);
		Showing updateShowing = bookStandardSeats(repo.findShowing(id) ,standardSeats, disabledSeats);
		if(updateShowing.getStandardSeats() <0 || updateShowing.getDisabledSeats() < 0) {
			return updateSeatsFull;
		}
		return repo.updateShowing(updateShowing, updateSeatsPass, updateSeatsFail);
	}
	
	
	public String deleteShowing(Long id) {
		return repo.deleteShowing(id);
	}

	
	public Showing findShowing(Long id) {
		return repo.findShowing(id);
	}
	
	public Showing updateFields(Showing updateShowing) {
		Showing existingShowing =  repo.findShowing(updateShowing.getShowing_id());
		if(existingShowing != null) {
			if(!updateShowing.getOMDbCode().trim().equals("")) {
				existingShowing.setOMDbCode(updateShowing.getOMDbCode());
			}
			if(!updateShowing.getTitle().trim().equals("")) {
				existingShowing.setTitle(updateShowing.getTitle());
			}
			if(!updateShowing.getYear().trim().equals("")) {
				existingShowing.setYear(updateShowing.getYear());
			}
			if(!updateShowing.getScreen().trim().equals("")) {
				existingShowing.setScreen(updateShowing.getScreen());
			}
			if(!updateShowing.getTime().trim().equals("")) {
				existingShowing.setTime(updateShowing.getTime());
			}
			if(updateShowing.getStandardSeats() >= 0) {
				existingShowing.setStandardSeats(updateShowing.getStandardSeats());
			}
			if(updateShowing.getDisabledSeats() >= 0) {
				existingShowing.setDisabledSeats(updateShowing.getDisabledSeats());
			}
		}
		return existingShowing;
	}
	
	
	public Showing bookStandardSeats(Showing showing, int standard, int disabled) {
		showing.bookStandardSeats(standard);
		showing.bookDisabledSeats(disabled);
		return showing;
	}
	

}
