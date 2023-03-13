package com.devsuperior.dslearnbds.entities;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.util.Objects;

public class Notfication {

    private Long id;
    private String text;
    private Instant momento;
    private boolean read = false;
    private String route;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notfication(Long id, String text, Instant momento, boolean read, String route, User user) {
        this.id = id;
        this.text = text;
        this.momento = momento;
        this.read = read;
        this.route = route;
        this.user = user;
    }

    public Notfication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getMomento() {
        return momento;
    }

    public void setMomento(Instant momento) {
        this.momento = momento;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notfication)) return false;

        Notfication that = (Notfication) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
