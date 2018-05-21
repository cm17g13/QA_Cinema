package com.qa.persistence;
import javax.persistence.*;


@Entity
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showing_id;
    
	@Column(name = "OMDbCode")
    private String OMDbCode = null;
	
	@Column(name = "title")
    private String title = null;
    
    @Column(name="year")
    private String year = null;
    
    @Column(name="time")
    private String time = null;

	@Column(name="screen")
    private String screen = null;

	@Column(name="standardSeats")
    private Integer standardSeats = null;
    
    @Column(name="disabledSeats")
    private Integer disabledSeats = null;
    
    @Column(name="imageURL")
    private String imageURL = null;

	public Showing() {
    }
	
    public Showing(String OMDbCode, String title, String year, String time, String screen, int standardSeats, int disabledSeats, String imageURL) {
    	this.OMDbCode = OMDbCode;
    	this.title = title; 
        this.year = year;
        this.time = time;
        this.screen = screen;
        this.standardSeats = standardSeats;
        this.disabledSeats = disabledSeats;
        this.imageURL = imageURL;
    }
    
	public Long getShowing_id() {
		return showing_id;
	}
    
    /*
	public void setShowing_id(Long showing_id) {
		this.showing_id = showing_id;
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
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}
	
   public int getStandardSeats() {
		return standardSeats;
	}

	public void setStandardSeats(int standardSeats) {
		this.standardSeats = standardSeats;
	}

	public int getDisabledSeats() {
		return disabledSeats;
	}

	public void setDisabledSeats(int disabledSeats) {
		this.disabledSeats = disabledSeats;
	}
	
	public void bookStandardSeats(int seats) {
		this.standardSeats = this.standardSeats - seats;
	}
	
	public void bookDisabledSeats(int seats) {
		this.disabledSeats = this.disabledSeats - seats;
	}
	
    public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}

