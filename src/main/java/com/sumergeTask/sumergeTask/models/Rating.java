package com.sumergeTask.sumergeTask.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Rating {
    @Id
    private Long id;
    private int number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
