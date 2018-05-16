package com.qa.persistence;
import javax.persistence.*;


@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movie_id;
    
	@Column(name = "OMDbCode")
    private String OMDbCode = null;
	
	@Column(name = "title")
    private String title = null;
    
    @Column(name="year")
    private String year = null;
    
    @Column(name="showings")
    private String showings = null;

	public Movie() {
    }
	
    public Movie(String OMDbCode, String title, String year, String showings) {
    	this.OMDbCode = OMDbCode;
    	this.title = title; 
        this.year = year;
        this.showings = showings;
    }
    
    public Long getMovie_id() {
		return movie_id;
	}
    
    /*
	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}*/
	
	public String getOMDbCode() {
		return OMDbCode;
	}
	
	public void setOMDbCode(String oMDbCode) {
		OMDbCode = oMDbCode;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getShowings() {
		return showings;
	}

	public void setShowings(String showings) {
		this.showings = showings;
	}
	
	public void addShowing(String newShowing) {
		if(showings == null) {
			this.showings = newShowing;
		} else {
			this.showings = this.showings + "," + newShowing;
		}
	}
	
	public void removeShowing(String showingToRemove) {
		String newShowings = "";
		for(String showing : this.showings.split(",")) {
			if(!showingToRemove.equals(showing)) {
				if(newShowings.equals("")) {
					newShowings = showing;
				} else {
					newShowings = newShowings + "," + showing;
				}
			}
		}
		if(newShowings.length() == 0) {
			this.showings = null;
		} else {
			this.showings = newShowings;
		}
	}
}

