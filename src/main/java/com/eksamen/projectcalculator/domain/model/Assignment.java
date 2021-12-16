package com.eksamen.projectcalculator.domain.model;

import com.eksamen.projectcalculator.domain.service.Calculator;

public abstract class Assignment {

    // Denne model indeholder attributter og metoder til tasks og subtasks

    protected long id;
    protected long foreignId;
    protected String name;
    protected String resource;
    protected String startDateStr; // Er string da det bruges til at indsætte datoer i gantt diagrammet
    protected String finishDateStr;
    protected int percentComplete;
    protected double dailyWorkHours;
    protected double pricePerHour;

    /**
     * @author Martin
     */

    // Udregner prisen af en assignment, ud fra arbejdsdage, timer, pris
    public double getPrice() {
        return Calculator.getWorkDaysBetweenDates(startDateStr, finishDateStr) * dailyWorkHours * pricePerHour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getForeignId() {
        return foreignId;
    }

    public void setForeignId(long foreignId) {
        this.foreignId = foreignId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getFinishDateStr() {
        return finishDateStr;
    }

    public void setFinishDateStr(String finishDateStr) {
        this.finishDateStr = finishDateStr;
    }

    public int getPercentComplete() {
        return percentComplete;
    }

    public void setPercentComplete(int percentComplete) {
        this.percentComplete = percentComplete;
    }

    public double getDailyWorkHours() {
        return dailyWorkHours;
    }

    public void setDailyWorkHours(double dailyWorkHours) {
        this.dailyWorkHours = dailyWorkHours;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
