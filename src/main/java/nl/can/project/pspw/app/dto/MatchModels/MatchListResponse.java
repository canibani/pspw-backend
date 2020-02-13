package nl.can.project.pspw.app.dto.MatchModels;

import java.util.ArrayList;

public class MatchListResponse {
    private ArrayList<Match> matches;

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }
}
