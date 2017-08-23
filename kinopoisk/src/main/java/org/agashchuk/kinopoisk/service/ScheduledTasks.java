package org.agashchuk.kinopoisk.service;

import org.agashchuk.kinopoisk.model.Rating;
import org.agashchuk.kinopoisk.repository.RatingRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
public class ScheduledTasks implements CommandLineRunner {

    @Autowired
    private RatingRepository ratingRepository;

    @Transactional
    public void crawl() throws Exception {
        List<Rating> ratings = new ArrayList<>();
        Document document = Jsoup.connect("https://www.kinopoisk.ru/top/").get();
        for (Element row : document.getElementsByAttributeValueMatching("id", "^top250_place_.*$")) {
            int position = Integer.valueOf(StringUtils.substring(row.children().get(0).text(), 0, -1));
            String name = StringUtils.trim(row.children().get(1).text());
            double rate = Double.valueOf(StringUtils.substring(row.children().get(2).text(), 0, 5));
            int votes = Integer.valueOf(StringUtils.substring(row.children().get(2).text(), 6).replaceAll("\\D+",""));
            Date date = DateUtils.truncate(Calendar.getInstance(), Calendar.DAY_OF_MONTH).getTime();
            Rating rating = new Rating(position, rate, name, votes, date);
            ratings.add(rating);
        }
        ratingRepository.save(ratings);
    }

    @Override
    public void run(String... strings) throws Exception {
        crawl();
    }
}
