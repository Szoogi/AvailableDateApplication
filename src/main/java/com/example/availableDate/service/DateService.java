package com.example.availableDate.service;

import com.example.availableDate.dto.AvailableDate;
import com.example.availableDate.dto.AvailableHours;
import com.example.availableDate.dto.RequestedReservation;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DateService {

    private List<AvailableDate> availableDates;

    public DateService() {
        this.availableDates = generateAvailableDates();
    }

    public List<AvailableDate> getAvailableDates() {
        return availableDates;
    }


    public List<AvailableDate> generateAvailableDates() {
        List<AvailableDate> availableDates = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        for (int i = 0; i < 6 ; i++) {
            AvailableDate availableDate = new AvailableDate();
            availableDate.setDate(currentDate.plusDays(i));
            availableDate.setAvailableHours(generateAvailableHours());
            availableDates.add(availableDate);
        }

        return availableDates;
    }


    public void refreshDate() {
        this.availableDates = generateAvailableDates();
    }

    private List<AvailableHours> generateAvailableHours() {
        List<AvailableHours> availableHoursList = new ArrayList<>();
        availableHoursList.add(new AvailableHours(LocalTime.of(8, 0), LocalTime.of(10, 0)));
        availableHoursList.add(new AvailableHours(LocalTime.of(10, 0), LocalTime.of(12, 0)));
        availableHoursList.add(new AvailableHours(LocalTime.of(12, 0), LocalTime.of(14, 0)));
        availableHoursList.add(new AvailableHours(LocalTime.of(14, 0), LocalTime.of(16, 0)));
        availableHoursList.add(new AvailableHours(LocalTime.of(16, 0), LocalTime.of(18, 0)));
        return availableHoursList;
    }

    public void removeDate(RequestedReservation dateToRemove) {
        for (AvailableDate availableDate : availableDates) {
            if (availableDate.getDate().equals(dateToRemove.getReservationDate())) {
                List<AvailableHours> availableHoursList = availableDate.getAvailableHours();
                availableHoursList.removeIf(availableHours ->
                        availableHours.getFrom().equals(dateToRemove.getFrom()));
                break;
            }
        }
    }
}
