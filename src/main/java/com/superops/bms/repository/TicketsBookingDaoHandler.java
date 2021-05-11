package com.superops.bms.repository;

import static com.superops.bms.util.Constants.CONST_BLOCKED;
import static com.superops.bms.util.Constants.CONST_SEAT_NO;
import static com.superops.bms.util.Constants.CONST_SELECT_TICKETS_PART_1;
import static com.superops.bms.util.Constants.CONST_SELECT_TICKETS_PART_2;
import static com.superops.bms.util.Constants.CONST_THEATRE_ID;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.superops.bms.entity.TheatreScreen1Entity;
import com.superops.bms.exception.ConcurrentBookingException;
import com.superops.bms.util.UtilityInterface;

@Repository
public class TicketsBookingDaoHandler {

	private TheatreScreen1Repository theatreScreen1Repository;
	private EntityManager entityManager;

	TicketsBookingDaoHandler(TheatreScreen1Repository theatreScreen1Repository,
			MovieRepository movieRepository, EntityManager entityManager, Environment environment) {
		this.theatreScreen1Repository = theatreScreen1Repository;
		this.entityManager = entityManager;
	}

	public List<TheatreScreen1Entity> bookTickets(String showNumber, List<String> seatNumbers, Long theatreId)
			throws ConcurrentBookingException {
		try {

			Query query = entityManager.createQuery(CONST_SELECT_TICKETS_PART_1 + UtilityInterface.getShowForQuery(showNumber) + CONST_SELECT_TICKETS_PART_2);
			query.setParameter(CONST_SEAT_NO, seatNumbers);
			query.setParameter(CONST_THEATRE_ID, theatreId);
			List<TheatreScreen1Entity> movieBookingsList = (List<TheatreScreen1Entity>) query.getResultList();

			if (!movieBookingsList.isEmpty()) {
				movieBookingsList.stream().forEach(seat -> UtilityInterface.setShowForDB(seat, showNumber, CONST_BLOCKED));
				theatreScreen1Repository.saveAll(movieBookingsList);
				theatreScreen1Repository.flush();
			}
			return movieBookingsList;
		} catch (Exception exp) {
			throw new ConcurrentBookingException(exp);
		}
	}

}
