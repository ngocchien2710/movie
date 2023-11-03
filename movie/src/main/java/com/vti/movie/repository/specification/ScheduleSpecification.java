package com.vti.movie.repository.specification;

import com.vti.movie.entity.Schedule;
import com.vti.movie.modal.SearchScheduleRequest;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleSpecification {
    public static Specification<Schedule> buildCondition(SearchScheduleRequest request) {
        return Specification.where(byMovieDate(request.getMovieDate()))
                .and(byShowtime(request.getShowTime()));
    }

    public static Specification<Schedule> byShowtime(LocalTime showtime) {
        if (showtime != null) {
            return (root, query, cri) -> {
                return cri.greaterThan(root.get("time"), showtime);
            };

        }
        return null;
    }

    public static Specification<Schedule> byMovieDate(LocalDate movieDate) {
        if (movieDate != null) {
            return (root, query, cri) -> {
                return cri.greaterThan(root.get("time"), movieDate);
            };

        }
        return null;
    }
}
