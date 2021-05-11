package com.superops.bms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


@Entity
@Table(name = "THEATRE_SCREEN_1")
public class TheatreScreen1Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "THTR_BKNG_SCRN_ID")
	private Long theatreScreenId;

	@Column(name = "SEAT_NUM")
	private String seatNo;

	@JoinColumn(name = "THEATRE_ID")
	@OneToOne(fetch = FetchType.LAZY)
	private TheatreEntity theatreEntity;

	@Column(name = "SHOW_1")
	private String show1;

	@Column(name = "SHOW_2")
	private String show2;

	@Column(name = "SHOW_3")
	private String show3;

	@Column(name = "SHOW_4")
	private String show4;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_OF_BOOKING")
	private Date dateofBooking;

	@Version
	@Column(name = "BOOKING_VRSN")
	private Long bookingVersion;

	public Long getTheatreScreenId() {
		return theatreScreenId;
	}

	public void setTheatreScreenId(Long theatreScreenId) {
		this.theatreScreenId = theatreScreenId;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public TheatreEntity getTheatreEntity() {
		return theatreEntity;
	}

	public void setTheatreEntity(TheatreEntity theatreEntity) {
		this.theatreEntity = theatreEntity;
	}

	public String getShow1() {
		return show1;
	}

	public void setShow1(String show1) {
		this.show1 = show1;
	}

	public String getShow2() {
		return show2;
	}

	public void setShow2(String show2) {
		this.show2 = show2;
	}

	public String getShow3() {
		return show3;
	}

	public void setShow3(String show3) {
		this.show3 = show3;
	}

	public String getShow4() {
		return show4;
	}

	public void setShow4(String show4) {
		this.show4 = show4;
	}

	public Date getDateofBooking() {
		return dateofBooking;
	}

	public void setDateofBooking(Date dateofBooking) {
		this.dateofBooking = dateofBooking;
	}

	public Long getBookingVersion() {
		return bookingVersion;
	}
}
