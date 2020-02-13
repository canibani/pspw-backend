package nl.can.project.pspw.app.dto;

public class PredictionsResponse {
    private Team[] predictions;
    public int eventId;

    public void setPredictions(Team[] predictions) {
        this.predictions = predictions;
    }

    public Team[] getPredictions() {
        return predictions;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
