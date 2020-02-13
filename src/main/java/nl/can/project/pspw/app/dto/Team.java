package nl.can.project.pspw.app.dto;

import java.util.ArrayList;

public class Team {
    private int teamId;
    private ArrayList<Wrestler> wrestlers;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public ArrayList<Wrestler> getWrestlers() {
        return wrestlers;
    }

    public void setWrestlers(ArrayList<Wrestler> wrestlers) {
        this.wrestlers = wrestlers;
    }
}
