package com.qa.database;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.transaction.Transactional.TxType.*;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import com.qa.persistence.Movie;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class MovieDBRepo {

	private static final Logger logger = Logger.getLogger(MovieDBRepo.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil jsonConverter;
	
	
	public String getAllMovies() {
		
		logger.info("MovieDBRepo getAllMovies");
		TypedQuery<Movie> query = manager.createQuery("SELECT m FROM Movie m", Movie.class);
		Collection<Movie> movies = query.getResultList();
		return jsonConverter.getJSONForObject(movies);	
	}
	
	public String findAMovie(Long id) {

		Movie movie = findMovie(id);
		if(movie != null) {
			return jsonConverter.getJSONForObject(movie);
		} 
		return "{\"message\": \"movie was not found\"}";
	}
	
	@Transactional(REQUIRED)
	public String createMovie(String movie) {
		
		Movie newMovie = jsonConverter.getObjectForJSON(movie, Movie.class);
		manager.persist(newMovie);
		return "{\"message\": \"movie sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateMovie(Movie movie) {
		
		if(movie != null) {
			manager.merge(movie);
			return "{\"message\": \"the movie has been updated\"}"; 
		}
		return "{\"message\": \"movie doesn't exist, could not updated\"}";	
	}
	
	@Transactional(REQUIRED)
	public String deleteMovie(Long id) {
		
		Movie exists = findMovie(id);
		if (exists != null) {
			manager.remove(exists);
			return "{\"message\": \"the movie has been deleted\"}";
		} else {
			return "{\"message\": \"the movie did not exist, and so was not deleted\"}";
		}	
	}

	public Movie findMovie(Long id) {
		return manager.find(Movie.class, id);
	}
	
	@Transactional(REQUIRED)
	public void setEntityManager(EntityManager manager) {
		this.manager = manager;
	}
	
	@Transactional(REQUIRED)
	public void setJsonConverter(JSONUtil jsonConverter) {
		this.jsonConverter = jsonConverter;
	}
	
	public JSONUtil getConverter() {
		return this.jsonConverter;
	}

}
	
