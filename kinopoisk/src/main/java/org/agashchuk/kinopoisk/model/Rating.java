package org.agashchuk.kinopoisk.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private int position;

    private double rate;

    private String originalName;

    private int votes;

    @Temporal(TemporalType.DATE)
    private Date date;

    public Rating() {
    }

    public Rating(int position, double rate, String originalName, int votes, Date date) {
        this.position = position;
        this.rate = rate;
        this.originalName = originalName;
        this.votes = votes;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", position=" + position +
                ", rate=" + rate +
                ", originalName='" + originalName + '\'' +
                ", votes=" + votes +
                ", date=" + date +
                '}';
    }
}