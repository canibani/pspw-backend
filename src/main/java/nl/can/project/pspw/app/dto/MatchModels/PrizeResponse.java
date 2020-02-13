package nl.can.project.pspw.app.dto.MatchModels;

import java.util.ArrayList;

public class PrizeResponse {
    ArrayList<Prize> prizes;

    public ArrayList<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(ArrayList<Prize> prizes) {
        this.prizes = prizes;
    }
}
