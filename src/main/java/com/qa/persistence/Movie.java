package com.qa.persistence;
import javax.persistence.*;


@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movie_id;
    
    //@OneToMany(mappedBy="movie", targetEntity=Showing.class)
    //private List<Showing> showings;

	@Column(name = "OMDbCode")
    private String OMDbCode = null;
	
	@Column(name = "title")
    private String title = null;
    
    @Column(name="year")
    private String year = null;

    
	public Movie() {
    }
	
    public Movie(String OMDbCode, String title, String year) {
        this.title = title; 
        this.year = year;
        this.OMDbCode = OMDbCode;
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
}

