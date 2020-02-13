package nl.can.project.pspw.app.dto;

public class WrestlingEvent {
    private int eventId;
    private String name;
    private String date;
    private String promotion;

    public WrestlingEvent(int eventId, String name, String date, String promotion){
        this.eventId = eventId;
        this.name = name;
        this.date = date;
        this.promotion = promotion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
