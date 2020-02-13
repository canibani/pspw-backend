package nl.can.project.pspw.app.dto.MatchModels;

import nl.can.project.pspw.app.dto.Team;

import java.util.ArrayList;

public class Match {
    private ArrayList<Team> teams;
    private int matchId;
    private String matchType;
    private String prize;

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}
