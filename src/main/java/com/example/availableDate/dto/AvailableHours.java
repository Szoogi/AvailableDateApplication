package com.example.availableDate.dto;

import java.time.LocalTime;

public class AvailableHours {
    private LocalTime from;
    private LocalTime to;

    public AvailableHours(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }
}
