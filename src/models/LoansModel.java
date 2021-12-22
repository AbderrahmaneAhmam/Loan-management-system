package models;

import jdk.jfr.Timespan;

import java.util.Date;

public class LoansModel {
    private int id;
    private Date loanDate;
    private Date backDate;
    private int duration;
    private MaterialModel material;
    private UserModel user;

    public LoansModel(int id, Date loanDate, Date backDate, int duration, MaterialModel material, UserModel user) {
        this.id = id;
        this.loanDate = loanDate;
        this.backDate = backDate;
        this.duration = duration;
        this.material = material;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public MaterialModel getMaterial() {
        return material;
    }

    public void setMaterial(MaterialModel material) {
        this.material = material;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
