package com.qa.service;

import com.qa.persistence.Movie;

public interface MovieServiceInterface {

	public String getAllMovies();

	public String findAMovie(Long id);
	
	public String createMovie(String movie);

	public String updateMovie(String movie);

	public String deleteMovie(Long id);
	
	public Movie findMovie(Long id);
	
}
