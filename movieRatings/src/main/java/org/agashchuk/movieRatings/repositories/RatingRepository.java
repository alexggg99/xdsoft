package org.agashchuk.movieRatings.repositories;

import org.agashchuk.movieRatings.models.Rating;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {
    List<Rating> findByDateEquals(Date date);
}
