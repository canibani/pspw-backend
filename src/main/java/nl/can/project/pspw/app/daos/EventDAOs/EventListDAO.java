package nl.can.project.pspw.app.daos.EventDAOs;

import nl.can.project.pspw.app.datasources.DbConnection;
import nl.can.project.pspw.app.dto.EventModels.EditEventRequest;
import nl.can.project.pspw.app.dto.WrestlingEvent;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventListDAO {
    private Connection connection;

    @Inject
    public void setDbc(DbConnection dbc) {
        this.connection = dbc.getConnection();
    }

    public ArrayList<WrestlingEvent> getListOfEvents() {
        ArrayList<WrestlingEvent> eventList = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement( "SELECT eventId, name, date, promotion " +
                                                                    "FROM Event ORDER BY date");
            ResultSet rsEmps = st.executeQuery();
            while (rsEmps.next()) {
                int eventId = rsEmps.getInt("EventId");
                String name = rsEmps.getString("name");
                String date = rsEmps.getString("date");
                String promotion = rsEmps.getString("promotion");
                eventList.add(new WrestlingEvent(eventId, name, date, promotion));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventList;
    }
    public void addEvent(String name, String date, String promotion) throws SQLException {
        try {
            PreparedStatement st = connection.prepareCall("{CALL Sp_create_event(?, ? , ?)}");
            st.setString(1, name);
            st.setString(2, date);
            st.setString(3, promotion);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public void editEvent(int eventId, EditEventRequest editEventRequest) throws SQLException {
        try {
            PreparedStatement st = connection.prepareCall("{CALL Sp_edit_event(?, ? , ?, ?)}");
            st.setInt(1, eventId);
            st.setString(2, editEventRequest.getNewEventName());
            st.setString(3, editEventRequest.getNewEventDate());
            st.setString(4, editEventRequest.getNewEventPromotion());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public void removeEvent(int eventId) throws SQLException {
        try {
            PreparedStatement st = connection.prepareCall("{CALL Sp_delete_event(?)}");
            st.setInt(1, eventId);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
