package nl.can.project.pspw.app.daos;

import nl.can.project.pspw.app.datasources.DbConnection;
import nl.can.project.pspw.app.dto.Promotion;
import nl.can.project.pspw.app.dto.WrestlingEvent;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PromotionsDAO {
    private Connection connection;

    @Inject
    public void setDbc(DbConnection dbc) {
        this.connection = dbc.getConnection();
    }

    public ArrayList<Promotion> getPromotions() {
        ArrayList<Promotion> promotions = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT name, abr FROM promotion");
            ResultSet rsEmps = st.executeQuery();
            while (rsEmps.next()) {
                String name = rsEmps.getString("name");
                String abr = rsEmps.getString("abr");
                promotions.add(new Promotion(name, abr));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotions;
    }
}
