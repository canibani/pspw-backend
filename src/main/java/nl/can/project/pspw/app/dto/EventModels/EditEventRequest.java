package nl.can.project.pspw.app.dto.EventModels;

public class EditEventRequest {
    String newEventName;
    String newEventDate;
    String newEventPromotion;

    public String getNewEventName() {
        return newEventName;
    }

    public void setNewEventName(String newEventName) {
        this.newEventName = newEventName;
    }

    public String getNewEventDate() {
        return newEventDate;
    }

    public void setNewEventDate(String newEventDate) {
        this.newEventDate = newEventDate;
    }

    public String getNewEventPromotion() {
        return newEventPromotion;
    }

    public void setNewEventPromotion(String newEventPromotion) {
        this.newEventPromotion = newEventPromotion;
    }
}
