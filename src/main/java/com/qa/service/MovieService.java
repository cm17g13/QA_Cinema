package com.qa.service;

import javax.inject.Inject;
import com.qa.database.MovieDBRepo;
import com.qa.persistence.Movie;

public class MovieService implements MovieServiceInterface {

	@Inject
	private MovieDBRepo repo;
	
	
	public String getAllMovies() {
		return repo.getAllMovies();
	}
	
	public String findAMovie(Long id) {
		return repo.findAMovie(id);
	}
	
	public String createMovie(String movie) {
		return repo.createMovie(movie);
	}
	
	public String updateMovie(String movie) {
		Movie updateMovie = repo.getConverter().getObjectForJSON(movie, Movie.class);
		Movie newMovie = updateFields(updateMovie);
		return repo.updateMovie(newMovie);
	}
	
	public String deleteMovie(Long id) {
		return repo.deleteMovie(id);
	}

	
	public Movie findMovie(Long id) {
		return repo.findMovie(id);
	}
	
	public Movie updateFields(Movie updateMovie) {
		Movie existingMovie =  repo.findMovie(updateMovie.getMovie_id());
		if(existingMovie != null) {
			if(!updateMovie.getOMDbCode().trim().equals("")) {
				existingMovie.setOMDbCode(updateMovie.getOMDbCode());
			}
			if(!updateMovie.getTitle().trim().equals("")) {
				existingMovie.setTitle(updateMovie.getTitle());
			}
			if(!updateMovie.getYear().trim().equals("")) {
				existingMovie.setYear(updateMovie.getYear());
			}
		}
		return existingMovie;
		
	}
	

}
