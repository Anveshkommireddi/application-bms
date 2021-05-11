package com.superops.bms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MOVIE")
public class MovieEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MOVIE_ID")
	private Long movieId;

	@Column(name = "MOVIE_NM")
	private String movieName;

	@OneToMany(mappedBy = "movieEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TheatreEntity> theatresList;

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public List<TheatreEntity> getTheatresList() {
		return theatresList;
	}

	public void setTheatresList(List<TheatreEntity> theatresList) {
		this.theatresList = theatresList;
	}
}
