package org.agashchuk.movieRatings.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.agashchuk.movieRatings.models.Rating;
import org.agashchuk.movieRatings.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private RatingRepository ratingRepository;

    private LoadingCache<Date, List<Rating>> cache;

    public CacheService() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(100, TimeUnit.MINUTES)
                .build(
                    new CacheLoader<Date, List<Rating>>() {
                        @Override
                        public List<Rating> load(Date id) throws Exception {
                            return getRatingByDate(id);
                        }
                    }
                );
    }
    public LoadingCache<Date, List<Rating>> getLoadingCache() {
        return cache;
    }

    public List<Rating> getRatingByDate(Date date) {
        return ratingRepository.findByDateEquals(date);
    }
}
