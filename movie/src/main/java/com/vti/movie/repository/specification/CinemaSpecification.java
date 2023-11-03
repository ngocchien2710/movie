package com.vti.movie.repository.specification;

import com.vti.movie.entity.Cinema;
import com.vti.movie.modal.SearchCinemaRequest;
import org.springframework.data.jpa.domain.Specification;

public class CinemaSpecification {
    public static Specification<Cinema> buildCondition(SearchCinemaRequest request){
        return Specification.where(byCinemaName(request.getCinemaName()));
    }

    public static Specification<Cinema> byCinemaName(String cinemaName){
        if (cinemaName != null) {
            return (root, query, cri) -> {
                return cri.like(root.get("name"), "%" + cinemaName+ "%");
            };

        }
        return null;
    }
}
