package com.vti.movie.repository.specification;

import com.vti.movie.entity.Movie;
import com.vti.movie.modal.SearchMovieRequest;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {
    public static Specification<Movie> buildCondition(SearchMovieRequest request){

        return Specification.where(byMovieName(request.getMovieName()));
    }

    private  static Specification<Movie> byMovieName(String movieName){
        if (movieName != null) {
            return (root, query, cri) -> {
                return cri.like(root.get("name"), "%" + movieName+ "%");
            };

        }
        return null;
    }
}
