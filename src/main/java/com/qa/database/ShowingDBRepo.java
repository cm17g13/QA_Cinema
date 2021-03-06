package com.qa.database;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static javax.transaction.Transactional.TxType.*;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.persistence.Showing;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class ShowingDBRepo {

	private static final Logger logger = Logger.getLogger(ShowingDBRepo.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil jsonConverter;
	
	
	public String getAllShowings() {
		logger.info("ShowingDBRepo getAllShowings");
		TypedQuery<Showing> query = manager.createQuery("SELECT s FROM Showing s ORDER BY s.title", Showing.class);
		Collection<Showing> showings = query.getResultList();
		return jsonConverter.getJSONForObject(showings);	
	}
	
	public String getAllMovies() {
		logger.info("ShowingDBRepo getAllMovies");
		Query query = manager.createQuery("SELECT DISTINCT s.title, s.year, s.imageURL, s.OMDbCode FROM Showing s ORDER BY s.title");
		Collection<String> showings = (Collection<String>) query.getResultList();
		return jsonConverter.getJSONForObject(showings);	
	}
	
	public String getAllCodes() {
		logger.info("ShowingDBRepo getAllMovies");
		TypedQuery<String> query = manager.createQuery("SELECT DISTINCT s.OMDbCode FROM Showing s", String.class);
		Collection<String> showings = query.getResultList();
		return jsonConverter.getJSONForObject(showings);	
	}
	
	public String getMovieShowings(String searchTitle) {
		logger.info("ShowingDBRepo getMovieShowings, title: " + searchTitle);
		TypedQuery<Showing> query = manager.createQuery("SELECT s FROM Showing s WHERE s.title = :searchTitle", Showing.class);
		query.setParameter("searchTitle", searchTitle);
		Collection<Showing> showings = query.getResultList();
		return jsonConverter.getJSONForObject(showings);	
	}
	
	
	public String findAShowing(Long id) {
		logger.info("ShowingDBRepo Finding a showing");
		Showing showing = findShowing(id);
		if(showing != null) {
			return jsonConverter.getJSONForObject(showing);
		} 
		return "{\"message\": \"showing was not found\"}";
	}
	
	@Transactional(REQUIRED)
	public String createShowing(String showing) {
		logger.info("ShowingDBRepo Creating showing");
		Showing newShowing = jsonConverter.getObjectForJSON(showing, Showing.class);
		manager.persist(newShowing);
		return "{\"message\": \"showing sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateShowing(Showing showing, String pass, String fail) {
		logger.info("ShowingDBRepo Updating showing");
		if(showing != null) {
			manager.merge(showing);
			return pass; 
		}
		return fail;	
	}
	
	
	@Transactional(REQUIRED)
	public String deleteShowing(Long id) {
		logger.info("ShowingDBRepo Deleting showing");
		Showing exists = findShowing(id);
		if (exists != null) {
			manager.remove(exists);
			return "{\"message\": \"the showing has been deleted\"}";
		} else {
			return "{\"message\": \"the showing did not exist, and so was not deleted\"}";
		}	
	}

	public Showing findShowing(Long id) {
		return manager.find(Showing.class, id);
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
	
