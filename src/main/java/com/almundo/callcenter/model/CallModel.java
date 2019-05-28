package com.almundo.callcenter.model;

/**
 * Title: CallModel.java <br>
 * <p>
 * Description: Clase de objeto de dominio que representa una llamada
 *
 * @author Saul Montoya
 */
public class CallModel {

    private long id;

    private String assignedCall;

    private int duration;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAssignedCall() {
        return assignedCall;
    }

    public void setAssignedCall(String assignedCall) {
        this.assignedCall = assignedCall;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CallModel model = (CallModel) o;

        return id == model.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
