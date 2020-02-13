package nl.can.project.pspw.app.daos.MatchDAOs;

import nl.can.project.pspw.app.datasources.DbConnection;
import nl.can.project.pspw.app.dto.MatchModels.MatchType;
import nl.can.project.pspw.app.dto.MatchModels.Prize;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;

public class MatchDetailDAO {
    private Connection connection;

    @Inject
    public void setDbc(DbConnection dbc) {
        this.connection = dbc.getConnection();
    }

    public ArrayList<MatchType> getAllMatchTypes() {
        ArrayList<MatchType> matchTypes = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT matchType FROM MatchType");
            ResultSet rsEmps = st.executeQuery();
            while (rsEmps.next()) {
                MatchType matchType = new MatchType();
                matchType.setMatchType(rsEmps.getString(1));
                matchTypes.add(matchType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchTypes;
    }

    public ArrayList<Prize> getPrizesForPromotion(String promotion) {
        ArrayList<Prize> prizes = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT name FROM Prize WHERE promotion = ?");
            st.setString(1, promotion);
            ResultSet rsEmps = st.executeQuery();
            while (rsEmps.next()) {
                Prize prize = new Prize();
                prize.setPrize(rsEmps.getString(1));
                prizes.add(prize);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prizes;
    }
}
