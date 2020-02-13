package nl.can.project.pspw.app.daos.EventDAOs;

import nl.can.project.pspw.app.datasources.DbConnection;
import nl.can.project.pspw.app.dto.*;
import nl.can.project.pspw.app.dto.EventModels.EventDataResponse;
import nl.can.project.pspw.app.dto.MatchModels.Match;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventsDAO {
    private Connection connection;

    public EventsDAO() {}

    @Inject
    public void setDbc(DbConnection dbc) {
        this.connection = dbc.getConnection();
    }

    public EventDataResponse getEventData(int eventId) {
        EventDataResponse response = new EventDataResponse();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT name, date, eventId, promotion FROM Event WHERE eventId = ?");
            st.setInt(1, eventId);
            ResultSet rsEmps = st.executeQuery();
            while (rsEmps.next()) {
                response.setEventId(rsEmps.getInt("eventId"));
                response.setName(rsEmps.getString("name"));
                response.setDate(rsEmps.getString("date"));
                response.setPromotion(rsEmps.getString("promotion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return response;
    }

    /* Might need this function later

    private ArrayList<Promotion> getEventPromotions(int eventID) {
        ArrayList<Promotion> promotions = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT P.name, P.abr FROM EventInPromotion EiP INNER JOIN Promotion P ON EiP.promotion = P.abr WHERE EiP.eventId = ?");
            st.setInt(1, eventID);
            ResultSet rsEmps = st.executeQuery();
            while (rsEmps.next()) {
                promotions.add(new Promotion(rsEmps.getString(1), rsEmps.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotions;
    }*/
}
