package nl.can.project.pspw.app.dto.EventModels;

import nl.can.project.pspw.app.dto.WrestlingEvent;

import java.util.ArrayList;

public class EventListResponse {

    public ArrayList<WrestlingEvent> getEventList() {
        return eventList;
    }

    public void setEventList(ArrayList<WrestlingEvent> eventList) {
        this.eventList = eventList;
    }

    private ArrayList<WrestlingEvent> eventList;
}
