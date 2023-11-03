package com.vti.movie.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchScheduleRequest {
    private LocalDate movieDate;

    private LocalTime showTime;
}
