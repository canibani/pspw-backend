package nl.can.project.pspw.app.dto;

public class PredictionsRequest {
    private int eventId;
    private int[] predictions;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setPredictions(int[] predictions) {
        this.predictions = predictions;
    }

    public int[] getPredictions() {
        return predictions;
    }
}
