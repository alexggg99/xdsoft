package org.agashchuk.kinopoisk.repository;

import org.agashchuk.kinopoisk.model.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long> {
}
