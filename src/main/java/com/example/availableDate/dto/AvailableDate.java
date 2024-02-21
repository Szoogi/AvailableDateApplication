package com.example.availableDate.dto;

import java.time.LocalDate;
import java.util.List;

public class AvailableDate {
    private LocalDate date;
    private List<AvailableHours> availableHours;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<AvailableHours> getAvailableHours() {
        return availableHours;
    }

    public void setAvailableHours(List<AvailableHours> availableHours) {
        this.availableHours = availableHours;
    }
}
