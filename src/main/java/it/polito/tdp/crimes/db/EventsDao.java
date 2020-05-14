package it.polito.tdp.crimes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.crimes.model.Event;

public class EventsDao {

	public List<Event> listAllEvents() {
		String sql = "SELECT * FROM events";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			List<Event> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"), res.getInt("offense_code"),
							res.getInt("offense_code_extension"), res.getString("offense_type_id"),
							res.getString("offense_category_id"), res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"), res.getDouble("geo_lon"), res.getDouble("geo_lat"),
							res.getInt("district_id"), res.getInt("precinct_id"), res.getString("neighborhood_id"),
							res.getInt("is_crime"), res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Event> eventsFromDateAndCategory(YearMonth date, String category) {
		String sql = "SELECT * FROM EVENTS WHERE MONTH(reported_date) = ? AND YEAR(reported_date) = ? AND offense_category_id = ?";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, date.getMonthValue());
			st.setInt(2, date.getYear());
			st.setString(3, category);

			List<Event> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"), res.getInt("offense_code"),
							res.getInt("offense_code_extension"), res.getString("offense_type_id"),
							res.getString("offense_category_id"), res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"), res.getDouble("geo_lon"), res.getDouble("geo_lat"),
							res.getInt("district_id"), res.getInt("precinct_id"), res.getString("neighborhood_id"),
							res.getInt("is_crime"), res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void allOffenseCategory(List<String> offenseCategory) {
		String sql = "SELECT DISTINCT offense_category_id FROM EVENTS ORDER BY offense_category_id";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					String r = res.getString("offense_category_id");
					offenseCategory.add(r);
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void allDate(List<YearMonth> date) {
		String sql = "select DISTINCT month(reported_date) as M, year(reported_date) as Y FROM events ORDER BY Y";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					YearMonth d = YearMonth.of(res.getInt("Y"), res.getInt("M"));
					date.add(d);
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
