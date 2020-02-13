package nl.can.project.pspw.app.daos.MatchDAOs;

import nl.can.project.pspw.app.datasources.DbConnection;
import nl.can.project.pspw.app.dto.MatchModels.Match;
import nl.can.project.pspw.app.dto.Team;
import nl.can.project.pspw.app.dto.Wrestler;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MatchListDAO {
    private Connection connection;

    @Inject
    public void setDbc(DbConnection dbc) {
        this.connection = dbc.getConnection();
    }

    public ArrayList<Match> getEventMatches(int eventId) {
        ArrayList<Match> matches = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT M.matchId, M.matchType, M.prize " +
                                                                    "FROM Match M INNER JOIN MatchInEvent MiE " +
                                                                    "ON M.matchId = MiE.matchId " +
                                                                    "WHERE MiE.eventId = ?");
            st.setInt(1, eventId);
            ResultSet rsEmps = st.executeQuery();
            while (rsEmps.next()) {
                Match match = new Match();
                match.setTeams(getWrestlersForMatch(rsEmps.getInt(1)));
                match.setMatchId(eventId);
                match.setMatchType(rsEmps.getString(2));
                match.setPrize(rsEmps.getString(3));
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    public ArrayList<Team> getWrestlersForMatch(int matchId) {
        ArrayList<Integer> teamIds = getListOfTeams(matchId);
        ArrayList<Team> teams = makeTeams(teamIds);
        for(Team team: teams) {
            try {
                PreparedStatement st = connection.prepareStatement("SELECT wrestler " +
                                                                        "FROM WrestlerInMatch " +
                                                                        "WHERE matchId = ? AND team = ? " +
                                                                        "ORDER BY team ASC");
                st.setInt(1, matchId);
                st.setInt(2, team.getTeamId());
                ResultSet rsEmps = st.executeQuery();
                ArrayList<Wrestler> wrestlers = new ArrayList<>();
                while (rsEmps.next()) {
                    Wrestler wrestler = new Wrestler();
                    wrestler.setName(rsEmps.getString(1));
                    wrestlers.add(wrestler);
                }
                team.setWrestlers(wrestlers);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return teams;
    }

    private ArrayList<Team> makeTeams(ArrayList<Integer> teamIds) {
        ArrayList<Team> teams = new ArrayList<>();
        for (int teamId: teamIds) {
            Team team = new Team();
            team.setTeamId(teamId);
            teams.add(team);
        }
        return teams;
    }

    private ArrayList<Integer> getListOfTeams(int matchId) {
        ArrayList<Integer> teamIds = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement("SELECT DISTINCT team FROM WrestlerInMatch " +
                                                                    "WHERE matchId = ? " +
                                                                    "ORDER BY team ASC");
            st.setInt(1, matchId);
            ResultSet rsEmps = st.executeQuery();
            while (rsEmps.next()) {
                teamIds.add(rsEmps.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamIds;
    }
}
