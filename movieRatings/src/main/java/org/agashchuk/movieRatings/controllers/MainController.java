package org.agashchuk.movieRatings.controllers;

import org.agashchuk.movieRatings.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

@Controller
public class MainController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/")
    public String mainPage(Model model) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new GregorianCalendar(2017, 7, 21).getTime();
        model.addAttribute("date",  format.format(date));
        model.addAttribute("topRating", cacheService.getLoadingCache().getUnchecked(date));
        cacheService.getLoadingCache().getUnchecked(date);
        return "index";
    }

    @RequestMapping(value = "/", params = "requestDate")
    public String requestRatings(@RequestParam("requestDate") String requestDate, Model model) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(requestDate);
        model.addAttribute("topRating", cacheService.getLoadingCache().getUnchecked(date));
        model.addAttribute("date",  format.format(date));
        return "index";
    }
}
