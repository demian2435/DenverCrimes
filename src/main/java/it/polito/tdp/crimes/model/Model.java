package it.polito.tdp.crimes.model;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	private EventsDao dao;
	private List<String> offenseCategorySet;
	private List<YearMonth> dateSet;

	public Model() {
		dao = new EventsDao();
		offenseCategorySet = new ArrayList<String>();
		dateSet = new ArrayList<YearMonth>();
		dao.allOffenseCategory(offenseCategorySet);
		dao.allDate(dateSet);
	}

	public List<String> getOffenseCategory() {
		return offenseCategorySet;
	}

	public List<YearMonth> getDate() {
		return dateSet;
	}

	public List<Event> getEventsFromDateAndCategory(YearMonth date, String category) {
		return dao.eventsFromDateAndCategory(date, category);
	}
}
