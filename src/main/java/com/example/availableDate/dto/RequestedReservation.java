package com.example.availableDate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class RequestedReservation {
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NonNull
    LocalDate reservationDate;

    @JsonFormat(pattern = "HH:mm:ss")
    @NonNull
    LocalTime from;


    @NonNull
    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(@NonNull LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @NonNull
    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(@NonNull LocalTime from) {
        this.from = from;
    }
}
