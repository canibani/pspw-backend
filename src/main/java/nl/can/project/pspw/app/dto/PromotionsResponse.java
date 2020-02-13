package nl.can.project.pspw.app.dto;

import java.util.ArrayList;

public class PromotionsResponse {
    private ArrayList<Promotion> promotions;

    public ArrayList<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(ArrayList<Promotion> promotions) {
        this.promotions = promotions;
    }
}
