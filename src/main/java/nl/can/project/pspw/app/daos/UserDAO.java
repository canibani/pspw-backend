package nl.can.project.pspw.app.daos;

import nl.can.project.pspw.app.datasources.DbConnection;
import nl.can.project.pspw.app.dto.User;
import nl.can.project.pspw.app.dto.UserResponse;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    private Connection c;
    @Inject
    public void setDbc(DbConnection dbc) {
        this.c = dbc.getConnection();
    }

    public User getUserByUsername(String username) {
        User user = new User();
        try {
            PreparedStatement st = c.prepareStatement("SELECT token, username, password FROM users WHERE username = ?");
            st.setString(1, username);
            ResultSet rsEmps = st.executeQuery();
            while(rsEmps.next()){
                user.setUsername(rsEmps.getString("username"));
                user.setPassword(rsEmps.getString("password"));
                user.setToken(rsEmps.getString("token"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Boolean checkToken(String token) {
        return true;
    }
}
