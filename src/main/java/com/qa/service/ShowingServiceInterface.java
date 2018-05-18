package com.qa.service;

import com.qa.persistence.Showing;

public interface ShowingServiceInterface {

	public String getAllShowings();
	
	public String getAllMovies();
	
	public String getAllCodes();
	
	public String getMovieShowings(String title);

	public String findAShowing(Long id);
	
	public String createShowing(String jsonShowing);

	public String updateShowing(String jsonShowing);

	public String bookSeats(Long id, String jsonSeats);
	
	public String deleteShowing(Long id);
	
	public Showing findShowing(Long id);
	
}
