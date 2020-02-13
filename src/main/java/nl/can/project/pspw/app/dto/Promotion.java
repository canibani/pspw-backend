package nl.can.project.pspw.app.dto;

import java.util.ArrayList;

public class Promotion {

    public Promotion(String name, String abr) {
        this.name = name;
        this.abr = abr;
    }
    public String getAbr() {
        return abr;
    }

    public void setAbr(String abr) {
        this.abr = abr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String abr;
}
