package com.superops.bms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "THEATRE", uniqueConstraints = @UniqueConstraint(columnNames = { "THEATRE_NAME", "SCREEN_NAME", "LOCATION" }))
public class TheatreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "THEATRE_ID")
	private Long theatreId;

	@Column(name = "THEATRE_NAME")
	private String theatreName;

	@Column(name = "SCREEN_NAME")
	private String screenName;
	
	@Column(name = "LOCATION")
	private String locationName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MOVIE_ID")
	private MovieEntity movieEntity;

	public Long getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public MovieEntity getMovieEntity() {
		return movieEntity;
	}

	public void setMovieEntity(MovieEntity movieEntity) {
		this.movieEntity = movieEntity;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
}
