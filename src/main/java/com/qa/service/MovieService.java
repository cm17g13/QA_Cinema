package com.qa.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.database.MovieDBRepo;
import com.qa.persistence.Movie;

public class MovieService implements MovieServiceInterface {

	@Inject
	private MovieDBRepo repo;
	
	private String updatePass = "{\"message\": \"the movie has been updated\"}";
	private String updateFail = "{\"message\": \"movie doesn't exist, could not updated\"}";
	
	private String addShowingPass = "{\"message\": \"the showing was added to the movies times, or already existed\"}";
	private String addShowingFail = "{\"message\": \"the movie did not exist, so a new showing was not added\"}";
	
	private String removeShowingPass = "{\"message\": \"the showing was removed from the movies times, or never existed\"}";
	private String removeShowingFail = "{\"message\": \"the movie did not exist, so a new showing was not removed\"}";
	
	private static final Logger logger = Logger.getLogger(MovieService.class);
	
	
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
		return repo.updateMovie(newMovie, updatePass, updateFail);
	}
	
	public String addMovieShowing(Long id, String showing) {
		
		String showingInfo = repo.getConverter().getAttribute(showing, "showing");
		logger.info("MovieService: " + ", " + id +  ", " + showingInfo);
		Movie updateMovie = addShowing(repo.findMovie(id), showingInfo);
		return repo.updateMovie(updateMovie, addShowingPass, addShowingFail);
	}
	
	public String removeMovieShowing(Long id, String showing) {
		String showingInfo = repo.getConverter().getAttribute(showing, "showing");
		logger.info("MovieService: " + ", " + id +  ", " + showingInfo);
		Movie updateMovie = removeShowing(repo.findMovie(id), showingInfo);
		return repo.updateMovie(updateMovie, removeShowingPass, removeShowingFail);
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
	
	public Movie addShowing(Movie movie, String newShowing) {
		String showings = movie.getShowings();
		boolean isNew = true;
		if(showings != null) {
			for(String showing : showings.split(",")) {
				if(newShowing.equals(showing)) {
					isNew = false;
				}
			} 
		}
		if(isNew) {
			movie.addShowing(newShowing);
		}
		return movie;
	}
	
	public Movie removeShowing(Movie movie, String newShowing) {
		String showings = movie.getShowings();
		boolean doesExist = false;
		if(showings != null) {
			for(String showing : showings.split(",")) {
				if(newShowing.equals(showing)) {
					doesExist = true;
				}
			}
		}
		if(doesExist) {
			movie.removeShowing(newShowing);
		}
		return movie;
	}
	

}
