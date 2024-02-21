package com.example.availableDate.controller;

import com.example.availableDate.dto.AvailableDate;
import com.example.availableDate.dto.RequestedReservation;
import com.example.availableDate.service.DateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mock/delivery")
public class DateController {

    private final DateService dateService;

    private final String ERROR_DATE_FORMAT_MESSAGE = "Wrong date format, use:\n" +
            "'reservationDate': 'yyyy-MM-dd'\n" +
            "'from': 'HH:mm:ss'";

    @Autowired
    public DateController(DateService dateService) {
        this.dateService = dateService;
    }


    @GetMapping("/available-dates")
    public List<AvailableDate> getAvailableDeliveryDates() {
        return dateService.getAvailableDates();
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshDate() {

        dateService.refreshDate();
        return ResponseEntity.ok().body("Dates refreshed");
    }

    @PostMapping("/reserve-date")
    public ResponseEntity<String> reserveDeliveryDate(@RequestBody @Valid RequestedReservation request) {
        List<AvailableDate> availableDates = dateService.getAvailableDates();

        Optional<AvailableDate> findDate = availableDates.stream().filter(availableDate -> availableDate.getDate().equals(request.getReservationDate())).findAny();
        if (findDate.isPresent()) {
            AvailableDate availableDate = findDate.get();
            if (availableDate.getAvailableHours().stream().anyMatch(availableHours -> availableHours.getFrom().equals(request.getFrom()))) {
                dateService.removeDate(request);
                return new ResponseEntity<>("Date reserved successfully: " + request.getReservationDate() + " " + request.getFrom(), HttpStatus.CREATED);
            }
            return ResponseEntity.badRequest().body("Date is not available for reservation.");
        }
        return ResponseEntity.badRequest().body("Date is not available for reservation.");
    }


    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> handleDateTimeParseException(DateTimeParseException ex) {
        return ResponseEntity.badRequest().body(ERROR_DATE_FORMAT_MESSAGE);
    }
}
