package com.turtlewave.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by TJsMac on 3/28/18.
 */

public class Crime {

    private UUID id;
    private String title;
    private Date date;
    private boolean solved;
    private boolean policeRequired;

    public Crime() {
        id = UUID.randomUUID();
        date = new Date();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isPoliceRequired() {
        return policeRequired;
    }

    public void setPoliceRequired(boolean policeRequired) {
        this.policeRequired = policeRequired;
    }
}
