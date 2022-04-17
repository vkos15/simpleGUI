package com.example.simplegui;

import java.time.LocalDate;

public class LocalTask {
    private String description;
    private LocalDate date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTask (LocalDate date, String description){
        this.setDate(date);
        this.setDescription(description);
    }
    @Override
    public String toString(){

        return  String.format(" %s %s",this.getDate(),this.getDescription());
    }
}
